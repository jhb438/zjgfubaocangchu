


$(function () {

    //初始化页面下拉框
    $("select[data-codename]").each(function () {
        var codeName = $(this).attr("data-codename");//代码项名称
        var id = $(this).attr("id");
        var showOption = $(this).attr("data-showOption");//默认显示文本
        var dataEvent = $(this).attr("data-event");//其他事件名称

        if (dataEvent == undefined) {
            var par = {};
            par['codeName'] = codeName;
            SendAjax("/sys/codeValue/getCodeValueToMap", par, function (res) {

                if (res.code == 0) {
                    var strLi = "";
                    if (showOption != "") {
                        strLi += "<option value=\"\">" + showOption + "</option>";
                    }
                    $.each(res.data, function (value, text) {
                        strLi += "<option value=\"" + text + "\">" + value + "</option>";
                    })

                    $("#" + id).html(strLi);
                }
            });
          
        }
        else {
            var url;
            var par = {};
            if (dataEvent == "getDeptList") {
                //集群对讲固定频道
                url = "/sys/dept/getDeptList";
            }
            else if(dataEvent=="getChannelList")
            {
                //集群对讲临时频道
                url = "sys/jttempchannel/getChannelList";
            
            }
            else
            {

            }
         
            SendAjax(url, par, function (res) {

                if (res.code == 0) {
                    var strLi = "";
                    if (showOption != "") {
                        strLi += "<option value=\"\">" + showOption + "</option>";
                    }
                    $.each(res.data, function (i, item) {
                        strLi += "<option value=\"" + item.itemValue + "\">" + item.itemText + "</option>";
                    })
                   
                    $("#" + id).append(strLi);
                  
                }
            });

        }

    });


    KindEditor.ready(function (K) {
        $('textarea[data-kinder-editor]').each(function () {
            var options = {
                uploadJson: '/sys/uploadJson',
                fileManagerJson: '/sys/fileManagerJson',
                allowFileManager: true,
                afterCreate: function () { this.sync(); },
                afterBlur: function () { this.sync(); }
            };

            var web_url = location.protocol + '\\\\' + location.hostname + ':' + (location.port == '' ? 80 : location.port);
            var thisObj = {};
            thisObj["width"] = "100%";
            thisObj["height"] = "250px";
            if ($(this).attr("width")) {
                thisObj["width"] = $(this).attr("width") + "px";
            }
            if ($(this).attr("height")) {
                thisObj["height"] = $(this).attr("height") + "px";
            }

            var kindobj = $.extend({}, options, thisObj);
            kindobj.uploadJson = web_url + kindobj.uploadJson;
            kindobj.fileManagerJson = web_url + kindobj.fileManagerJson;
            KindEditor.create('#' + $(this).attr("id"), kindobj);

        })
    });
})


