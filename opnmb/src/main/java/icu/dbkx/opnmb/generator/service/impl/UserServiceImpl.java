package icu.dbkx.opnmb.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.dbkx.opnmb.common.pojo.dto.RegisterDto;
import icu.dbkx.opnmb.generator.entity.User;
import icu.dbkx.opnmb.generator.service.UserService;
import icu.dbkx.opnmb.generator.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Yumei
* @description 针对表【nmb_user】的数据库操作Service实现
* @createDate 2024-05-20 14:14:07
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    public Integer register(RegisterDto registerDto){
        User user = new User(registerDto);
        this.save(user);
        return user.getUser_id();
    }
}




