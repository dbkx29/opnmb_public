package icu.dbkx.opnmb.generator.service;

import icu.dbkx.opnmb.common.pojo.dto.RegisterDto;
import icu.dbkx.opnmb.generator.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Yumei
* @description 针对表【nmb_user】的数据库操作Service
* @createDate 2024-05-20 14:14:07
*/
public interface UserService extends IService<User> {
    Integer register(RegisterDto registerDto);
}
