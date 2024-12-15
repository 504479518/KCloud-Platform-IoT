package com.aizuda.snailjob.server.starter.listener;

import com.aizuda.snailjob.common.core.constant.SystemConstants;
import com.aizuda.snailjob.common.core.util.SnailJobVersion;
import com.aizuda.snailjob.common.log.SnailJobLog;
import com.aizuda.snailjob.server.common.Lifecycle;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.laokou.common.banner.utils.ResourceUtil;
import org.slf4j.helpers.MessageFormatter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 系统启动监听器
 *
 * @author opensnail 2021-11-19 19:00
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class StartListener implements ApplicationListener<ContextRefreshedEvent> {

	private final List<Lifecycle> lifecycleList;

	private volatile boolean isStarted = false;

	@Override
	@SneakyThrows
	public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
		if (isStarted) {
			SnailJobLog.LOCAL.info("snail-job server already started v{}", SnailJobVersion.getVersion());
			return;
		}
		String bannerContent = ResourceUtil.getResource("banner.txt").getContentAsString(StandardCharsets.UTF_8).trim();
		log.info("{}\n", MessageFormatter.format(bannerContent, "").getMessage());
		log.info(MessageFormatter.format(SystemConstants.LOGO, SnailJobVersion.getVersion()).getMessage());
		SnailJobLog.LOCAL.info("snail-job server is preparing to start... v{}", SnailJobVersion.getVersion());
		lifecycleList.forEach(Lifecycle::start);
		SnailJobLog.LOCAL.info("snail-job server started successfully v{}", SnailJobVersion.getVersion());
		isStarted = true;
	}

}
