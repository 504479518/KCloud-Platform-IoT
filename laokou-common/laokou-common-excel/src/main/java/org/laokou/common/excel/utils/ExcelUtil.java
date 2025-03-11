/*
 * Copyright (c) 2022-2025 KCloud-Platform-IoT Author or Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.laokou.common.excel.utils;

import cn.idev.excel.ExcelWriter;
import cn.idev.excel.FastExcel;
import cn.idev.excel.context.AnalysisContext;
import cn.idev.excel.read.listener.ReadListener;
import cn.idev.excel.util.ListUtils;
import cn.idev.excel.write.metadata.WriteSheet;
import com.google.common.collect.Lists;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.laokou.common.core.utils.CollectionUtil;
import org.laokou.common.core.utils.ResponseUtil;
import org.laokou.common.excel.validator.ExcelValidator;
import org.laokou.common.i18n.common.exception.SystemException;
import org.laokou.common.i18n.dto.PageQuery;
import org.laokou.common.i18n.dto.Result;
import org.laokou.common.i18n.utils.DateUtil;
import org.laokou.common.i18n.utils.ObjectUtil;
import org.laokou.common.i18n.utils.StringUtil;
import org.laokou.common.i18n.utils.ValidatorUtil;
import org.laokou.common.mybatisplus.mapper.BaseDO;
import org.laokou.common.mybatisplus.mapper.CrudMapper;
import org.laokou.common.mybatisplus.utils.MybatisUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.function.BiConsumer;

import static org.laokou.common.i18n.common.constant.StringConstant.DROP;
import static org.laokou.common.i18n.common.constant.StringConstant.EMPTY;

/**
 * Excel工具类.
 *
 * @author laokou
 */
@Slf4j
public final class ExcelUtil {

	private static final int BATCH_SIZE = 100000;

	private static final int PARTITION_SIZE = 10000;

	private static final int DEFAULT_SIZE = 1000000;

	private ExcelUtil() {
	}

	public static <MAPPER extends CrudMapper<?, ?, DO>, EXCEL, DO> void doImport(String sheetName, Class<EXCEL> excel,
			ExcelConvertor<DO, EXCEL> convert, InputStream inputStream, HttpServletResponse response,
			Class<MAPPER> clazz, BiConsumer<MAPPER, DO> consumer, MybatisUtil mybatisUtil) {
		doImport(sheetName, excel, convert, inputStream, response, clazz, consumer, mybatisUtil, null);
	}

	public static <MAPPER extends CrudMapper<?, ?, DO>, EXCEL, DO> void doImport(String sheetName, Class<EXCEL> excel,
			ExcelConvertor<DO, EXCEL> convert, InputStream inputStream, HttpServletResponse response,
			Class<MAPPER> clazz, BiConsumer<MAPPER, DO> consumer, MybatisUtil mybatisUtil,
			ExcelValidator<EXCEL> validator) {
		FastExcel
			.read(inputStream, excel, new DataListener<>(clazz, consumer, response, mybatisUtil, convert, validator))
			.sheet(sheetName)
			.doRead();
	}

	public static <EXCEL, DO extends BaseDO> void doExport(String fileName, String sheetName,
			HttpServletResponse response, PageQuery pageQuery, CrudMapper<Long, Integer, DO> crudMapper,
			Class<EXCEL> clazz, ExcelConvertor<DO, EXCEL> convertor, ExecutorService virtualThreadExecutor) {
		doExport(fileName, sheetName, BATCH_SIZE, response, pageQuery, crudMapper, clazz, convertor,
				virtualThreadExecutor);
	}

	public static <EXCEL, DO extends BaseDO> void doExport(String fileName, String sheetName, int size,
			HttpServletResponse response, PageQuery pageQuery, CrudMapper<Long, Integer, DO> crudMapper,
			Class<EXCEL> clazz, ExcelConvertor<DO, EXCEL> convertor, ExecutorService virtualThreadExecutor) {
		if (crudMapper.selectObjectCount(pageQuery) > 0) {
			try (ServletOutputStream out = response.getOutputStream();
					ExcelWriter excelWriter = FastExcel.write(out, clazz).build()) {
				// 设置请求头
				setHeader(fileName, response);
				// https://idev.cn/fastexcel/zh-CN/docs/write/write_hard
				List<DO> list = Collections.synchronizedList(new ArrayList<>(size));
				// 设置sheet页
				WriteSheet writeSheet = FastExcel.writerSheet(sheetName).head(clazz).build();
				crudMapper.selectObjectListHandler(pageQuery, resultContext -> {
					list.add(resultContext.getResultObject());
					if (list.size() % size == 0) {
						try {
							// 分组
							List<List<DO>> partition = Lists.partition(list, PARTITION_SIZE);
							List<Callable<Boolean>> futures = partition.stream().map(item -> (Callable<Boolean>) () -> {
								// 写数据
								excelWriter.write(convertor.toExcels(list), writeSheet);
								return true;
							}).toList();
							virtualThreadExecutor.invokeAll(futures);
						}
						catch (InterruptedException e) {
							Thread.currentThread().interrupt();
							log.error("未知错误，错误信息：{}", e.getMessage(), e);
							throw new SystemException("S_UnKnow_Error", e.getMessage(), e);
						}
						finally {
							list.clear();
						}
					}
				});
				if (list.size() % size != 0) {
					// 写数据
					excelWriter.write(convertor.toExcels(list), writeSheet);
					list.clear();
				}
				// 刷新数据
				excelWriter.finish();
			}
			catch (Exception e) {
				log.error("Excel导出失败，错误信息：{}", e.getMessage());
				throw new SystemException("S_Excel_UnKnowError", "Excel导出失败，系统繁忙", e);
			}
		}
		else {
			throw new SystemException("S_Excel_DataIsEmpty", "Excel导出失败，数据不能为空");
		}
	}

