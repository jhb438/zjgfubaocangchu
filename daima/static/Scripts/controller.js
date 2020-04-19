
/***多文件上传控件专用****/
var jsList = document.scripts;
var thisJavaScriptUrl = jsList[jsList.length - 1].src.substring(0, jsList[jsList.length - 1].src.lastIndexOf("/") + 1);

$(function () {
    $("input[data-uploader]").each(function () {
        var divParent = document.createElement("div");
        divParent.className = "upload_div upload_up_div";
        this.parentNode.insertBefore(divParent, this.nextElementSibling);
        var attrs = this.attributes;
        var HtmlAttributes = {};
        for (var indexAttr = 0 ; indexAttr < attrs.length; indexAttr++) {
            if (attrs[indexAttr].name !== "data-uploader") {
                HtmlAttributes[attrs[indexAttr].name] = attrs[indexAttr].value;
            }
        }
        for (var at in HtmlAttributes) {
            this.attributes.removeNamedItem(at);
        }
        var id = HtmlAttributes["id"];
        var name = HtmlAttributes["name"];
        if (!HtmlAttributes["name"]) {
            name = id;
        }
        var value = this.value;
        if (!value) {
            value = GuidGenerator(); //随机guid
        }
        this.parentNode.removeChild(this);
        var weburl = thisJavaScriptUrl;
        //input file的上传控件
        var success = "";//成功回调时间
        var complete = "";//描述
        var type = "edit";//描述
        var savetype = "stream";//描述
        var init = "";//描述
        var showlist = "";//描述
        var error = "";//描述
        var isMulti = "true";//是否多选
        var fileTypeDesc = "";//描述
        var fileTypeExts = "";//描述
        var buttonText = "";//描述
        var width = "";//描述
        var height = "";//描述
        var uploader = "";
        var showlisturl = "";
        var deleteurl = "";
        var showurl = "";
        var downloadurl = "";
        var filesizelimit = "";
        var auto = "true";//是否自动上传
        var select = "";//是否自动上传
        var onSelectOnce = "";
        var onUploadFileFinished = "";
        var uploadprogress = "";
        var asbutton = "";
        var style = "";
        var html = "";
        var download = "";
        if (HtmlAttributes["init"]) {
            init = HtmlAttributes["init"];
        }
        if (HtmlAttributes["showlist"]) {
            showlist = HtmlAttributes["showlist"];
        }
        if (HtmlAttributes["error"]) {
            error = HtmlAttributes["error"];
        }
        if (HtmlAttributes["success"]) {
            success = HtmlAttributes["success"];
        }
        if (HtmlAttributes["multi"]) {
            isMulti = HtmlAttributes["multi"];
        }
        if (HtmlAttributes["filetypedesc"]) {
            fileTypeDesc = HtmlAttributes["filetypedesc"];
        }
        if (HtmlAttributes["filetypeexts"]) {
            fileTypeExts = HtmlAttributes["filetypeexts"];
        }
        if (HtmlAttributes["buttontext"]) {
            buttonText = HtmlAttributes["buttontext"];
        }
        if (HtmlAttributes["width"]) {
            width = HtmlAttributes["width"];
        }
        if (HtmlAttributes["height"]) {
            height = HtmlAttributes["height"];
        }
        if (HtmlAttributes["complete"]) {
            complete = HtmlAttributes["complete"];
        }
        if (HtmlAttributes["mode"]) {
            type = HtmlAttributes["mode"];
        }
        if (HtmlAttributes["savemode"]) {
            savetype = HtmlAttributes["savemode"];
        }
        if (HtmlAttributes["uploader"]) {
            uploader = HtmlAttributes["uploader"];
        }

        if (HtmlAttributes["listurl"]) {
            showlisturl = HtmlAttributes["listurl"];
        }
        if (HtmlAttributes["deleteurl"]) {
            deleteurl = HtmlAttributes["deleteurl"];
        }
        if (HtmlAttributes["showurl"]) {
            showurl = HtmlAttributes["showurl"];
        }
        if (HtmlAttributes["downloadurl"]) {
            downloadurl = HtmlAttributes["downloadurl"];
        }
        if (HtmlAttributes["filesizelimit"]) {
            filesizelimit = HtmlAttributes["filesizelimit"];
        }
        if (HtmlAttributes["auto"]) {
            auto = HtmlAttributes["auto"];
        }
        if (HtmlAttributes["select"]) {
            select = HtmlAttributes["select"];
        }
        if (HtmlAttributes["dialogclose"]) {
            onSelectOnce = HtmlAttributes["dialogclose"];
        }
        if (HtmlAttributes["uploadprogress"]) {
            uploadprogress = HtmlAttributes["uploadprogress"];
        }
        if (HtmlAttributes["uploadfinished"]) {
            onUploadFileFinished = HtmlAttributes["uploadfinished"];
        }

        if (HtmlAttributes["asbutton"]) {
            asbutton = HtmlAttributes["asbutton"].toLocaleLowerCase();
        }
        if (HtmlAttributes["style"]) {
            style = HtmlAttributes["style"];
        }
        if (HtmlAttributes["html"]) {
            html = HtmlAttributes["html"];
        }
        if (HtmlAttributes["download"]) {
            download = HtmlAttributes["download"];
        }
        HtmlAttributes["type"] = "file";
        HtmlAttributes["name"] = name + "_file";
        HtmlAttributes["data-web-url"] = weburl;
        HtmlAttributes["data-upload-success"] = success;
        HtmlAttributes["data-upload-init"] = success;
        HtmlAttributes["data-upload-error"] = error;
        HtmlAttributes["data-upload-complete"] = complete;
        HtmlAttributes["data-upload-multi"] = isMulti;
        HtmlAttributes["data-upload-filetypedesc"] = fileTypeDesc;
        HtmlAttributes["data-upload-filetypeexts"] = fileTypeExts;
        HtmlAttributes["data-upload-buttontext"] = buttonText;
        HtmlAttributes["data-upload-width"] = width;
        HtmlAttributes["data-upload-height"] = height;
        HtmlAttributes["data-upload-mode"] = type;
        HtmlAttributes["data-upload-savemode"] = savetype;
        HtmlAttributes["data-upload-uploader"] = uploader;
        HtmlAttributes["data-upload-listurl"] = showlisturl;
        HtmlAttributes["data-upload-deleteurl"] = deleteurl;
        HtmlAttributes["data-upload-showurl"] = showurl;
        HtmlAttributes["data-upload-downloadurl"] = downloadurl;
        HtmlAttributes["data-upload-filesizelimit"] = filesizelimit;
        HtmlAttributes["data-upload-auto"] = auto;
        HtmlAttributes["data-upload-select"] = select;
        HtmlAttributes["data-upload-selectonce"] = onSelectOnce;
        HtmlAttributes["data-upload-uploadprogress"] = uploadprogress;
        HtmlAttributes["data-upload-asbutton"] = asbutton;
        HtmlAttributes["data-upload-style"] = style;
        HtmlAttributes["data-upload-download"] = download;
        HtmlAttributes["data-upload-uploadfilefinished"] = onUploadFileFinished;

        var divHidden = document.createElement("div");
        for (var itemAttr in HtmlAttributes) {
            divHidden.setAttribute(itemAttr, HtmlAttributes[itemAttr]);
        }
        HtmlAttributes["id"] = id + "_file";
        var inputFileHidden = document.createElement("input");
        for (var itemAttr in HtmlAttributes) {
            inputFileHidden.setAttribute(itemAttr, HtmlAttributes[itemAttr]);
        }
        divParent.append(inputFileHidden);

        var divBtnsHidden = document.createElement("div");
        divBtnsHidden.className = "upload_btn";
        divBtnsHidden.append(divHidden);
        divParent.append(divBtnsHidden);

        //input hidden的存储guid控件
        HtmlAttributes = {};
        HtmlAttributes["type"] = "hidden";
        HtmlAttributes["id"] = id;
        HtmlAttributes["name"] = name;
        HtmlAttributes["data-name"] = id + "_file";
        HtmlAttributes["value"] = value;
        HtmlAttributes["data-web-url"] = weburl;

        var tagHtml = "";
        for (var itemAttr in HtmlAttributes) {
            tagHtml += " " + itemAttr + "=\"" + HtmlAttributes[itemAttr] + "\"";
        }
        var inputHidden = document.createElement("input");
        for (var itemAttr in HtmlAttributes) {
            inputHidden.setAttribute(itemAttr, HtmlAttributes[itemAttr]);
        }
        divParent.append(inputHidden);

        HtmlAttributes = {};
        HtmlAttributes["class"] = "upload_panel";
        HtmlAttributes["id"] = id + "_upload_panel";
        HtmlAttributes["data-upload-id"] = value;
        HtmlAttributes["data-upload-showlist"] = showlist;
        HtmlAttributes["data-upload-mode"] = type;
        HtmlAttributes["data-upload-savemode"] = savetype;
        HtmlAttributes["data-web-url"] = weburl;
        HtmlAttributes["data-upload-filetypedesc"] = fileTypeDesc;
        HtmlAttributes["data-upload-filetypeexts"] = fileTypeExts;
        HtmlAttributes["data-upload-buttontext"] = buttonText;
        HtmlAttributes["data-upload-width"] = width;
        HtmlAttributes["data-upload-height"] = height;
        HtmlAttributes["data-upload-uploader"] = uploader;
        HtmlAttributes["data-upload-listurl"] = showlisturl;
        HtmlAttributes["data-upload-deleteurl"] = deleteurl;
        HtmlAttributes["data-upload-showurl"] = showurl;
        HtmlAttributes["data-upload-downloadurl"] = downloadurl;
        HtmlAttributes["data-upload-filesizelimit"] = filesizelimit;
        HtmlAttributes["data-upload-auto"] = auto;

        var divPanel = document.createElement("div");
        for (var itemAttr in HtmlAttributes) {
            divPanel.setAttribute(itemAttr, HtmlAttributes[itemAttr]);
        }
        divParent.append(divPanel);

        var obj = {
            buttonText: '选择文件',
            width: 120,
            height: 30,
            fileTypeDesc: '所有文件',
            fileTypeExts: '*',
            swf: '../Content/uploadify/uploadify.swf',
            webswf: 'webuploader/Uploader.swf',
            uploader: '../../sys/attach/uploadFile',
            removeTimeout: 0,
            successTimeout: 120,
            fileSizeLimit: '1GB',
            auto: true,
            multi: true,
            onQueueComplete: function (filePanel, response) {
            },
            onUploadSuccess: function (filePanel, file, data, response) {

                data = eval("[" + data + "]")[0];
                if ($(filePanel).find('div[class="layui-upload-list"]').length == 0) {
                    $(filePanel).append('<div class="layui-upload-list"><table class="layui-table" ><tbody id="demoList"></tbody></table ></div >');
                }

                var tr = $(['<tr lay-attachid="' + data.rowId + '">'
                    , '<td>' + data.fileName + '<br/><span style="color: #888888;font- size:12px;">' + data.createTime + '</span></td>'
                    , '<td style="width:80px;text-align:center">' + (data.contentLength / 1024).toFixed(1) + 'Kb</td>'
                    , '<td style="width:90px">'
                    , '<a target="_blank" class="layui-btn layui-btn-xs layui-btn-normal" href="' + data.url + '">下载</a>'
                    , '<a class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</a>'
                    , '</td>'
                    , '</tr>'].join(''));

                //删除
                tr.find('.demo-delete').on('click', function () {
                    deleteAttach(data.attachRowguid, tr);
                });

                $(filePanel).find("#demoList").append(tr);



            },
            onUploadError: function (file, errorCode, errorMsg, errorString) {
                alert('文件上传失败，错误代码：' + errorMsg);
            }
        }
        var web_url = $(inputFileHidden).attr("data-web-url");
        var thisObj = obj;
        for (var item in thisObj) {
            if ($(inputFileHidden).attr(item) != "" && $(inputFileHidden).attr(item) != null) {
                thisObj[item] = $(inputFileHidden).attr(item);
            }
        }
        if ($(inputFileHidden).attr("data-upload-success") != "") {
            thisObj.onUploadSuccess = window[$(inputFileHidden).attr("data-upload-success")];
        }
        if ($(inputFileHidden).attr("data-upload-complete") != "") {
            thisObj.onQueueComplete = window[$(inputFileHidden).attr("data-upload-complete")];
        }
        if ($(inputFileHidden).attr("data-upload-buttontext") != "") {
            thisObj.buttonText = $(inputFileHidden).attr("data-upload-buttontext");
        }
        if ($(inputFileHidden).attr("data-upload-filetypedesc") != "") {
            thisObj.fileTypeDesc = $(inputFileHidden).attr("data-upload-filetypedesc");
        }
        if ($(inputFileHidden).attr("data-upload-filetypeexts") != "") {
            thisObj.fileTypeExts = $(inputFileHidden).attr("data-upload-filetypeexts");
        }
        if ($(inputFileHidden).attr("data-upload-multi") != "") {
            thisObj.multi = ($(inputFileHidden).attr("data-upload-multi") == "true");
        }
        if ($(inputFileHidden).attr("data-upload-uploader") != "") {
            thisObj.uploader = $(inputFileHidden).attr("data-upload-uploader");
        }
        if ($(inputFileHidden).attr("data-upload-filesizelimit") != "") {
            thisObj.fileSizeLimit = $(inputFileHidden).attr("data-upload-filesizelimit");
        }
        if ($(inputFileHidden).attr("data-upload-width") != "") {
            thisObj.width = parseInt($(inputFileHidden).attr("data-upload-width"));
        }
        if ($(inputFileHidden).attr("data-upload-height") != "") {
            thisObj.height = parseInt($(inputFileHidden).attr("data-upload-height"));
        }
        if ($(inputFileHidden).attr("data-upload-auto") != "") {
            thisObj.auto = ($(inputFileHidden).attr("data-upload-auto") == "true");
        }
        thisObj.swf = web_url + thisObj.swf;
        thisObj.uploader = web_url + thisObj.uploader + (thisObj.uploader.indexOf("?") < 0 ? "?" : "&") +
            "formRowGuid=" + $(inputFileHidden).closest("div").find("input[type='hidden']").val();
        thisObj.onUploadSuccess = thisObj.onUploadSuccess.bind(null, $(inputFileHidden).closest("div").find(".upload_panel"));
        thisObj.onQueueComplete = thisObj.onQueueComplete.bind(null, $(inputFileHidden).closest("div").find(".upload_panel"));

        if ($(inputFileHidden).attr("data-upload-mode") == "view") {
            $(inputFileHidden).hide();
        }
        else {
            $(inputFileHidden).uploadify(thisObj);
        }

    })


    $("div[data-upload-id]").each(function () {
        if ($(this).attr("data-upload-showlist") != "") {
            window[$(this).attr("data-upload-showlist")].
                call(window[$(this).attr("data-upload-showlist")], $(this), $(this).attr("data-upload-id"));
        }
        else {
            newShowUploadItems($(this), $(this).attr("data-upload-id"));
        }
    })

})

