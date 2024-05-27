package icu.dbkx.opnmb.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.dbkx.opnmb.common.utils.BiscuitUtil;
import icu.dbkx.opnmb.generator.entity.Biscuit;
import icu.dbkx.opnmb.generator.service.BiscuitService;
import icu.dbkx.opnmb.generator.mapper.BiscuitMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

import static icu.dbkx.opnmb.common.utils.BiscuitUtil.generateBiscuitId;

/**
* @author Yumei
* @description 针对表【nmb_biscuit】的数据库操作Service实现
* @createDate 2024-05-20 14:59:34
*/
@Service
@Slf4j
public class BiscuitServiceImpl extends ServiceImpl<BiscuitMapper, Biscuit>
    implements BiscuitService{

    public Biscuit createBiscuit(Integer user_id){
        Biscuit biscuit = Biscuit.builder()
                .biscuit_id(generateBiscuitId())
                .user_id(user_id)
                .activate(1)
                .build();
        save(biscuit);
        return biscuit;
    }

}




