(function () {

    var jsList = document.scripts;
    var thisJavaScriptUrl = jsList[jsList.length - 1].src.substring(0, jsList[jsList.length - 1].src.lastIndexOf("/") + 1);

    var AllJs = [
    //必须要的js
   thisJavaScriptUrl + "jquery-3.3.1.min.js",
   thisJavaScriptUrl + "layuiadmin/layui/layui.js?t=1",
   thisJavaScriptUrl + "global.js",
   thisJavaScriptUrl + "../Content/kindeditor/kindeditor-all-min.js",
    thisJavaScriptUrl + "../Content/kindeditor/lang/zh-CN.js",
   thisJavaScriptUrl + "utils.js",
     thisJavaScriptUrl + "Page.js",
        thisJavaScriptUrl + "jquery.unobtrusive-ajax.min.js",
         thisJavaScriptUrl + "webuploader/webuploader.min.js",
        thisJavaScriptUrl + "webuploader/uploadifytowebupload.js"
    ]
    var AllCss = [
    //必须要的css
      thisJavaScriptUrl + "layuiadmin/layui/css/layui.css",
      thisJavaScriptUrl + "../Content/global.css",
      thisJavaScriptUrl + "layuiadmin/style/admin.css",
      thisJavaScriptUrl + "webuploader/webuploader.css"
    ]


    var strJs = "";
    var strCss = "";
    for (i = 0; i < AllJs.length; i++) {
        strJs += '<script src="' + AllJs[i] + '" type="text/javascript"></script>';
    }
    for (i = 0; i < AllCss.length; i++) {
        strCss += ' <link href="' + AllCss[i] + '" rel="stylesheet" type="text/css"  media="all"/>';
    }
    document.write(strJs + strCss);
    window.FontDocument = document;

    //IE11不支持append，故不用这段代码
    //var ReadyJs = [
    //   thisJavaScriptUrl + "controller.js?t=1",
    //]
    //window.onload = function () {
    //    var strJs = "";
    //    for (i = 0; i < ReadyJs.length; i++) {
    //        var scripts = document.createElement("script");
    //        scripts.src = ReadyJs[i];
    //        document.body.append(scripts);
    //    }
    //};
})();