	private static void setHeader(String fileName, HttpServletResponse response) {
		fileName = fileName + "_导出全部_" + DateUtil.format(DateUtil.now(), DateUtil.YYYYMMDDHHMMSS) + ".xlsx";
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-disposition",
				"attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
		response.addHeader("Access-Control-Expose-Headers", "Content-disposition");
	}

	public interface ExcelConvertor<DO, EXCEL> {

		List<EXCEL> toExcels(List<DO> list);

		DO toDataObject(EXCEL excel);

	}

	private static class DataListener<MAPPER extends CrudMapper<?, ?, DO>, DO, EXCEL> implements ReadListener<EXCEL> {

		/**
		 * Temporary storage of data.
		 */
		private final List<DO> CACHED_DATA_LIST;

		/**
		 * 错误信息.
		 */
		private final List<String> ERRORS;

		private final HttpServletResponse response;

		private final MybatisUtil mybatisUtil;

		private final Class<MAPPER> clazz;

		private final BiConsumer<MAPPER, DO> consumer;

		private final ExcelConvertor<DO, EXCEL> convertor;

		private final ExcelValidator<EXCEL> validator;

		DataListener(Class<MAPPER> clazz, BiConsumer<MAPPER, DO> consumer, HttpServletResponse response,
				MybatisUtil mybatisUtil, ExcelConvertor<DO, EXCEL> convertor, ExcelValidator<EXCEL> validator) {
			this.clazz = clazz;
			this.response = response;
			this.validator = validator;
			this.ERRORS = new ArrayList<>();
			this.CACHED_DATA_LIST = ListUtils.newArrayListWithExpectedSize(DEFAULT_SIZE);
			this.mybatisUtil = mybatisUtil;
			this.consumer = consumer;
			this.convertor = convertor;
		}

		@Override
		public void invoke(EXCEL excel, AnalysisContext context) {
			int currentRowNum = context.readRowHolder().getRowIndex() + 1;
			// 校验数据
			Set<String> validates;
			if (ObjectUtil.isNotNull(validator)) {
				validates = validator.validate(excel);
			}
			else {
				validates = ValidatorUtil.validate(excel);
			}
			if (CollectionUtil.isNotEmpty(validates)) {
				ERRORS.add(getTemplate(currentRowNum, StringUtil.collectionToDelimitedString(validates, DROP)));
			}
			else {
				CACHED_DATA_LIST.add(convertor.toDataObject(excel));
			}
		}

		@Override
		public void onException(Exception e, AnalysisContext context) {
			log.error("Excel导入异常，错误信息：{}", e.getMessage());
			throw new SystemException("S_Excel_ImportError", "Excel导入异常，系统繁忙", e);
		}

		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			// log.info("完成数据解析");
			if (CollectionUtil.isNotEmpty(ERRORS)) {
				try {
					ResponseUtil.responseOk(response, Result.fail("S_Excel_ImportFailed", "Excel导入失败【仅显示前100条】",
							ERRORS.subList(0, Math.min(ERRORS.size(), 100))));
					// 清除数据
					CACHED_DATA_LIST.clear();
					return;
				}
				catch (IOException e) {
					log.error("Excel导入失败，错误信息：{}", e.getMessage(), e);
					throw new SystemException("S_Excel_ImportFailed", e.getMessage(), e);
				}
			}
			if (CollectionUtil.isNotEmpty(CACHED_DATA_LIST)) {
				mybatisUtil.batch(CACHED_DATA_LIST, clazz, consumer);
				// 清除数据
				CACHED_DATA_LIST.clear();
			}
			try {
				ResponseUtil.responseOk(response, Result.ok(EMPTY));
			}
			catch (IOException e) {
				log.error("Excel导入失败，错误信息：{}", e.getMessage(), e);
				throw new SystemException("S_Excel_ImportFailed", e.getMessage(), e);
			}
		}

		private String getTemplate(int num, String msg) {
			return String.format("第%s行，%s", num, msg);
		}

	}

}
