var submitBtn = document.getElementById('submit'); // 点击事件处理函数 
submitBtn.addEventListener('click', function () { // 在这里执行页面跳转操作，例如跳转到名为 "other_page.html" 的页面 
    window.location.href = '../main/main.html';
});
function hideEditProfileModal() {
    document.getElementById('edit-profile-modal').classList.remove("show");
}

function showEditProfileModal() {
    document.getElementById('edit-profile-modal').classList.add("show");
}

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