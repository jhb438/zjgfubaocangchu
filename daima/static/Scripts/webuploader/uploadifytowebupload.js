var webuploads = {
    webupload_index: 0
};
var webupload = {
    defaultObj: {
        swf: '',
        // 文件接收服务端。
        server: '',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '',
        fileVal: 'uploadify',
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    },

    methods: {
        init: function (option) {
            var defaultObj = webuploads[$(this).attr("id")].defaultObj;
            defaultObj = $.extend({}, defaultObj, option);
            defaultObj.swf = option.webswf;
            defaultObj.server = option.uploader;
            defaultObj.formData = option.scriptData;
            defaultObj.pick = {
                id: "#" + $(this).attr("id"),
                innerHTML: (defaultObj.buttonText == "" ? "&nbsp;" : defaultObj.buttonText),
                multiple: (defaultObj.multi) ? true : false
            };
            defaultObj.accept = {
                title: option.fileTypeDesc,
                extensions: option.fileTypeExts
            };
            if (typeof (defaultObj.fileSizeLimit) == "string") {
                if (defaultObj.fileSizeLimit.toUpperCase().indexOf("GB") > -1) {
                    defaultObj.fileSizeLimit = parseInt(defaultObj.fileSizeLimit.toUpperCase().replace("GB", "")) * 1024 * 1024 * 1024
                }
                else if (defaultObj.fileSizeLimit.toUpperCase().indexOf("MB") > -1) {
                    defaultObj.fileSizeLimit = parseInt(defaultObj.fileSizeLimit.toUpperCase().replace("MB", "")) * 1024 * 1024
                }
                else if (defaultObj.fileSizeLimit.toUpperCase().indexOf("KB") > -1) {
                    defaultObj.fileSizeLimit = parseInt(defaultObj.fileSizeLimit.toUpperCase().replace("KB", "")) * 1024
                }
                else {
                    defaultObj.fileSizeLimit = parseInt(defaultObj.fileSizeLimit.toUpperCase().replace("B", ""))
                }
            }
            webuploads[$(this).attr("id")].defaultObj = defaultObj;
            var onUploadProgress = defaultObj.onUploadProgress;
            var onUploadSuccess = defaultObj.onUploadSuccess;
            defaultObj.onUploadProgress = undefined;
            defaultObj.onUploadSuccess = undefined;
            var uploader = WebUploader.create(defaultObj); 

            uploader.on('fileQueued', (function (defaultObj, div, id, file) {
                webuploads[id]["files"].push(file);
                if (defaultObj.onSelect) {
                    defaultObj.onSelect.call(defaultObj.onSelect, file, id)
                } 
            }).bind(null, defaultObj, $(this).closest(".upload_div").find(".upload_panel"), $(this).attr("id")));

            uploader.on('filesQueued', (function (func, dialog, div, id, files) {  
                if (dialog != undefined) {
                    dialog.call(dialog, { size: files.length }, files, div, id)
                }
            }).bind(null, defaultObj.onSelect, defaultObj.onDialogClose, $(this).closest(".upload_div").find(".upload_panel"), $(this).attr("id")));

            uploader.on('uploadProgress', (function (func, div, id, file, percentage) {
                var bytesUploaded = file.size * percentage;
                var bytesTotal = file.size;
                if (func) {
                    func.call(func, file, file.size * percentage, file.size, file.size * percentage, file.size)
                } 
            }).bind(null, onUploadProgress, $(this).closest(".upload_div").find(".upload_panel"), $(this).attr("id")));
            uploader.on('uploadSuccess', (function (func, file, response) {
                if (func) {
                    func.call(func, file, JSON.stringify(response), response);
                }
            }).bind(null, onUploadSuccess));

            uploader.on('uploadError', (function (func, file) {
                if (func) {
                    func.call(func, file);
                }
            }).bind(null, defaultObj.onSelect));

            uploader.on('uploadFinished', (function (func, id) {
                webuploads[id]["files"] = [];
                $("#" + id + "").uploadify('reset');
                if (func) {
                    func.call(func, id);
                }
            }).bind(null, defaultObj.onUploadFileFinished, $(this).attr("id")));
            uploader.on('uploadBeforeSend', function (obj, data, headers) {
                headers = $.extend(headers, {
                    "token": window.localStorage.getItem('m_token')
                });
            });
            uploader.on('uploadComplete', (function (func, id, file) {
                $.each(webuploads[id]["files"], function (index, item) {
                    if (item.id == file.id) {
                        webuploads[id]["files"].remove(index);
                        return false;
                    }
                })

                if (func) {
                    func.call(func, file, id);
                }
            }).bind(null, defaultObj.onQueueComplete, $(this).attr("id"))); 
            $(this).closest(".upload_div").attr("style", $(this).attr("data-upload-style"));
            $(this).closest(".upload_div").attr("class", $(this).closest(".upload_div").attr("class") + " "
                + $(this).attr("data-upload-class"));
            $(this).closest(".upload_div").removeClass("upload_up_div");
            $(this).closest(".upload_div").next().remove();
            webuploads[$(this).attr("id")]["uploader"] = uploader;
            webuploads[$(this).attr("id")]["files"] = [];
        },
        option: function (name, value) {
            webuploads[$(this).attr("id")].uploader.option(name, value);
        },
        upload: function (file) {
            if (file == undefined) {
                webuploads[$(this).attr("id")].uploader.upload();
            }
            else {
                webuploads[$(this).attr("id")].uploader.upload(file);
            }
        },
        stop: function (file) {
            if (file == undefined) {
                webuploads[$(this).attr("id")].uploader.stop();
            }
            else {
                webuploads[$(this).attr("id")].uploader.stop(file);
            }
        },
        cancelFile: function (obj) {
            webuploads[$(this).attr("id")].uploader.cancelFile(obj);
        },
        reset: function () {
            webuploads[$(this).attr("id")].uploader.reset();
        },
        resize: function () { 
            var divB = $(this).find("input[type='file']").parent();
            var showBtn = divB.prev();
            var width = showBtn.width() + parseInt(showBtn.css("padding-left").replace("px", ""))
                + parseInt(showBtn.css("padding-right").replace("px", "")) + parseInt(showBtn.css("border-left-width").replace("px", ""))
                + parseInt(showBtn.css("border-right-width").replace("px", ""));

            var height = showBtn.width() + parseInt(showBtn.css("padding-top").replace("px", ""))
                + parseInt(showBtn.css("padding-bottom").replace("px", "")) - parseInt(showBtn.css("border-top-width").replace("px", ""))
                - parseInt(showBtn.css("border-bottom-width").replace("px", ""));
            divB.width(width);
            divB.height(height);
        },
        files: function () {  
            return webuploads[$(this).attr("id")]["files"];
        },
        hide: function () {
            if ($(this).closest(".upload_div").attr("data-position") == null) {
                $(this).closest(".upload_div").attr("data-position", $(this).closest(".upload_div").css("position"))
                    .attr("data-top", $(this).closest(".upload_div").css("top"))
                    .attr("data-z-index", $(this).closest(".upload_div").css("z-index"));
            }
            $(this).closest(".upload_div").css({ position: "absolute", top: -$(window).height() + "px", "z-index": "20000000" })
        },
        show: function () {
            $(this).closest(".upload_div").css({
                position: $(this).closest(".upload_div").attr("data-position"),
                top: $(this).closest(".upload_div").attr("data-top"),
                "z-index": $(this).closest(".upload_div").attr("data-z-index")
            })
        }
    }
}

