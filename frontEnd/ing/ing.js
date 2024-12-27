var map = new BMap.Map("container");
var point = new BMap.Point(120.15507, 30.25348); // 杭州西湖的经纬度坐标
var shift = [BMAP_NORMAL_MAP,BMAP_SATELLITE_MAP,BMAP_HYBRID_MAP]; //可调节地图类型
map.setMapType(shift[0]);
map.centerAndZoom(point, 15); // 设置缩放级别为15
// map.enableScrollWheelZoom(true);// 开启滚动缩放

var points = [  //所有点位集
    new BMap.Point(120.141081, 30.258531), // 岳王庙
    new BMap.Point(120.149058, 30.256348), // 文澜阁
    new BMap.Point(120.152508, 30.258282), // 平湖秋月
    new BMap.Point(120.158814, 30.265191), // 云水光中
    new BMap.Point(120.164064, 30.264688), // 钱塘门
    new BMap.Point(120.166996, 30.260926) // 湖滨公园
];
//根据点位创建覆盖物
function addPoint(i) {
    var marker = new BMap.Marker(points[i]);
    map.addOverlay(marker);
}
var points2 = [];//所选点位集

var checkboxData = JSON.parse(localStorage.getItem('checkboxData'));
var item = JSON.parse(localStorage.getItem('item'));
var num = item.item;
if (checkboxData) {
    //添加标记
    for (const key in checkboxData[num]) {
        const i = checkboxData[num][key];
        addPoint(i - 1);

        points2.push(points[i - 1]);
    }
    // 连接地点
    var polyline = new BMap.Polyline(points2, { strokeColor: "blue", strokeWeight: 2, strokeOpacity: 0.5 });
    map.addOverlay(polyline);
}


var overBtn = document.getElementById('over');

// 点击事件处理函数
overBtn.addEventListener('click', function () {
    window.location.href = '../main/main.html';
});


//退出按钮
// 获取按钮元素
var backButton = document.querySelector('.return-button');

//回主界面按钮样式
   // 监听整个文档区域的鼠标移动事件
document.addEventListener('mousemove', function (e) {
    var mouseY = e.clientY;

    // 当鼠标在0-100px范围内时
    if ((mouseY <= 100)) {
        // 显示按钮动画
        backButton.classList.add('is-visible');
        backButton.classList.remove('is-leaving');
    } else {
        // 鼠标离开后延时一段时间再隐藏，避免频繁闪烁
        setTimeout(function () {
            if (!backButton.matches(':hover')) {
                backButton.classList.remove('is-visible');
                backButton.classList.add('is-leaving');
            }
        }, 200); // 可以调整这个延时值以适应不同的交互需求
    }
});
// 可选地，为按钮添加mouseleave事件以确保鼠标离开按钮时立即隐藏
backButton.addEventListener('mouseleave', function () {
    this.classList.remove('is-visible');
    this.classList.add('is-leaving');
});

backButton.addEventListener('click', () => {
    window.location.href = '../main/main.html';
})