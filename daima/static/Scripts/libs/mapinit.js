var map;

var hotTileId = null;           //当前热区ID
var hotTileName = null;         //当前热区名称
var hotTileLayer = null;  

//地图初始化
var projection = ol.proj.get('EPSG:4326');//设置坐标系
var projectionExtent = projection.getExtent();
var resolutions = [];
for (var i = 0; i < 21; i++) {
    resolutions[i] = 1.40625 / Math.pow(2, i);
}
var radiusCorn = null;
//瓦片矩阵
var matrixIds = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20];
var centerXY = "120.568524,31.844056";//地图中心点，张家港市交通局
var center = ReturnCenter(centerXY);
//map = new ol.Map({
//    controls: ol.control.defaults({
//        attribution: false
//    }).extend([
//        new ol.control.MousePosition()//是否显示鼠标所在地图点的经纬度
//    ]),
//    layers: [
//      new ol.layer.Tile({
//          source: new ol.source.XYZ({
//              name: "国家天地图12-18级(影像图)",
//              url: "https://t4.tianditu.gov.cn/DataServer?T=img_w&tk=9f836c109b1fe4e3bb06503b85254635&x={x}&y={y}&l={z}"
//          }),
//          maxResolution: resolutions[8],
//          minResolution: resolutions[18]
//      }),
//      new ol.layer.Tile({
//          source: new ol.source.XYZ({
//              name: "国家天地图12-18级(路网图)",
//              url: "https://t4.tianditu.gov.cn/DataServer?T=cia_w&tk=9f836c109b1fe4e3bb06503b85254635&x={x}&y={y}&l={z}"
//          }),
//          maxResolution: resolutions[8],
//          minResolution: resolutions[18]
//      }),
//      new ol.layer.Tile({
//          source: new ol.source.WMTS({
//              name: "无锡天地图18-20级(影像图)",
//              url: "http://61.177.139.228/gateway/WMTSServer/wuxi_image?ak=259d9d19eaf54e81901e3ba688c3a278",
//              layer: "0",
//              style: "default",
//              matrixSet: "default",
//              format: "tiles",
//              wrapX: true,
//              tileGrid: new ol.tilegrid.WMTS({
//                  origin: ol.extent.getTopLeft(projectionExtent),
//                  resolutions: resolutions,
//                  matrixIds: matrixIds
//              })
//          }),
//          maxResolution: resolutions[18],
//          minResolution: resolutions[20]
//      }),
//      new ol.layer.Tile({
//          source: new ol.source.WMTS({
//              name: "无锡天地图18-20级(路网图)",
//              url: "http://61.177.139.228/gateway/WMTSServer/wuxi_imageanno?ak=259d9d19eaf54e81901e3ba688c3a278",
//              layer: "0",
//              style: "default",
//              matrixSet: "default",
//              format: "tiles",
//              wrapX: true,
//              tileGrid: new ol.tilegrid.WMTS(
//                  origin: ol.extent.getTopLeft(projectionExtent),
//                  resolutions: resolutions,
//                  matrixIds: matrixIds
//              })
//          }),
//          maxResolution: resolutions[18],
//          minResolution: resolutions[20]
//      }),
//    ],
//    target: "map",
//    view: new ol.View({
//        center: center,//地图中心点
//        projection: projection,//投影类别
//        zoom: 15,//默认缩放级别
//        maxZoom: 20,//最大缩放级别
//        minZoom: 11//最小缩放级别
//    })
//});

map = new ol.Map({
    controls: ol.control.defaults({
        attribution: false
    }).extend([
        new ol.control.MousePosition()//是否显示鼠标所在地图点的经纬度
    ]),
    layers: [
      new ol.layer.Tile({
          source: new ol.source.XYZ({
              url: 'http://wprd0{1-4}.is.autonavi.com/appmaptile?lang=zh_cn&scl=1&style=7&x={x}&y={y}&z={z}'
          })
      })
    ],
    target: "map",
    view: new ol.View({
        center: center,//地图中心点
        projection: projection,//投影类别
        zoom: 16,//默认缩放级别
        maxZoom: 18,//最大缩放级别
        minZoom: 11//最小缩放级别
    })
});


