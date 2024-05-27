package icu.dbkx.opnmb.controller.api;

import icu.dbkx.opnmb.common.pojo.Result;
import icu.dbkx.opnmb.generator.entity.Category;
import icu.dbkx.opnmb.generator.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/category")
@Slf4j
public class CategoryController {
    @Resource
    CategoryService categoryService;

    /**
     * 查询所有分类
     */
    @GetMapping("/list")
    public Result<List<Category>> listCategory() {
        Result<List<Category>> result = Result.success("/api/category/list");
        result.setData(categoryService.list());
        return result;
    }
}
