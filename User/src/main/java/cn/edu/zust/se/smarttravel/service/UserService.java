package cn.edu.zust.se.smarttravel.service;

import cn.edu.zust.se.smarttravel.entity.R;
import cn.edu.zust.se.smarttravel.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    User addUser(User user);

    User login(User user);

    R<User> getUserById(Long id);

    R<User> updateUserById(User user);
}
