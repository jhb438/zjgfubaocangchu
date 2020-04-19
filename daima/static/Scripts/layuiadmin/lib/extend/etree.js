
layui.define(['jquery'], function (exports) { //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
    var $ = layui.jquery,
        MOD_NAME = 'etree'
        , MainCss = '.layui-ztree-content {display: none; position: absolute;left: 0;top: 42px; padding: 5px 0;z-index: 800;min-width: 100%;border: 1px solid #d2d2d2;min-height: 150px;max-height: 250px;overflow-y: auto;background-color: #fff;border-radius: 2px;box-shadow: 0 2px 4px rgba(0, 0, 0, .12);box-sizing: border-box;}'
        , Class = function (options) {
            var that = this;
            that.index = ++etree.index;
            if (options.type === "radio") {
                that.config = $.extend({}, that.radioConfig, options);
            } else {
                that.config = $.extend({}, that.checkboxConfig, options);
            }
            that.render();
        }
        //表格渲染
        , etree = {
            cache: {} //数据缓存
            , index: layui.etree ? (layui.etree.index + 10000) : 0
        }
        //操作当前实例
        , thisetree = function () {
            var that = this
                , options = that.config
                , id = options.id || options.index;

            if (id) {
                thisetree.that[id] = that; //记录当前实例对象
                thisetree.config[id] = options; //记录当前实例配置项
            }
            return {
                config: options
            };
        }
        , getthisetree = function (id) {
            var that = thisetree.that[id];
            return that || null;
        };
    thisetree.that = {}; //记录所有实例对象
    thisetree.config = {}; //记录所有实例配置项

    //多选树配置
    Class.prototype.checkboxConfig = {
        setting: {
            check: {
                enable: true,
                chkboxType: { "Y": "", "N": "" }
            },
            callback: {
				onClick:function(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		},
                onCheck: function (e, treeId, treeNode) {
                    var zTree = $.fn.zTree.getZTreeObj(treeId);
                    etree.initData(zTree, treeId);
                }
            }
        }
    };

    //单选树配置
    Class.prototype.radioConfig = {
        setting: {
            check: {
                enable: true,
                chkStyle: "radio",
                radioType: "all"
            },
            callback: {
				onClick:function(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.checkNode(treeNode, !treeNode.checked, null, true);
			return false;
		},
                onCheck: function (e, treeId, treeNode) {
                    var zTree = $.fn.zTree.getZTreeObj(treeId);
                    etree.initData(zTree, treeId);
                }
            }
        }
    };

    //弹出下拉菜单
    Class.prototype.showMenu = function () {
        var that = this;
        that.reElem.addClass('layui-form-selected');
        that.layBox.slideDown("fast");
        $(document).bind("mousedown", function () { that.onBodyDown.call(that); });
    };
    //隐藏下拉菜单
    Class.prototype.hideMenu = function () {
        var that = this;
        that.reElem.removeClass('layui-form-selected');
        that.layBox.fadeOut("fast");
        $(document).unbind("mousedown", function () { that.onBodyDown.call(that); });
    };
    //点击周边隐藏下拉菜单
    Class.prototype.onBodyDown = function () {
        var that = this;
        if (!($(event.target).parents(".layui-form-select-" + that.index).length > 0 )) {
            that.hideMenu.call(that);
        }
    };
    Class.prototype.render = function () {
        var that = this
            , options = that.config;
        options.elem = $(options.elem);
        options.id = options.id || options.elem.attr('id') || that.index;

        options.elem.hide();

        var placeholder = options.elem.attr('placeholder');

        if (!options.elem[0]) return that;
        $("body").append($('<style ztree>').append(MainCss));
        //开始插入替代元素
        var othis = options.elem
            , hasRender = othis.next('.layui-form-select')

            , reElem = $(['<div class="layui-form-select layui-form-select-' + that.index +'">'//最外面一层容器
                , '<div class="layui-select-title">'
                , '<input type="text" placeholder="' + placeholder + '"  readonly class="layui-input">'
                , '<i class="layui-edge"></i></div>'
                , '<div class="layui-ztree-content">'
                , '<ul id="ztree-ul-' + options.id + '" class="ztree" ></ul>'
                , '</div>'].join(''));

        hasRender[0] && hasRender.remove(); //如果已经渲染，则Rerender
        othis.after(reElem);


        //各级容器
        that.reElem = reElem;
        that.layValue = othis;
        that.layText = reElem.find(".layui-input");
        that.layBox = reElem.find(".layui-ztree-content");
        that.layUl = reElem.find(".ztree");

        that.pullData(); //请求数据
        that.events(); //事件
    };

    //事件处理
    Class.prototype.events = function () {
        var that = this;
        //工具栏操作事件
        that.layText.on('click', function (e) {
            that.showMenu();
        });
    };
    //获得数据
    Class.prototype.pullData = function () {
        var that = this
            , options = that.config
            , request = options.request
            , response = options.response;

        that.startTime = new Date().getTime(); //渲染开始时间

        if (options.url) { //Ajax请求
            var params = {};
            //参数
            var data = $.extend(params, options.where);
            if (options.contentType && options.contentType.indexOf("application/json") === 0) { //提交 json 格式
                data = JSON.stringify(data);
            }

            $.ajax({
                type: options.method || 'get'
                , url: options.url
                , contentType: options.contentType
                , data: data
                , dataType: 'json'
                , headers: options.headers || {}
                , success: function (res) {
                    //如果有数据解析的回调，则获得其返回的数据
                    if (typeof options.parseData === 'function') {
                        res = options.parseData(res) || res;
                    }
                    zTree = $.fn.zTree.init(that.layUl, options.setting, res);

                    etree.initData(zTree, options.id);

                    typeof options.done === 'function' && options.done(res);
                }
                , error: function (e, m) {
                    alert('数据接口请求异常：' + m);
                }
            });
        }
    };
    //初始化选中值
    etree.initData = function (zTree, treeId) {
        var that = getthisetree(treeId.replace("ztree-ul-", ""));

        nodes = zTree.getCheckedNodes(true),
            text = "", val = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            text += nodes[i].name + ",";
            val += nodes[i].value + ",";
        }
        if (text.length > 0) text = text.substring(0, text.length - 1);
        if (val.length > 0) val = val.substring(0, val.length - 1);
        that.layValue.val(val);
        that.layText.val(text);
    };
    etree.render = function (options) {
        var inst = new Class(options);
        return thisetree.call(inst);
    };
    exports(MOD_NAME, etree);
});    