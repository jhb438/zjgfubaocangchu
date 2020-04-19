var draw;               //绘制热区或者poi的对象
var hotVectorLayer;     //所有热区Vector
var lineLayer;
var layers = [];

//地图相关操作类
var MapManager = {};
(function (m) {

    //加载热区
    m.loadHotTile = function (type, isClick) {
        $.post("/ExpFrame/GisMap/HotTile/LoadHotTile", { Type: type }, function (data) {
            if (data.IsSuccess == true) {
                m.drawHotTileGeo(data.Message.replace(/&lt;/g, "<").replace(/&gt;/g, ">").replace(/&quot;/g, "\"").replace(/&#39;/g, "'"), type)


                //启用热区点击事件
                if (isClick) {
                    m.enableHotTileClick();
                }
            }
        });
    };

    //采集热区
    m.gatherHotTile = function () {
        polygonLayer = new ol.layer.Vector({
            source: new ol.source.Vector(),
            style: new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: '#ffd800',
                    width: 2
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.4)'
                }),
            }),
        })
        map.addLayer(polygonLayer);

        //修改ol.interaction.Draw的构造参数type的值为
        //'Point'，'Polygon',，'MultiPoint'，'MultiLineString'，'MultiPolygon' 或者 'Circle'就可以添加点，多边形，多个点，多条线，多个多边形，及圆
        draw = new ol.interaction.Draw({
            clickTolerance: 1,
            snapTolerance: 1,
            type: 'Polygon',
            source: polygonLayer.getSource(),    // 注意设置source，这样绘制好的线，就会添加到这个source里
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.5)'
                }),
                stroke: new ol.style.Stroke({
                    color: '#ffcc33',
                    lineDash: [10, 10],
                    width: 2
                }),
                image: new ol.style.Circle({
                    radius: 5,
                    stroke: new ol.style.Stroke({
                        color: 'rgba(0, 0, 0, 0.7)'
                    }),
                    fill: new ol.style.Fill({
                        color: 'rgba(255, 255, 255, 0.2)'
                    })
                })
            }),
            //maxPoints: 10    // 限制不超过10个点
        });

        // 监听线绘制结束事件，获取坐标
        draw.on('drawend', function (event) {
            var coordinates = JSON.stringify(event.feature.getGeometry().getCoordinates());

            //弹出保存对话框
            OpenWin('/ExpFrame/GisMap/HotTile/Create?coordinates=' + coordinates, '700px', '450px', "新增图层", function () {
                if (top.RetValue != "") {
                    //绘制热区
                    m.drawHotTile(top.RetValue.split('|')[0], top.RetValue.split('|')[1], event.feature.getGeometry().getCoordinates());
                    top.RetValue = "";
                }
                polygonFeature = null;
            });
            //在地图上移除多边形区域;
            polygonLayer.getSource().clear(true);
            map.removeLayer(polygonLayer);

        });

        map.addInteraction(draw);
    };

    //清除所有热区
    m.clearHotTile = function () {	
        featureOverlay_red.getSource().clear();
        featureOverlay_yellow.getSource().clear();
        featureOverlay_green.getSource().clear();
    }

    //根据geojson绘制热区
    m.drawHotTileGeo = function (geojson, type) {
        var geojsonObject = geojson;
        geojsonObject = eval('[' + geojsonObject + ']')[0];

        var styles = [
          new ol.style.Style({
              stroke: new ol.style.Stroke({
                  color: 'rgba(255,255,255, 0)',
                  width: 2
              }),
              fill: new ol.style.Fill({
                  color: 'rgba(255,255,255, 0)'
              }),
              text: new ol.style.Text({
                  font: '12px Calibri,sans-serif',
                  fill: new ol.style.Fill({
                      color: '#000'
                  }),
                  stroke: new ol.style.Stroke({
                      color: '#fff',
                      width: 3
                  })
              })
          })
        ];

        var source = new ol.source.Vector({
            features: (new ol.format.GeoJSON()).readFeatures(geojsonObject),
            style: function (feature, resolution) {
                style.getText().setText(resolution < 5000 ? feature.get('name') : '');  //当放大到1:5000分辨率时，显示名字
                return [style];
            },
            zIndex: 10
        });

        hotVectorLayer = new ol.layer.Vector({
            source: source,
            style: styles
        });

        map.addLayer(hotVectorLayer);


        if (type == "1") {          //热区
            //鼠标移到热区时高亮显示
            map.on('pointermove', function (evt) {
                if (evt.dragging) {
                    return;
                }
                var pixel = map.getEventPixel(evt.originalEvent);
                displayFeatureInfo(pixel);
            });
        } else if (type == "2") {   //包干区

            //设置包干区默认样式
            var features = hotVectorLayer.getSource().getFeatures();
            for (var i = 0; i < features.length ; i++) {
                BaoGanOverlayStyle.getSource().addFeature(features[i]);
            }
        }

    };

    //根据坐标点信息绘制热区
    m.drawHotTile = function (RowGuid, Name, coordinates) {
        var styles = [
            new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: 'rgba(255,255,255, 0)',
                    width: 2
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(255,255,255, 0)'
                }),
                text: new ol.style.Text({
                    font: '12px Calibri,sans-serif',
                    fill: new ol.style.Fill({
                        color: '#000'
                    }),
                    stroke: new ol.style.Stroke({
                        color: '#fff',
                        width: 3
                    })
                })
            })
        ];

        var geojsonObject = {
            'type': 'FeatureCollection',
            'crs': { 'type': 'name', 'properties': { 'name': 'EPSG:4326' } },
            'features': [{
                'type': 'Feature',
                'id': RowGuid,
                'properties': { "name": Name },
                'geometry': {
                    'type': 'Polygon',
                    'coordinates': coordinates
                }
            }]
        };

        var source = new ol.source.Vector({
            features: (new ol.format.GeoJSON()).readFeatures(geojsonObject)
        });



        hotVectorLayer = new ol.layer.Vector({
            source: source,
            style: styles
        });

        map.addLayer(hotVectorLayer);


        //设置包干区默认样式
        var features = hotVectorLayer.getSource().getFeatures();
        for (var i = 0; i < features.length ; i++) {
            BaoGanOverlayStyle.getSource().addFeature(features[i]);
        }
    };

    //热区点击事件
    m.enableHotTileClick = function () {
        map.on('click', function (evt) {
            //displayFeatureInfo(evt.pixel);

            var pixel = map.getEventPixel(evt.originalEvent);
            var feature = map.forEachFeatureAtPixel(pixel, function (feature, layer) {
                return feature;
            });
            var coordinate = evt.coordinate;
            if (feature != undefined) {
                //显示热区信息

                OpenWin('/XinWuHB/SmallWatershed/TabSearch/Tabs?ItemText=' + feature.get("name"), 1000, 600, '综合查看');
            }
        });
    };

    //绘制矩形(框选)
    m.drawBox = function () {
        var boxLayer = new ol.layer.Vector({
            source: new ol.source.Vector(),
            style: new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: '#ffd800',
                    width: 2
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.4)'
                }),
            }),
        })
		layers.push(boxLayer)
        map.addLayer(boxLayer);
        draw = new ol.interaction.Draw({
            source: boxLayer.getSource(),
            type: 'LineString',
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)'
                }),
                stroke: new ol.style.Stroke({
                    color: '#ffcc33',
                    width: 2
                }),
                image: new ol.style.Circle({
                    radius: 7,
                    fill: new ol.style.Fill({
                        color: '#ffcc33'
                    })
                })
            }),
            maxPoints: 2,
            geometryFunction: function (coordinates, geometry) {
                if (!geometry) {
                    geometry = new ol.geom.Polygon(coordinates);
                }
                var start = coordinates[0];
                var end = coordinates[1];
                geometry.setCoordinates([[start, [start[0], end[1]], end, [end[0], start[1]], start]]);
                return geometry;
            }
        })

        // 监听线绘制结束事件，获取坐标
        draw.on('drawend', function (event) {
            var coordinates = event.feature.getGeometry().getCoordinates();
			let maxX = coordinates[0][1];
			let minY = coordinates[0][3];
			let box = maxX.concat(minY);
			OpenWin('/static/pages/deviceMap/police_camera_list.html?box='+box, 310, 600, '摄像头与执法人员列表')
			
        });
        map.addInteraction(draw);
    }
    
    //绘制圆
    m.drawCircle = function () {
    	//打开半径显示
        var boxLayer = new ol.layer.Vector({
            source: new ol.source.Vector(),
            style: new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: '#ffd800',
                    width: 2
                }),
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.4)'
                }),
            }),
        })
        map.addLayer(boxLayer);
        draw = new ol.interaction.Draw({
            source: boxLayer,
            type: 'Circle',
            style: new ol.style.Style({
                fill: new ol.style.Fill({
                    color: 'rgba(255, 255, 255, 0.2)'
                }),
                stroke: new ol.style.Stroke({
                    color: '#ffcc33',
                    width: 2
                }),
                image: new ol.style.Circle({
                    radius: 7,
                    fill: new ol.style.Fill({
                        color: '#ffcc33'
                    })
                })
            }),
            geometryFunction: ol.interaction.Draw.createRegularPolygon(32,4)
        })
		
