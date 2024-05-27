package icu.dbkx.opnmb.generator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import icu.dbkx.opnmb.common.utils.BiscuitUtil;
import icu.dbkx.opnmb.generator.entity.Biscuit;
import icu.dbkx.opnmb.generator.service.BiscuitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@ExtendWith(SpringExtension.class)
class BiscuitServiceImplTest {
    @Autowired
    BiscuitService biscuitService;

    @Test
    void generateBiscuitId() {
        System.out.println(BiscuitUtil.generateBiscuitId());
    }
    @Test
    void selectBiscuit() {
        biscuitService.list(new LambdaQueryWrapper<Biscuit>().eq(Biscuit::getUser_id, 106));
    }

}