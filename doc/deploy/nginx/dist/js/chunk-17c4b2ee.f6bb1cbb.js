(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-17c4b2ee","chunk-6d3e0918"],{"027e":function(e,t,i){"use strict";i.r(t);var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("a-drawer",{attrs:{width:"35%","label-col":4,"wrapper-col":14,visible:e.open},on:{close:e.onClose}},[i("a-divider",{attrs:{orientation:"left"}},[i("b",[e._v(e._s(e.formTitle))])]),i("a-form-model",{ref:"form",attrs:{model:e.form}},[i("a-form-model-item",{attrs:{label:"流程图",prop:"file"}},[i("a-upload",{attrs:{name:"file",remove:e.handleRemove,multiple:!1,"file-list":e.fileList,accept:".xml","before-upload":e.beforeUpload},on:{change:e.uploadFile}},[i("a-button",{attrs:{disabled:e.disabled}},[e._v(" 上传流程图 ")])],1)],1),i("div",{staticClass:"bottom-control"},[i("a-space",[i("a-button",{attrs:{type:"primary",loading:e.submitLoading},on:{click:e.submitForm}},[e._v(" 保存 ")]),i("a-button",{attrs:{type:"dashed"},on:{click:e.cancel}},[e._v(" 取消 ")])],1)],1)],1)],1)},a=[],s=i("2909"),r=(i("99af"),i("fb6a"),i("a434"),i("b64b"),i("d3b7"),i("831d")),o=i("5bd3"),c={name:"CreateForm",props:{},components:{},data:function(){return{accessToken:"",fileList:[],submitLoading:!1,formTitle:"",fileData:{},form:{name:void 0},open:!1,disabled:!1}},filters:{},created:function(){},computed:{},watch:{},methods:{token:function(){var e=this;Object(o["a"])().then((function(t){e.accessToken=t.data.token}))},onClose:function(){this.open=!1},cancel:function(){this.open=!1,this.reset()},reset:function(){this.fileList=[],this.disabled=!1},handleAdd:function(){this.reset(),this.token(),this.open=!0,this.formTitle="流程新增"},beforeUpload:function(e){return this.fileList=[].concat(Object(s["a"])(this.fileList),[e]),!1},handleRemove:function(e){var t=this.fileList.indexOf(e),i=this.fileList.slice();i.splice(t,1),this.fileList=i},uploadFile:function(e){this.disabled=e.fileList.length>0,this.fileData=e.file},submitForm:function(){var e=this;this.$refs.form.validate((function(t){if(!t)return!1;if(0!==Object.keys(e.fileData).length){e.submitLoading=!0;var i=new FormData;i.append("file",e.fileData),Object(r["e"])(i,e.accessToken).then((function(){e.$message.success("新增成功",3),e.open=!1,e.$emit("ok")})).catch((function(){e.token()})).finally((function(){e.submitLoading=!1,e.reset()}))}else e.$message.error("请上传相关流程图",3)}))}}},d=c,l=i("2877"),u=Object(l["a"])(d,n,a,!1,null,null,null);t["default"]=u.exports},"4a82":function(e,t,i){"use strict";i.r(t);var n=function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("page-header-wrapper",[i("a-card",{attrs:{bordered:!1}},[i("div",{staticClass:"table-page-search-wrapper"},[i("a-form",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:list"],expression:"['definitions:list']"}],attrs:{layout:"inline"}},[i("a-row",{attrs:{gutter:48}},[i("a-col",{attrs:{md:8,sm:24}},[i("a-form-item",{attrs:{label:"流程名称"}},[i("a-input",{attrs:{placeholder:"请输入流程名称","allow-clear":""},model:{value:e.queryParam.name,callback:function(t){e.$set(e.queryParam,"name",t)},expression:"queryParam.name"}})],1)],1),i("a-col",{attrs:{md:8,sm:24}},[i("span",{staticClass:"table-page-search-submitButtons"},[i("a-button",{attrs:{type:"primary"},on:{click:e.handleQuery}},[i("a-icon",{attrs:{type:"search"}}),e._v("查询")],1),i("a-button",{staticStyle:{"margin-left":"8px"},on:{click:e.resetQuery}},[i("a-icon",{attrs:{type:"redo"}}),e._v("重置")],1)],1)])],1)],1)],1),i("div",{staticClass:"table-operations"},[i("a-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:insert"],expression:"['definitions:insert']"}],attrs:{type:"primary"},on:{click:function(t){return e.$refs.createForm.handleAdd()}}},[i("a-icon",{attrs:{type:"plus"}}),e._v("新增 ")],1),i("a-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:template"],expression:"['definitions:template']"}],on:{click:e.downloadTemplate}},[i("a-icon",{attrs:{type:"download"}}),e._v("模板 ")],1)],1),i("create-form",{ref:"createForm",on:{ok:e.getList}}),i("a-table",{attrs:{loading:e.loading,size:e.tableSize,rowKey:"definitionId",columns:e.columns,"data-source":e.list,pagination:!1,bordered:e.tableBordered},scopedSlots:e._u([{key:"suspended",fn:function(t,n){return i("span",{},[e._v(" "+e._s(e.statusFormat(n))+" ")])}},{key:"operation",fn:function(t,n){return i("span",{},[i("a",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:insert"],expression:"['definitions:insert']"}],on:{click:function(t){return e.$refs.createForm.handleAdd()}}},[i("a-icon",{attrs:{type:"plus"}}),e._v(" 新增 ")],1),i("a-divider",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:suspend"],expression:"['definitions:suspend']"}],attrs:{type:"vertical"}}),i("a",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:suspend"],expression:"['definitions:suspend']"}],on:{click:function(t){return e.suspendFlow(n)}}},[i("a-icon",{attrs:{type:"pause-circle"}}),e._v(" 挂起 ")],1),i("a-divider",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:activate"],expression:"['definitions:activate']"}],attrs:{type:"vertical"}}),i("a",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:activate"],expression:"['definitions:activate']"}],on:{click:function(t){return e.activateFlow(n)}}},[i("a-icon",{attrs:{type:"play-circle"}}),e._v(" 激活 ")],1),i("a-divider",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:delete"],expression:"['definitions:delete']"}],attrs:{type:"vertical"}}),i("a",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:delete"],expression:"['definitions:delete']"}],on:{click:function(t){return e.handleDelete(n)}}},[i("a-icon",{attrs:{type:"delete"}}),e._v(" 删除 ")],1),i("a-divider",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:diagram"],expression:"['definitions:diagram']"}],attrs:{type:"vertical"}}),i("a",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["definitions:diagram"],expression:"['definitions:diagram']"}],on:{click:function(t){return e.getDefinition(n)}}},[i("a-icon",{attrs:{type:"eye"}}),e._v(" 查看 ")],1)],1)}}])}),i("a-pagination",{staticClass:"ant-table-pagination",attrs:{"show-size-changer":"","show-quick-jumper":"",current:e.queryParam.pageNum,total:e.total,"page-size":e.queryParam.pageSize,showTotal:function(){return"共 "+e.total+" 条"}},on:{showSizeChange:e.onShowSizeChange,change:e.changeSize}})],1),i("a-modal",{ref:"noticeDetail",attrs:{width:900,visible:e.visible,footer:null},on:{cancel:e.close}},[i("template",{slot:"title"},[i("center",[i("a-tag",{attrs:{color:"blue"}},[e._v("流程图")])],1)],1),i("img",{staticStyle:{width:"100%",height:"100%"},attrs:{src:e.flowUri}})],2)],1)},a=[],s=(i("d3b7"),i("3ca3"),i("ddb0"),i("2b3d"),i("9861"),i("831d")),r=i("027e"),o=i("435a"),c=i("c1df"),d=i.n(c),l={name:"Definition",components:{CreateForm:r["default"]},mixins:[o["a"]],data:function(){return{list:[],flowUri:"",visible:!1,selectedRowKeys:[],selectedRows:[],multiple:!0,ids:[],loading:!1,total:0,queryParam:{pageNum:1,pageSize:10,name:""},columns:[{title:"流程标识",dataIndex:"processKey",ellipsis:!0,align:"center"},{title:"流程名称",dataIndex:"processName",align:"center"},{title:"状态",dataIndex:"isSuspended",scopedSlots:{customRender:"suspended"},align:"center"},{title:"操作",dataIndex:"operation",scopedSlots:{customRender:"operation"},align:"center",width:"40%"}]}},filters:{},created:function(){this.getList()},computed:{},watch:{},methods:{downloadTemplate:function(){var e=this;Object(s["b"])().then((function(t){var i=window.URL.createObjectURL(t),n=document.createElement("a");n.style.display="none",n.download=d()(new Date).format("YYYYMMDDHHmmss")+".bpmn20.xml",n.href=i,document.body.appendChild(n),n.click(),document.body.removeChild(n),window.URL.revokeObjectURL(i),e.$message.success("下载成功",3)}))},statusFormat:function(e){return e.isSuspended?"挂起":"激活"},close:function(){this.visible=!1,this.flowUri=""},getList:function(){var e=this;this.loading=!0,Object(s["f"])(this.queryParam).then((function(t){e.list=t.data.records,e.total=t.data.total-0,e.loading=!1}))},getDefinition:function(e){var t=this;this.visible=!0,Object(s["d"])(e.definitionId).then((function(e){t.flowUri="data:image/png;base64,"+e.data}))},handleQuery:function(){this.queryParam.pageNum=1,this.getList()},resetQuery:function(){this.dateRange=[],this.queryParam={pageNum:1,pageSize:10,name:""},this.handleQuery()},suspendFlow:function(e){var t=this,i=this;Object(s["g"])(e.definitionId).then((function(){i.getList(),t.$message.success("挂起成功",3)}))},activateFlow:function(e){var t=this,i=this;Object(s["a"])(e.definitionId).then((function(){i.getList(),t.$message.success("激活成功",3)}))},onShowSizeChange:function(e,t){this.queryParam.pageSize=t,this.getList()},changeSize:function(e,t){this.queryParam.pageNum=e,this.queryParam.pageSize=t,this.getList()},handleDelete:function(e){var t=this,i=e.deploymentId;this.$confirm({title:"确认删除所选中数据?",content:"当前选中编号为"+i+"的数据",onOk:function(){return Object(s["c"])(i).then((function(){t.getList(),t.$message.success("删除成功",3)}))},onCancel:function(){}})}}},u=l,m=i("2877"),f=Object(m["a"])(u,n,a,!1,null,null,null);t["default"]=f.exports},"5bd3":function(e,t,i){"use strict";i.d(t,"a",(function(){return a}));var n=i("b775");function a(){return Object(n["b"])({url:"/admin/v1/tokens",method:"get"})}},"831d":function(e,t,i){"use strict";i.d(t,"f",(function(){return a})),i.d(t,"e",(function(){return s})),i.d(t,"g",(function(){return r})),i.d(t,"a",(function(){return o})),i.d(t,"c",(function(){return c})),i.d(t,"d",(function(){return d})),i.d(t,"b",(function(){return l}));var n=i("b775");function a(e){return Object(n["b"])({url:"/admin/v1/definitions/list",method:"post",data:e})}function s(e,t){return Object(n["b"])({url:"/admin/v1/definitions",method:"post",data:e,headers:{"Content-Type":"application/json;charset=UTF-8","Request-Id":t}})}function r(e){return Object(n["b"])({url:"/admin/v1/definitions/"+e+"/suspend",method:"put"})}function o(e){return Object(n["b"])({url:"/admin/v1/definitions/"+e+"/activate",method:"put"})}function c(e){return Object(n["b"])({url:"/admin/v1/definitions/"+e,method:"delete"})}function d(e){return Object(n["b"])({url:"/admin/v1/definitions/"+e+"/diagram",method:"get"})}function l(){return Object(n["b"])({url:"/admin/v1/definitions/template",method:"get",responseType:"blob"})}}}]);