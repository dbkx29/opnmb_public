package icu.dbkx.opnmb.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.dbkx.opnmb.generator.entity.Category;
import icu.dbkx.opnmb.generator.service.CategoryService;
import icu.dbkx.opnmb.generator.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author Yumei
* @description 针对表【nmb_category】的数据库操作Service实现
* @createDate 2024-05-23 00:46:13
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