var toggleFlag = false
//获取鼠标点击位置的坐标
$("#map").click(function (e) {
	//根据m.event.toElement判断他是否在清除图标上面
	
	if(e.toElement && e.toElement.title != '清除' && e.toElement.innerText != '半径搜索'){
		coordinates = map.getEventCoordinate(e);
	}
	
	
//  if(draw && draw.mode_ == 'Circle'){
//  	$('#slider').css('display','')
//  }
//  console.log(toggleFlag)
//  //0是执法人员  1是摄像头
//  if(feature && feature.values_.type == 0){
//  	// 下面把上面的图标附加到地图上，需要一个ol.Overlay
//		  var anchor = new ol.Overlay({
//		    element: document.getElementById('anchor')
//		  });
//		  // 关键的一点，需要设置附加到地图上的位置
//		  anchor.setPosition(coordinates.split(','));
//		  // 然后添加到map上
//		  map.addOverlay(anchor);
//		  if(!toggleFlag){
//		  	toggleFlag = true
//		  }
//		  $('#toggle').click()
//  }else if(toggleFlag){
//  	toggleFlag = false
//  	$('#toggle').click()
//  }
    
})

//鼠标移动事件
map.on('pointermove',function(e) {
//	//画圆计算半径
//	if(draw && draw.mode_ == 'Circle'){
//		//圆心点坐标
//		let center = coordinates.split(',');
//		//计算
//		let radius = ol.sphere.getDistance(center,e.coordinate);
//		//单位m转km 四舍五入
//		radius = Math.round(radius/1000*10)/10
//		if(radius){
//			$('#sliderVal').text(rtoggleFlagtoggleFlagadius)
//		}
//	}
	
	//改变移至图层的鼠标样式
	if(!draw){
		var pixel=map.getEventPixel(e.originalEvent);
		  var feature=map.forEachFeatureAtPixel(pixel,function (feature) {
		    return feature;
		  }) 
		  
		  
		  if(feature==undefined){
		    map.getTargetElement().style.cursor="auto"
		  }else{
		    map.getTargetElement().style.cursor="pointer"
		  }
	}
	
})

//中心点处理
function ReturnCenter(m_centerXY) {
    //alert(m_centerXY);
    var centerObj = m_centerXY.split(',');
    var centerX = centerObj[0];
    var centerY = centerObj[1];
    return [parseFloat(centerX), parseFloat(centerY)];//一定要转换下类型，否则拖拽后，地图就消失了
}

$(map.getViewport()).on("contextmenu", function (event) {
    var pixel = map.getEventPixel(event.originalEvent);//获取鼠标当前像素点
    var feature = map.hasFeatureAtPixel(pixel);//通过像素点判断当前鼠标上是否有图层
    var coordinate = map.getEventCoordinate(event);//获取鼠标坐标
    event.preventDefault();//取消右键默认行为

    //如果没有图层 ，移除修改热区和删除热区两个菜单项
    if (feature) {
        //获取当前点击的图层
        hotTileLayer = map.forEachLayerAtPixel(pixel, function (layer) {
            return layer;
        });
        currentFeature = map.forEachFeatureAtPixel(pixel, function (feature) {
            return feature;
        });
        hotTileId = currentFeature.getId();
        hotTileName = currentFeature.get('name');

        if (currentFeature.values_.type == 0) {
            //显示警员菜单
            $('#checkVideo').hide();
            $('#addCamera').hide();
            $('#showVideo').show();
            $('#showVoice').show();

        }

        if (currentFeature.values_.type == undefined) {
            //说明是其他操作 例如画矩形等
            $("#ReditHottile").hide();
            $("#RdelHotTile").hide();
            $("#showVideo").hide();
            $("#checkVideo").hide();
            $('#showVoice').hide();
        }

        if (currentFeature.values_.type == 1) {
            //显示摄像头菜单
            $("#showVideo").hide();
            $("#checkVideo").show();
            $('#showVoice').hide();
            $('#addCamera').hide();
        }

    } else if (feature == 'undefined') {
        $("#ReditHottile").hide();
        $("#RdelHotTile").hide();
        $('#drawCircle').show();
       
    } else {
        $("#ReditHottile").hide();
        $("#RdelHotTile").hide();
        $("#showVideo").hide();
        $("#checkVideo").hide();
        $('#showVoice').hide();
        $('#addCamera').show();
        $('#drawCircle').show();
       
    }
});

 var anchor = new ol.Overlay({
    element: document.getElementById('anchor')
  });

//右键菜单
var rightMenu = new ol.Overlay({
    element: document.getElementById("contextmenu"),
    positioning: 'center-center'
});
rightMenu.setMap(map);

//半径框
var radius = new ol.Overlay({
    element: document.getElementById("showRadius"),
    positioning: 'center-center'
});



//点击左键隐藏菜单
$(map.getViewport()).on("click", function (e) {
    e.preventDefault();
    rightMenu.setPosition(undefined);
    if(circleFlag){
    	//true 则说明是画圆
    	circleFlag = false;
    	return;
    }
    map.removeOverlay(anchor);//关键点
    map.removeOverlay(radius);
    $('#showRadius').css('display','none')
});

