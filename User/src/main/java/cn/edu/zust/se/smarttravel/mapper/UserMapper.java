package cn.edu.zust.se.smarttravel.mapper;

import cn.edu.zust.se.smarttravel.entity.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
