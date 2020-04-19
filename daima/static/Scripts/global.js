window.serverUrl = location.protocol + '\\\\' + location.hostname + ':' + (location.port == '' ? 80 : location.port) + "/zjgfbcc";
window.fileUrl = window.serverUrl + "/file/";



$(function () {
    var list = $(".layui-form");
    var list2 = $(".layui-input.treeInput");

    for (var i = 0; i < list.length; i++) {
        list[i].setAttribute('action', window.serverUrl + list[i].getAttribute('action'))
    }

    for (var j = 0; j < list2.length; j++) {
        list2[j].setAttribute('etree-url', window.serverUrl + list2[j].getAttribute('etree-url'))
    }
})

layui.config({
      version: true,
      base: "../../Scripts/layuiadmin/" //静态资源所在路径
  })
  .extend({
      index: "lib/index" //主入口模块
  })
  .use(["index", "form", "laydate", "sample"], function () {
      var $ = layui.$,
        admin = layui.admin,
        element = layui.element,
        layer = layui.layer,
        laydate = layui.laydate,
        form = layui.form;

      layui.form.render(null, "layform");

      //lay(".date-input").each(function() {
      //  laydate.render({
      //    elem: this,
      //    format: "yyyy-MM-dd",
      //    trigger: "click"
      //  });
      //});

      //初始化日期格式
      //laydate.render({
      //    elem: '.layuitime'
      //});
  });

//为所有 AJAX 请求设置默认 headers 和 complete 函数：
$.ajaxSetup({
    headers: {
      
        "X-Auth-Token": window.localStorage.getItem("m_token")
    },
    complete: function (res) {
    	if (isJSON(res.responseText) && JSON.parse(res.responseText).code == "401") {
	        window.top.location.href = window.serverUrl+"/static/login.html";
	    }
    }
});

function isJSON(str) {
    if (typeof str == 'string') {
        try {
            JSON.parse(str);
            return true;
        } catch(e) {
            return false;
        }
    }
}

function SendAjax(url, responseData, successFunction, async, type) {
    var boolasync = false;
    
    if(/^serial=.*$/g.test('http')||/^serial=.*$/g.test('https')){
    	
    }else{
    	url = window.serverUrl + url
    }
    
    if (async != undefined) {
        boolasync = async;
    }

    var booltype = "POST";
    if (type != undefined) {
        booltype = type;
    }
    //var obj = {
    //    createUserGuid: window.localStorage.getItem('m_user_rowGuid'),
    //    createDeptGuid: window.localStorage.getItem('m_deptGuid'),
    //    createDeptName: window.localStorage.getItem('m_deptName'),
    //    createUserName: window.localStorage.getItem('m_user_userName'),

    //}
    //if (typeof a == "string") {
    //    for (var i = 0; i < a.split('&').length; i++) {
    //        var e = a.split('&')[i].split('=');
    //        obj[e[0]] = e[1];
    //    }
    //}
    //else {
    //    obj = $.extend({}, obj, a);
    //}


    $.ajax({
        type: booltype,
        url: url,
        async: boolasync,
        dataType: "JSON",
        data: responseData,
        success: function (params, data) {
            successFunction.call(successFunction, data, params);
        }.bind(null, responseData),
        error: function (XMLHttpRequest, textStatus, errorThrown) { }
    });
}

function Session(Name, Value) {
    if (Value == undefined) {
        if (localStorage.getItem(Name) === undefined || localStorage.getItem(Name) == null) {
            localStorage.setItem(Name, "");
        }
        return localStorage.getItem(Name);
    }
    else {
        localStorage.setItem(Name, Value);
    }
}
//删除变量
function removeSession(Name) {
    localStorage.removeItem(Name);
}

//解析url路径,获取参数
function getURLParameter(name) {
    return (
      decodeURIComponent(
        (new RegExp("[?|&]" + name + "=" + "([^&;]+?)(&|#|;|$)").exec(
          location.search
        ) || [, ""])[1].replace(/\+/g, "%20")
      ) || ""
    );
}

function GuidGenerator() {
    var S4 = function () {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    };

    return (
      S4() +
      S4() +
      "-" +
      S4() +
      "-" +
      S4() +
      "-" +
      S4() +
      "-" +
      S4() +
      S4() +
      S4()
    );
}

