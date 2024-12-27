//获取表单对象
const loginForm = document.querySelector(".loginForm");
const registerForm = document.querySelector(".registerForm");

//获取按钮
const registerBut = document.querySelector(".register");
const beginBut = document.querySelector(".begin");

const registerBut2 = document.querySelector(".register2");
const returnBut = document.querySelector(".return");

//获取表单元素
const userName = document.getElementsByClassName('username');
const pswd = document.getElementsByClassName('pswd');

const zcuserName = document.getElementsByClassName('zcusername');
const pswd1 = document.getElementsByClassName('pswd1');
const pswd2 = document.getElementsByClassName('pswd2');

//进入注册界面
registerBut.addEventListener('click',function(){
    loginForm.style.display="none";
    registerForm.style.display="block"
})

//返回登录界面
returnBut.addEventListener('click',function(){
    loginForm.style.display='block';
    registerForm.style.display="none";
})

//提交用户信息并返回登录界面
registerBut2.addEventListener('click',function(){
    // if(localStorage.getItem(zcuserName.value === zcuserName.value)){
    //     alert('该用户已存在！')
    // }else if(pswd1.value !== pswd2.value){
    //     alert('两次密码不一致！')
    // }
    // else{
    //     localStorage.setItem(zcuserName.value,pswd1.value);
    //     alert('注册成功！')
    // }

    loginForm.style.display='block';
    registerForm.style.display="none";
})

//进入主界面
beginBut.addEventListener('click',function(){
   
    // if(userName === '' || pswd === ''){
    //     alert('用户名或密码不能为空');
    //      const storedPassword = localStorage.getItem(userName.value);
    //      if (pswd.value !== storedPassword){
    //         alert('密码不正确！')
    //      }else{
            window.location.href="./main/main.html"
    //      }
    // }

})