$.fn["uploadify"] = function (method) {
    var element = $(this);
    if ($(this).attr("type") == "hidden") {
        element = $("#" + $(this).attr("data-name"));
    }
    if (element.attr("id") == undefined) {
        element.attr("id", "webupload_" + webuploads.webupload_index);
        webuploads.webupload_index++;
    }
    var id = element.attr("id");
    if (webuploads[id] !== undefined && (method !== undefined) && webuploads[id].methods[method]) {
        return webuploads[id].methods[method].apply(element.get(0), Array.prototype.slice.call(arguments, 1));
    } else if (typeof method === "object" || !method) {
        if (method == undefined) {
            return webuploads[id]["uploader"];
        }
        var name = $("#" + id).attr("name");
        $div = $("#" + id).closest("div");
        $("#" + id).remove();
        $div.find("div[name='" + name + "']").attr("id", id);

        webuploads[id] = clone(webupload);
        webuploads[id].methods.init.apply(document.getElementById(id), arguments);
        return webuploads[id];
    }else {
        //$.error("Method " + method + " does not exist");
    }
};

function showFileSize(value) {
    if (value > 1024 * 1024 * 1024) {
        return (value / 1024 / 1024 / 1024).toFixed(2) + "GB";
    } if (value > 1024 * 1024) {
        return (value / 1024 / 1024).toFixed(2) + "MB";
    } else if (value > 1024) {
        return (value / 1024).toFixed(2) + "KB";
    }
    return value + "B";
}

function uploadImageError(img) { 
    $(img).attr("src", getRootPath() + "/Content/images/uploadico/txt.png");
}