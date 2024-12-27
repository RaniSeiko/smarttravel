var map = new BMap.Map("container");
var point = new BMap.Point(120.15507, 30.25348); // 杭州西湖的经纬度坐标
map.centerAndZoom(point, 16); // 设置缩放级别为16

// var scaleCtrl = new BMapGL.ScaleControl();  // 添加比例尺控件
// map.addControl(scaleCtrl);
// var zoomCtrl = new BMapGL.ZoomControl();  // 添加缩放控件
// map.addControl(zoomCtrl);

const newRouteBtn = document.getElementById('newRoute')

var points = [
    new BMap.Point(120.141081, 30.258531), // 岳王庙
    new BMap.Point(120.149058, 30.256348), // 中山公园
    new BMap.Point(120.152508, 30.258282), // 平湖秋月
    new BMap.Point(120.158814, 30.265191), // 白堤
    new BMap.Point(120.16406, 30.265877), // 风波亭
    new BMap.Point(120.165286, 30.262684) // 湖滨公园
];

// 获取按钮元素
var routeBtn = document.getElementById('routeBtn');

// 点击事件处理函数
routeBtn.addEventListener('click', function () {
    // 设置查看最新的旅游记录
    localStorage.setItem('item',JSON.stringify({item:4}));

    var checkboxData = {};
    var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked')
    // 生成当前旅游点
    checkboxes.forEach(function (checkbox) {
        checkboxData[checkbox.name] = checkbox.value;
    });
    //获取localStorage数据
    var PointsData = JSON.parse(localStorage.getItem('checkboxData'));
    //添加当前旅游点
    PointsData.push(checkboxData);

    if (PointsData.length > 5){   //若长度大于5，则删除第一条数据
        PointsData.shift();
    }

    // 上传数据
    localStorage.setItem('checkboxData', JSON.stringify(PointsData));

    // 在这里执行页面跳转操作，例如跳转到名为 "other_page.html" 的页面
    window.location.href = '../ing/ing.html';
});


//遗留代码
    // Image// 在页面加载完成后执行以下代码
    // window.onload = function () {
    //     // 创建一个 Image 对象
    //     var img = new Image();

    //     // 设置 Image 对象的 src 为百度地图的静态地图 API 地址
    //     img.src = "http://api.map.baidu.com/staticimage/v2?ak=EHEQP4Ept6uvPImZEwPd0zAvVWUU7hpO&mcode=666666&center=120.15507,30.22348&width=300&height=200&zoom=16";

    //     // 当图片加载完成后，将其作为页面背景
    //     img.onload = function () {
    //         document.body.style.backgroundImage = "url('" + img.src + "')";
    //     };
    // };
    // })