(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-a095a912"],{"5bd3":function(e,t,r){"use strict";r.d(t,"a",(function(){return i}));var o=r("b775");function i(){return Object(o["b"])({url:"/admin/v1/tokens",method:"get"})}},"765c":function(e,t,r){"use strict";r.d(t,"j",(function(){return i})),r.d(t,"k",(function(){return a})),r.d(t,"f",(function(){return n})),r.d(t,"g",(function(){return s})),r.d(t,"a",(function(){return d})),r.d(t,"b",(function(){return l})),r.d(t,"l",(function(){return u})),r.d(t,"o",(function(){return c})),r.d(t,"p",(function(){return m})),r.d(t,"m",(function(){return f})),r.d(t,"q",(function(){return p})),r.d(t,"n",(function(){return h})),r.d(t,"i",(function(){return b})),r.d(t,"c",(function(){return v})),r.d(t,"e",(function(){return g})),r.d(t,"h",(function(){return y})),r.d(t,"d",(function(){return k}));var o=r("b775");function i(e){return Object(o["b"])({url:"/admin/v1/resource/list",method:"post",data:e})}function a(e){return Object(o["b"])({url:"/admin/v1/resource/task-list",method:"post",data:e})}function n(e){return Object(o["b"])({url:"/admin/v1/resource/"+e,method:"get"})}function s(e){return Object(o["b"])({url:"/admin/v1/resource/"+e+"/detail-task",method:"get"})}function d(e,t){return Object(o["b"])({url:"/admin/v1/resource/audit-task",method:"post",data:e,headers:{"Content-Type":"application/json;charset=UTF-8","Request-Id":t}})}function l(e,t){return Object(o["b"])({url:"/admin/v1/resource/delegate-task",method:"post",data:e,headers:{"Content-Type":"application/json;charset=UTF-8","Request-Id":t}})}function u(e,t){return Object(o["b"])({url:"/admin/v1/resource/resolve-task",method:"post",data:e,headers:{"Content-Type":"application/json;charset=UTF-8","Request-Id":t}})}function c(e,t){return Object(o["b"])({url:"/admin/v1/resource/transfer-task",method:"post",data:e,headers:{"Content-Type":"application/json;charset=UTF-8","Request-Id":t}})}function m(e,t){return Object(o["b"])({url:"/admin/v1/resource",method:"put",data:e,headers:{"Content-Type":"application/json;charset=UTF-8","Request-Id":t}})}function f(e){return Object(o["b"])({url:"/admin/v1/resource/search",method:"post",data:e})}function p(e){return Object(o["b"])({url:"/admin/v1/resource/upload",method:"post",data:e})}function h(){return Object(o["b"])({url:"/admin/v1/resource/sync",method:"post"})}function b(e,t){return Object(o["b"])({url:"/admin/v1/resource",method:"post",data:e,headers:{"Content-Type":"application/json;charset=UTF-8","Request-Id":t}})}function v(e){return Object(o["b"])({url:"/admin/v1/resource/"+e,method:"delete"})}function g(e){return Object(o["b"])({url:"/admin/v1/resource/"+e+"/audit-log",method:"get"})}function y(e){return Object(o["b"])({url:"/admin/v1/resource/"+e+"/diagram",method:"get"})}function k(e){return Object(o["b"])({url:"/admin/v1/resource/"+e+"/download",method:"get",responseType:"blob"})}},"88b4":function(e,t,r){"use strict";r.r(t);var o=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("a-drawer",{attrs:{width:"35%","label-col":4,"wrapper-col":14,visible:e.open},on:{close:e.onClose}},[r("a-divider",{attrs:{orientation:"left"}},[r("b",[e._v(e._s(e.formTitle))])]),r("a-form-model",{ref:"form",attrs:{model:e.form,rules:e.rules}},[r("a-form-model-item",{attrs:{label:"标题",prop:"title"}},[r("a-input",{attrs:{placeholder:"请输入标题"},model:{value:e.form.title,callback:function(t){e.$set(e.form,"title",t)},expression:"form.title"}})],1),r("a-form-model-item",{attrs:{label:"编码",prop:"code"}},[r("a-select",{attrs:{size:"large",placeholder:"请选择编码"},on:{change:e.changeResource},model:{value:e.form.code,callback:function(t){e.$set(e.form,"code",t)},expression:"form.code"}},[r("a-select-option",{key:"video",attrs:{value:"video"}},[e._v("视频")]),r("a-select-option",{key:"audio",attrs:{value:"audio"}},[e._v("音频")]),r("a-select-option",{key:"image",attrs:{value:"image"}},[e._v("图片")])],1)],1),r("a-form-model-item",{attrs:{label:"资源",prop:"url"}},[r("a-input",{staticStyle:{display:"none"},attrs:{placeholder:"请上传资源"},model:{value:e.form.url,callback:function(t){e.$set(e.form,"url",t)},expression:"form.url"}}),r("a-upload",{attrs:{remove:e.handleRemove,multiple:!1,"file-list":e.fileList,name:"file",accept:".gif,.GIF,.jpg,.JPG,.jpeg,.JPEG,.png,.PNG,.bmp,.BMP,.webp,.WEBP,.flac,.FLAC,.cda,.wav,.mp3,.aif,.aiff,.mid,.wma,.ra,.vqf,.ape,.CDA,.WAV,.MP3,.AIF,.AIFF,.MID,.WMA,.RA,.VQF,.APE,.mp4,.MP4,.AVI,.mov,.rmvb,.rm,.FLV,.mp4,.3GP,.flv","before-upload":e.beforeUpload},on:{change:e.uploadFile}},[r("a-button",{attrs:{disabled:e.disabled}},[e._v(" 上传资源 ")])],1),r("img",{directives:[{name:"show",rawName:"v-show",value:e.display1,expression:"display1"}],attrs:{src:e.form.url,width:"100",height:"100",alt:"暂无图片"}}),r("audio",{directives:[{name:"show",rawName:"v-show",value:e.display2,expression:"display2"}],attrs:{loop:"loop",src:e.form.url,controls:"controls"}},[r("object",{attrs:{data:e.form.url}},[r("embed",{attrs:{src:e.form.url}})])]),r("video",{directives:[{name:"show",rawName:"v-show",value:e.display3,expression:"display3"}],attrs:{src:e.form.url,loop:"loop",width:"200",height:"100",controls:"controls"}},[r("source",{attrs:{src:e.form.url,type:"video/mp4"}}),r("object",{attrs:{data:e.form.url,width:"200",height:"100"}},[r("embed",{attrs:{src:e.form.url,width:"200",height:"100"}})])])],1),r("a-form-model-item",{attrs:{label:"备注",prop:"remark"}},[r("a-input",{attrs:{placeholder:"请输入备注",type:"textarea","allow-clear":""},model:{value:e.form.remark,callback:function(t){e.$set(e.form,"remark",t)},expression:"form.remark"}})],1),r("div",{staticClass:"bottom-control"},[r("a-space",[r("a-button",{attrs:{type:"primary",loading:e.submitLoading},on:{click:e.submitForm}},[e._v(" 保存 ")]),r("a-button",{attrs:{type:"dashed"},on:{click:e.cancel}},[e._v(" 取消 ")])],1)],1)],1)],1)},i=[],a=r("2909"),n=(r("fb6a"),r("a434"),r("99af"),r("d3b7"),r("765c")),s=r("5bd3"),d={name:"CreateForm",components:{},data:function(){return{fileList:[],accessToken:"",submitLoading:!1,formTitle:"",tagInputVisible:!1,tagInputValue:"",form:{id:void 0,title:void 0,url:void 0,code:"video",status:0,remark:void 0,instanceId:void 0},disabled:!1,open:!1,display1:!1,display2:!1,display3:!1,rules:{title:[{required:!0,message:"标题不能为空",trigger:"blur"}],remark:[{required:!0,message:"备注不能为空",trigger:"blur"}],url:[{required:!0,message:"资源地址不能为空",trigger:"blur"}],code:[{required:!0,message:"编码不能为空",trigger:"blur"}]}}},filters:{},created:function(){},computed:{},watch:{},methods:{token:function(){var e=this;Object(s["a"])().then((function(t){e.accessToken=t.data.token}))},onClose:function(){this.open=!1},cancel:function(){this.open=!1,this.reset()},reset:function(){0===this.fileList.length?this.disabled=!1:this.disabled=!0,this.form={id:void 0,title:void 0,url:void 0,code:"video",status:0,remark:void 0,instanceId:void 0}},changeResource:function(){this.display()},handleRemove:function(e){var t=this.fileList.indexOf(e),r=this.fileList.slice();r.splice(t,1),this.fileList=r},uploadFile:function(e){var t=this;if(1===e.fileList.length){this.disabled=!0;var r=new FormData;r.append("file",e.file),Object(n["q"])(r).then((function(e){t.form.url=e.data.url,t.display()}))}else this.display1=!1,this.display2=!1,this.display3=!1,this.disabled=!1,this.form.url=void 0},beforeUpload:function(e){return this.fileList=[].concat(Object(a["a"])(this.fileList),[e]),!1},handleAdd:function(){this.reset(),this.token(),this.open=!0,this.display1=!1,this.display2=!1,this.display3=!1,this.formTitle="图片新增"},handleUpdate:function(e,t){var r=this;this.reset(),this.token();var o=e?e.id:t;Object(n["f"])(o).then((function(e){r.form.id=e.data.id,r.form.url=e.data.url,r.form.title=e.data.title,r.form.code=e.data.code,r.display(),r.form.instanceId=e.data.instanceId,r.form.remark=e.data.remark,r.open=!0,r.formTitle="资源修改"}))},display:function(){"image"===this.form.code&&(this.display1=!0,this.display2=!1,this.display3=!1),"audio"===this.form.code&&(this.display1=!1,this.display2=!0,this.display3=!1),"video"===this.form.code&&(this.display1=!1,this.display2=!1,this.display3=!0)},submitForm:function(){var e=this;this.$refs.form.validate((function(t){if(!t)return!1;if(e.submitLoading=!0,void 0!==e.form.id){var r={resourceCO:e.form};Object(n["p"])(r,e.accessToken).then((function(){e.$message.success("修改成功",3),e.open=!1,e.$emit("ok")})).catch((function(){e.token()})).finally((function(){e.submitLoading=!1}))}else{var o={resourceCO:e.form};Object(n["i"])(o,e.accessToken).then((function(){e.$message.success("新增成功",3),e.open=!1,e.$emit("ok")})).catch((function(){e.token()})).finally((function(){e.submitLoading=!1}))}}))}}},l=d,u=r("2877"),c=Object(u["a"])(l,o,i,!1,null,null,null);t["default"]=c.exports}}]);