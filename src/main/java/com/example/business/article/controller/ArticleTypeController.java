package com.example.business.article.controller;

import com.example.business.article.dto.request.QueryNameRequest;
import com.example.business.article.entity.ArticleType;
import com.example.business.article.service.ArticleTypeService;
import com.example.business.user.entity.ApiResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/29
 */
public class ArticleTypeController {
    //文章类型控制器
    @Resource
    private ArticleTypeService articleTypeService;

    @RequestMapping("/article_type/add/{name}")
    public ApiResult<?> add(@PathVariable String name) {
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
        articleTypeService.removeById(id);
        return ApiResult.ok("删除文章类型成功");
    }
}