//删除附件
function deleteAttach(attachRowguid, tr) {
    var guids = new Array;
    guids.push(attachRowguid)
    $.post(window.serverUrl + '/sys/frameAttach/delete', { attachRowguid: guids }, function (data) {
        if (data.code == "0") {
            $(tr).remove();
        } else {
            alert("删除失败")
        }
    })
}

function newShowUploadItems(div, formRowGuid) {
    var style = "";
    if ($(div).attr("data-upload-mode") == "view") {
        style = "style='display:none;'";
    }
    $.post(window.serverUrl + '/sys/frameAttach/getAttachList', { "guid": formRowGuid }, function (data) {

        $.each(data.data, function (index, item) {

            var tr = $(['<tr id="upload-' + index + '" lay-attachid="' + item.rowId + '">'
                , '<td>' + item.attachName + '<br/><span style="color: #888888;font- size:12px;">' + item.createTime + '</span></td>'
                , '<td style="width:80px;text-align:center">' + (item.contentLength / 1024).toFixed(1) + 'Kb</td>'
                , '<td style="width:90px">'
                , '<a target="_blank" class="layui-btn layui-btn-xs layui-btn-normal" href="' + item.url + '">下载</a>'
                , '<a class="layui-btn layui-btn-xs layui-btn-danger demo-delete" ' + style + '>删除</a>'
                , '</td>'
                , '</tr>'].join(''));
            //删除
            tr.find('.demo-delete').on('click', function () {
                deleteAttach(item.rowGuid, tr)
            });
            if ($(div).find('div[class="layui-upload-list"]').length == 0) {
                $(div).append('<div class="layui-upload-list"><table class="layui-table" ><tbody id="demoList"></tbody></table ></div >');
            }

            $(div).find("#demoList").append(tr);
        })
    })

}
