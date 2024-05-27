package icu.dbkx.opnmb.generator.service;

import icu.dbkx.opnmb.generator.entity.Biscuit;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Yumei
* @description 针对表【nmb_biscuit】的数据库操作Service
* @createDate 2024-05-20 14:59:34
*/
public interface BiscuitService extends IService<Biscuit> {
    public Biscuit createBiscuit(Integer user_id);
}
