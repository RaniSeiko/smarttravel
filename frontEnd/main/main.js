//鼠标经过效果
const newDiv = document.getElementById("new");
const historyDiv = document.getElementById("history");
const ingDiv = document.getElementById("ing");
const personalDiv = document.getElementById("personal");

newDiv.addEventListener('mouseover', function () {
    this.style.backgroundColor = "#CCCCCC";
    this.querySelector("div").style.backgroundColor = "#FFFFFF";
    this.style.width = "200px";
    this.style.height = "200px";
    this.style.transform = "translate(-20px,-20px)";
    this.querySelector("div").style.borderBottom = "30px solid white";
    this.querySelector("div").style.borderLeft = "30px solid #CCCCCC";
    this.querySelector("div").style.transform = "translate(10px,5px)";
});

newDiv.addEventListener('mouseout', function () {
    this.style.backgroundColor = "#FFFFFF";
    this.querySelector("div").style.backgroundColor = "#CCCCCC";
    this.style.width = "180px";
    this.style.height = "180px";
    this.style.transform = "translate(0,0)";
    this.querySelector("div").style.width = "0px";
    this.querySelector("div").style.height = "0px";
    this.querySelector("div").style.borderBottom = "25px solid #CCCCCC";
    this.querySelector("div").style.borderLeft = "25px solid white";
    this.querySelector("div").style.transform = "translate(0px,0px)";
});

// 对其他三个span进行同样的操作
historyDiv.addEventListener('mouseover', function () {
    this.style.backgroundColor = "#CCCCCC";
    this.querySelector("div").style.backgroundColor = "#FFFFFF";
    this.style.width = "200px";
    this.style.height = "200px";
    this.style.transform = "translate(20px,-20px)";
    this.querySelector("div").style.borderBottom = "30px solid white";
    this.querySelector("div").style.borderRight = "30px solid #CCCCCC";
    this.querySelector("div").style.transform = "translate(10px,5px)";
});

historyDiv.addEventListener('mouseout', function () {
    this.style.backgroundColor = "#FFFFFF";
    this.querySelector("div").style.backgroundColor = "#CCCCCC";
    this.style.width = "180px";
    this.style.height = "180px";
    this.style.transform = "translate(0,0)";
    this.querySelector("div").style.width = "0px";
    this.querySelector("div").style.height = "0px";
    this.querySelector("div").style.borderBottom = "25px solid #CCCCCC";
    this.querySelector("div").style.borderRight = "25px solid white";
    this.querySelector("div").style.transform = "translate(0px,0px)";
});

ingDiv.addEventListener('mouseover', function () {
    this.style.backgroundColor = "#CCCCCC";
    this.querySelector("div").style.backgroundColor = "#FFFFFF";
    this.style.width = "200px";
    this.style.height = "200px";
    this.style.transform = "translate(-20px,20px)";
    this.querySelector("div").style.borderTop = "30px solid white";
    this.querySelector("div").style.borderLeft = "30px solid #CCCCCC";
    this.querySelector("div").style.transform = "translate(10px,5px)";
});

ingDiv.addEventListener('mouseout', function () {
    this.style.backgroundColor = "#FFFFFF";
    this.querySelector("div").style.backgroundColor = "#CCCCCC";
    this.style.width = "180px";
    this.style.height = "180px";
    this.style.transform = "translate(0,0)";
    this.querySelector("div").style.width = "0px";
    this.querySelector("div").style.height = "0px";
    this.querySelector("div").style.borderTop = "25px solid #CCCCCC";
    this.querySelector("div").style.borderLeft = "25px solid white";
    this.querySelector("div").style.transform = "translate(0px,0px)";
});


personalDiv.addEventListener('mouseover', function () {
    this.style.backgroundColor = "#CCCCCC";
    this.querySelector("div").style.backgroundColor = "#FFFFFF";
    this.style.width = "200px";
    this.style.height = "200px";
    this.style.transform = "translate(20px,10px)";
    this.querySelector("div").style.borderTop = "30px solid white";
    this.querySelector("div").style.borderRight = "30px solid #CCCCCC";
    this.querySelector("div").style.transform = "translate(10px,5px)";
});

personalDiv.addEventListener('mouseout', function () {
    this.style.backgroundColor = "#FFFFFF";
    this.querySelector("div").style.backgroundColor = "#CCCCCC";
    this.style.width = "180px";
    this.style.height = "180px";
    this.style.transform = "translate(0,0)";
    this.querySelector("div").style.width = "0px";
    this.querySelector("div").style.height = "0px";
    this.querySelector("div").style.borderTop = "25px solid #CCCCCC";
    this.querySelector("div").style.borderRight = "25px solid white";
    this.querySelector("div").style.transform = "translate(0px,0px)";
});

// 从localStorage获取登录信息
if (localStorage.getItem('loginInfo')) {
    const loginInfo = JSON.parse(localStorage.getItem('loginInfo'));
    console.log(loginInfo);
}

// window.onload = function () {
//     // 获取存储的登录信息
//     const loginInfo = localStorage.getItem('loginInfo');

//     if (!loginInfo) {
//         // 如果登录信息不存在，则跳转到登录页面
//         window.location.href = './login.html';
//     } else {
//         // 解析存储的登录信息为对象
//         const { username, password } = JSON.parse(loginInfo);

//     }
// };

newDiv.addEventListener('click', function () {
    window.location.href = '../new/new.html';
});

historyDiv.addEventListener('click', function () {
    window.location.href = '../history/history.html';
});

ingDiv.addEventListener('click', function () {
    localStorage.setItem('item',JSON.stringify({item:4})); //查看当前记录
    window.location.href = '../ing/ing.html';
});

personalDiv.addEventListener('click', function () {
    window.location.href = '../personal/personal.html';
});

//智能数字人
document.getElementById('robotImg')
robotImg.addEventListener('click',function(){
    window.location.href = 'http://192.168.222.0:5173/';
})


