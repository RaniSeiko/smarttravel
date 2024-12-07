package cn.edu.zust.se.smarttravel.service.impl;


import cn.edu.zust.se.smarttravel.entity.R;
import cn.edu.zust.se.smarttravel.entity.po.User;
import cn.edu.zust.se.smarttravel.mapper.UserMapper;
import cn.edu.zust.se.smarttravel.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static final double EPSILON = 1e-6; // 合适的容差值，用以浮点数判断
    @Override
    public User addUser(User user) {
        //设置初始密码123456，需要md5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setMode(0.5);
        user.setBudget(0.5);
        user.setRhythm(0.5);
        user.setCount(0);
        user.setAccount("myAccount");
        this.save(user);
        return this.getById(user.getId());
    }

    @Override
    public User login(User user) {
        //对前端密码加密
        String password = user.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据用户名查数据库
        User user1 = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername()));
        //数据是否存在
        if (user1 == null) {
            return null;
        }
        //密码比对
        if (!user1.getPassword().equals(password)) {
            user1.setAccount("WrongPassword");
            return user1;
        }
        return user1;
    }

    @Override
    public R<User> getUserById(Long id) {
        User user=this.getById(id);
        return user != null ? R.success(user) : R.error("用户不存在");
    }

    @Override
    public R<User> updateUserById(User user) {

        User oldUser=this.getById(user.getId());
        if(user.getPassword()!=null&&!user.getPassword().equals("")){
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        }else {
            user.setPassword(oldUser.getPassword());
        }
        if(user.getUsername()==null||user.getUsername().equals("")){
            user.setUsername(oldUser.getUsername());
        }
        if (user.getAccount() == null || user.getAccount().equals("")) {
            user.setAccount(oldUser.getAccount());
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            user.setPassword(oldUser.getPassword());
        }
        if (user.getGender() == null || user.getGender().equals("")) {
            user.setGender(oldUser.getGender());
        }
        if (user.getBudget() < EPSILON) {
            user.setBudget(oldUser.getBudget());
        }
        if (user.getMode() < EPSILON) {
            user.setMode(oldUser.getMode());
        }
        if (user.getRhythm() < EPSILON) {
            user.setRhythm(oldUser.getRhythm());
        }
        if (user.getCount() == 0) {
            user.setCount(oldUser.getCount());
        }

        return this.updateById(user) ? R.success("用户信息更新成功", this.getById(user.getId())) : R.error("用户信息更新失败");
    }
}