//		// 鼠标移动事件
//		map.on('pointermove', function (evt) {
//			
//		});
		
        // 监听线绘制结束事件，获取坐标
        draw.on('drawend', function (event) {
          	var extent = ol.extent.boundingExtent(event.feature.getGeometry().getCoordinates()[0]); //获取一个坐标数组的边界，格式为[minx,miny,maxx,maxy]
			var center = ol.extent.getCenter(extent);   //获取边界区域的中心位置
			
			//获取半径
			let point = event.feature.getGeometry().getCoordinates()[0];
			var radius = ol.sphere.getDistance(center,point[0]);
          	console.log('圆心'+center+",半径"+radius)
			OpenWin('/static/pages/deviceMap/police_camera_circlelist.html?center='+center+"&radius="+radius, 310, 600, '摄像头与执法人员列表',refreshGrid)
			
        });
        map.addInteraction(draw);
	}   
    
    //根据半径绘制圆
    m.drawCircleByRadius = function (center, radius) {
        var proj = map.getView().getProjection();
        var circleLayer = new ol.layer.Vector({
                source: new ol.source.Vector({
                    features: [new ol.Feature(new ol.geom.Polygon.fromExtent(proj.getExtent()))]
                }),
                style: function (feature) {
                    return [
                        new ol.style.Style({
                            geometry: ol.geom.Polygon.circular(center, radius, 32).transform('EPSG:4326', proj),
                            stroke: new ol.style.Stroke({
                                color: 'blue',
                                width: 3
                            })
                        })
                    ];
                }
            })
        layers.push(circleLayer)
        map.addLayer(circleLayer);
    }
	
    //采集Poi
    m.gatherPoi = function () {
        var vector = new ol.layer.Vector({
            source: new ol.source.Vector()
        });
        map.addLayer(vector);

        draw = new ol.interaction.Draw({
            type: 'Point',
            source: vector.getSource()
        });

        // 监听线绘制结束事件，获取坐标
        draw.on('drawend', function (event) {

            //弹出保存对话框
            OpenWin('../Poi/Add?coordinates=' + JSON.stringify(event.feature.getGeometry().getCoordinates()), '450px', '420px', "新增Poi", function () {
                if (topValue != null) {
                    //添加marker
                    m.addMarker(event.feature.getGeometry().getCoordinates());
                    topValue = null;

                    map.removeInteraction(draw);
                    draw = null;
                }
            })
        });
        map.addInteraction(draw);
    }

    //添加Poi
    m.addPoi = function (point, text,value,type, image) {
		
        if (image == null || image == undefined || image == "") {
            image = "../../Scripts/libs/images/marker.png";
        }
        var iconFeature = new ol.Feature({
            geometry: new ol.geom.Point(point),
            name: 'marker',
            population: 4000,
            rainfall: 500,
            mineGuid:value,
            type:type
        });

        var iconStyle = new ol.style.Style({
            image: new ol.style.Icon(({
                src: image
            })),
            text: new ol.style.Text({
                padding: [1, 5, 1, 5],
                offsetY: '26',
                font: '13px Microsoft YaHei',
                text: text,
                fill: new ol.style.Fill({
                    color: '#ffffff'
                }),
                backgroundFill: new ol.style.Fill({
                    color: 'rgb(255, 153, 71)'
                })
            })
        });

        iconFeature.setStyle(iconStyle);

        var vectorSource = new ol.source.Vector({
            features: [iconFeature]
        });

        var vectorLayer = new ol.layer.Vector({
            source: vectorSource
        });
        map.addLayer(vectorLayer);
    }



    //显示路线
    m.showRoute = function (routeCoords) {
        //画出路线图
        var routeLength = routeCoords.length;
        MapManager.drawLine(routeCoords);
        var geoMarker = new ol.Feature({
            type: 'geoMarker',
            geometry: new ol.geom.Point(routeCoords[0])
        });
		
        var styles = {
            'geoMarker': new ol.style.Style({
                image: new ol.style.Icon({
                    anchor: [0.5, 0.5],
                    src: '../../Scripts/libs/images/user2.png',

                })
            })
        };


        vectorLayer = new ol.layer.Vector({
            source: new ol.source.Vector({
                features: [geoMarker]
            }),
            style: function (feature) {
                return styles[feature.get('type')];
            }
        });
        layers.push(vectorLayer)
        map.addLayer(vectorLayer);


        //动画
        var animating = false;
        var speed = 3;
        var now;


        var moveFeature = function (event) {
            var vectorContext = ol.render.getVectorContext(event);
            var frameState = event.frameState;

            if (animating) {
                var elapsedTime = frameState.time - now;
                // here the trick to increase speed is to jump some indexes
                // on lineString coordinates
                var index = Math.round(speed * elapsedTime / 1000);

                if (index >= routeLength) {
                    stopAnimation(true);
                    return;
                }

                var currentPoint = new ol.geom.Point(routeCoords[index]);
                var feature = new ol.Feature(currentPoint);
                vectorContext.drawFeature(feature, styles.geoMarker);
            }
            // tell OpenLayers to continue the postrender animation
            map.render();
        };

        function startAnimation() {
            if (animating) {
                stopAnimation(false);
            } else {
                animating = true;
                now = new Date().getTime();
                // hide geoMarker
                geoMarker.setStyle(null);
                // just in case you pan somewhere else
                //map.getView().setCenter(center);
                vectorLayer.on('postrender', moveFeature);
                map.render();
            }
        }


        /**
         * @param {boolean} ended end of animation.
         */
        function stopAnimation(ended) {
            animating = false;

            // if animation cancelled set the marker at the beginning
            var coord = ended ? routeCoords[routeLength - 1] : routeCoords[0];
            var geometry = geoMarker.getGeometry();
            geometry.setCoordinates(coord);
            //remove listener
            vectorLayer.un('postrender', moveFeature);
        }

        startAnimation();
    }


    //根据坐标点信息绘制线路
    m.drawLine = function (coordinates) {
        var styles = [
            new ol.style.Style({
                stroke: new ol.style.Stroke({
                    color: '#1E9FFF',
                    width: 5
                }),
                fill: new ol.style.Fill({
                    color: '#1E9FFF'
                }),
                text: new ol.style.Text({
                    font: '12px Calibri,sans-serif',
                    fill: new ol.style.Fill({
                        color: '#1E9FFF'
                    }),
                    stroke: new ol.style.Stroke({
                        color: '#1E9FFF',
                        width: 3
                    })
                })
            })
        ];

        var geojsonObject = {
            'type': 'FeatureCollection',
            'crs': { 'type': 'name', 'properties': { 'name': 'EPSG:4326' } },
            'features': [{
                'type': 'Feature',
                'geometry': {
                    'type': 'LineString',
                    'coordinates': coordinates
                }
            }]
        };
		
		let d = (new ol.format.GeoJSON()).readFeatures(geojsonObject);
        var source = new ol.source.Vector({
            features: d
        });

        lineLayer = new ol.layer.Vector({
            source: source,
            style: styles
        });
		layers.push(lineLayer)
        map.addLayer(lineLayer);
    };

})(MapManager);

function refreshGrid(){
	$('#slider').css('display','none')
}