//点击右键
$(map.getViewport()).on("contextmenu", function(e){
    e.preventDefault();
    // 书写事件触发后的函数
    coordinates = map.getEventCoordinate(e);
    rightMenu.setMap(map);
   	rightMenu.setPosition(coordinates);
    //赋值圆心坐标给半径框
	radiusCorn = coordinates;
    if(!draw){
    	var pixel=map.getEventPixel(e.originalEvent);
		var feature=map.forEachFeatureAtPixel(pixel,function (feature) {
		   return feature;
		})
		if(feature){
			return;
	    }
		  // 关键的一点，需要设置附加到地图上的位置
		  anchor.setPosition(coordinates);
		  // 然后添加到map上
		  map.addOverlay(anchor);
    }
});

//绘制矩形
$("#DrawBox").click(function () {
    MapManager.drawBox();
})

//绘制圆形
$("#DrawCircle").click(function () {
    MapManager.drawCircle();
})

//绘制热区
$("#DrawHottile").click(function () {
    MapManager.gatherHotTile();
})

//采集POI
$("#DrawPOI").click(function () {
    MapManager.gatherHotTile();
})

//清除
$("#DrawClear").click(function () {
    if (draw != null) {
        map.removeInteraction(draw);
    }
    console.log(layers)
    for(var i=0;i<layers.length;i++){
    	map.removeLayer(layers[i]);
    }

    
})

//右键菜单点击事件 
//回中心点
$("#RsetCenteer").click(function (e) {
    map.getView().setCenter(center);
    map.getView().setZoom(15);
    e.preventDefault();
    menu_overlay.setPosition(undefined);
})

//编辑图层
$("#ReditHottile").click(function (e) {
    //弹出保存对话框
    OpenWin('/ExpFrame/GisMap/HotTile/Edit?RowGuid=' + hotTileId, '800px', '550px', "编辑图层", function () {

        //回调
    })
    e.preventDefault();
    menu_overlay.setPosition(undefined);
})

//删除图层
$("#RdelHotTile").click(function (e) {
    if (confirm("确定要删除图层'" + hotTileName + "'吗？")) {
        $.post("/ExpFrame/GisMap/HotTile/DeleteHotTile?RowGuid=", { RowGuid: hotTileId }, function (data) {
            if (data.IsSuccess == true) {
                $.messager.alert("提醒", "删除成功", "info");
                //移除热区,好像没有效果
                hotTileLayer.getSource().clear(true);
                map.removeLayer(hotTileLayer);
            } else {
                $.messager.alert("提醒", "删除失败", "info");
            }
        });
    }
    e.preventDefault();
    menu_overlay.setPosition(undefined);
})





//包干区默认显示样式
var BaoGanOverlayStyle = new ol.layer.Vector({
    source: new ol.source.Vector(),
    map: map,
    style: function (feature, resolution) {
        var text = resolution < 5000 ? feature.get('name') : '';
        return new ol.style.Style({
            stroke: new ol.style.Stroke({
                color: '#ffd800',
                width: 2
            }),
            fill: new ol.style.Fill({
                color: 'rgba(255,255,255,0.5)'
            }),
            text: new ol.style.Text({
                font: '12px Calibri,sans-serif',
                text: text,
                fill: new ol.style.Fill({
                    color: '#000'
                }),
                stroke: new ol.style.Stroke({
                    color: '#fff',
                    width: 5
                })
            })
        });
    }
});



//移动到热区高亮显示
var highlightStyleCache = {};
var featureOverlay = new ol.layer.Vector({
    source: new ol.source.Vector(),
    map: map,
    style: function (feature, resolution) {
        var text = resolution < 5000 ? feature.get('name') : '';
        if (!highlightStyleCache[text]) {
            highlightStyleCache[text] = new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: '#ffd800',
                    width: 2
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(255,255,255,0.5)'
                }),
                text: new ol.style.Text({
                    font: '12px Calibri,sans-serif',
                    text: text,
                    fill: new ol.style.Fill({
                        color: '#000'
                    }),
                    stroke: new ol.style.Stroke({
                        color: '#fff',
                        width: 5
                    })
                })
            });
        }
        return highlightStyleCache[text];
    }
});

var highlight;
var displayFeatureInfo = function (pixel) {

    var feature = map.forEachFeatureAtPixel(pixel, function (feature) {
        return feature;
    });
    if (feature !== highlight) {
        if (highlight) {
            featureOverlay.getSource().removeFeature(highlight);
        }
        if (feature) {
            featureOverlay.getSource().addFeature(feature);
        }
        highlight = feature;
    }
};