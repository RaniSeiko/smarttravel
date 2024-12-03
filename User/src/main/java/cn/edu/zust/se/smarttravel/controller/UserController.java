package cn.edu.zust.se.smarttravel.controller;

import cn.edu.zust.se.smarttravel.entity.R;
import cn.edu.zust.se.smarttravel.entity.po.User;
import cn.edu.zust.se.smarttravel.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@Transactional
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;


    /**
     * 用户注册
     *
     * @param user 用户数据
     * @return R<String>
     */
    @PostMapping("/enroll")
    public R<User> save(@RequestBody User user){
        log.info("注册用户：{}",user);
        User newUser=userService.addUser(user);
        log.info("封装完成用户数据{}",newUser);
        return R.success("注册用户成功",newUser);
    }


    /**
     * 用户登录
     *
     * @param request 请求
     * @param user 登录数据
     * @return R<User>
     */
    @PostMapping("/login")
    public R<User> login(HttpServletRequest request, @RequestBody User user){
        log.info("用户登录：{}",user);
        User user1 = userService.login(user);
        if(user1==null){
            return R.error("用户尚未注册");
        }else if(user1.getAccount().equals("WrongPassword")){
            return R.error("密码错误");
        }else {//登录成功，id放入Session，返回结果
            request.getSession().setAttribute("user",user1.getId());
            return R.success(user1);
        }
    }

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return R<User>
     */
    @GetMapping("/id")
    public R<User>  getUserById(@RequestParam Long id) {
        log.info("查询id:{}的用户信息",id);
        return userService.getUserById(id);
    }

    /**
     * 退出登录
     *
     * @param request 请求
     * @return R<String>
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //清除session中员工id
        request.getSession().removeAttribute(("user"));
        return R.success("退出成功");
    }

    /**
     * 修改用户信息
     *
     * @param user 修改信息
     * @return R<User>
     */
    @PutMapping()
    public R<User> update(@RequestBody User user) {
        log.info("更新用户数据:{}",user);
        return  userService.updateUserById(user);
    }
}
