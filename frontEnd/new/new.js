function removeElement(id) {
    document.getElementById(id).style.display = "none";
}
function openElement(id) {
    document.getElementById(id).style.display = "block";
}
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

window.onload = function () {
    var resetBtn = document.getElementById('reset');
    var showElements = document.getElementsByClassName('show');
    var emptyElement = document.querySelector('.empty');

    resetBtn.addEventListener('click', function () {
        for (var i = 0; i < showElements.length; i++) {
            showElements[i].textContent = '';
        }
        emptyElement.style.display = 'block'; // 显示“还没有已选择的景点”

        // 如果你的checkboxes在名为"myForm"的表单内，可以添加以下代码进行重置：
        var checkboxes = document.querySelectorAll('input[type="checkbox"]');
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = false;
        }
    });
};


const left = document.querySelector('.left');

left.addEventListener('click', function (event) {
    if (event.target.tagName === 'INPUT' && event.target.type === 'checkbox') {
        const parentDiv = event.target.closest('.pr1, .pr2, .pr3, .pr4, .pr5, .pr6');

        if (parentDiv && parentDiv.querySelector('.cn')) {
            const cnElement = parentDiv.querySelector('.cn');
            const cnText = cnElement.textContent.trim();

            const showElements = Array.from(document.querySelectorAll('.show'));

            // Find the first empty p element or the one that previously contained the text
            let targetP = null;
            for (let i = 0; i < showElements.length; i++) {
                const showP = showElements[i];
                if (!showP.innerText.trim() || showP.innerText === cnText) {
                    targetP = showP;
                    break;
                }
            }

            // Handle checkbox checked/unchecked state
            if (event.target.checked) {
                if (targetP) {
                    targetP.textContent = cnText;
                    const emptyElement = document.querySelector('.empty');
                    if (emptyElement) {
                        emptyElement.style.display = 'none';
                    }
                }
            } else {
                if (targetP) {
                    targetP.textContent = '';
                }
            }

            const hasNonEmptyShowElements = showElements.some(showP => showP.innerText.trim());

            if (!hasNonEmptyShowElements) {
                const emptyElement = document.querySelector('.empty');
                if (emptyElement) {
                    emptyElement.style.display = 'block';
                }
            }
        }
    }
});

