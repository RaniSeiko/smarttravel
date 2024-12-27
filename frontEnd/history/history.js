
//获取按钮
const btn1 = document.getElementById('btn1');
const btn2 = document.getElementById('btn2');
const btn3 = document.getElementById('btn3');
const btn4 = document.getElementById('btn4');
const btn5 = document.getElementById('btn5');

btn1.addEventListener('click', () => {
    localStorage.setItem('item', JSON.stringify({ item: 4 }));  //查看当前记录
    location.href = '../historyroute/historyroute.html'
})

btn2.addEventListener('click', () => {
    localStorage.setItem('item', JSON.stringify({ item: 3 }));  //查看上一次记录
    location.href = '../historyroute/historyroute.html'
})

btn3.addEventListener('click', () => {
    localStorage.setItem('item', JSON.stringify({ item: 2 }));  //查看上上次记录
    location.href = '../historyroute/historyroute.html'
})

btn4.addEventListener('click', () => {
    localStorage.setItem('item', JSON.stringify({ item: 1 }));  //查看次久记录
    location.href = '../historyroute/historyroute.html'
})

btn5.addEventListener('click', () => {
    localStorage.setItem('item', JSON.stringify({ item: 0 }));  //查看最久记录
    location.href = '../historyroute/historyroute.html'
})

window.onload = function(){
        // 获取各元素
        var p1 = document.querySelectorAll('.h1 p:nth-of-type(2)');  // 唉，没理解透nth-type和nth-child的区别，被卡了一小时
        var p2 = document.querySelectorAll('.h1 p:nth-of-type(4)');  
        var p3 = document.querySelectorAll('.h2 p:nth-of-type(2)');  
        var p4 = document.querySelectorAll('.h2 p:nth-of-type(4)');
        var p5 = document.querySelectorAll('.h3 p:nth-of-type(2)');
        var p6 = document.querySelectorAll('.h3 p:nth-of-type(4)');
        var p7 = document.querySelectorAll('.h4 p:nth-of-type(2)');
        var p8 = document.querySelectorAll('.h4 p:nth-of-type(4)');
        var p9 = document.querySelectorAll('.h5 p:nth-of-type(2)');
        var p10 = document.querySelectorAll('.h5 p:nth-of-type(4)');

        // 获取数据
        var pointsData = JSON.parse(localStorage.getItem('checkboxData'));
        //改变元素文本值
        p1[0].textContent = Object.keys(pointsData[4])[0];
        p2[0].textContent = Object.keys(pointsData[4])[Object.keys(pointsData[4]).length - 1]; //获取最后一个属性名
        p3[0].textContent = Object.keys(pointsData[3])[0];
        p4[0].textContent = Object.keys(pointsData[3])[Object.keys(pointsData[3]).length - 1];
        p5[0].textContent = Object.keys(pointsData[2])[0];
        p6[0].textContent = Object.keys(pointsData[2])[Object.keys(pointsData[2]).length - 1];
        p7[0].textContent = Object.keys(pointsData[1])[0];
        p8[0].textContent = Object.keys(pointsData[1])[Object.keys(pointsData[1]).length - 1];
        p9[0].textContent = Object.keys(pointsData[0])[0];
        p10[0].textContent = Object.keys(pointsData[0])[Object.keys(pointsData[0]).length - 1];
};

//退出按钮
// 获取按钮元素
var backButton = document.querySelector('.return-button');

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