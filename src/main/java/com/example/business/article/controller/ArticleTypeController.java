package com.example.business.article.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.business.article.dto.request.QueryNameRequest;
import com.example.business.article.entity.Article;
import com.example.business.article.entity.ArticleType;
import com.example.business.article.service.ArticleService;
import com.example.business.article.service.ArticleTypeService;
import com.example.business.user.entity.ApiResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/29
 */
@RestController
public class ArticleTypeController {
    //文章类型控制器
    @Resource
    private ArticleTypeService articleTypeService;

    @Resource
    private ArticleService articleService;

    @RequestMapping("/article_type/add/{name}")
    public ApiResult<?> add(@PathVariable String name) {
        LambdaQueryWrapper<ArticleType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleType::getName,name);
        List<ArticleType> list = articleTypeService.list(wrapper);
        if(list.size()!=0){
            return ApiResult.fail("文章类型已存在");
        }
        ArticleType articleType = new ArticleType();
        articleType.setName(name);
        articleTypeService.save(articleType);
        return ApiResult.ok("添加文章类型成功");
    }

    @RequestMapping("/article_type/query")
    public ApiResult<?> query(@Valid @RequestBody QueryNameRequest request) {
        return articleTypeService.query(request);
    }

    @RequestMapping("/article_type/delete/{id}")
    public ApiResult<?> add(@PathVariable Long id) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getTypeId,id);
        List<Article> list = articleService.list(wrapper);
        if(list.size()!=0){
            return ApiResult.ok("文章类型正在被使用，无法删除");
        }
        articleTypeService.removeById(id);
        return ApiResult.ok("删除文章类型成功");
    }
}
