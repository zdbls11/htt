package com.example.business.article.controller;

import com.example.business.article.dto.request.AddArticleRequest;
import com.example.business.article.dto.request.QueryArticleRequest;
import com.example.business.article.service.ArticleService;
import com.example.business.user.entity.ApiResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/29
 */
@RestController
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @RequestMapping("/article/add")
    public ApiResult<?> add(@Valid @RequestBody AddArticleRequest request) {
        articleService.add(request);
        return ApiResult.ok();
    }

    @RequestMapping("/article/update")
    public ApiResult<?> update(@Valid @RequestBody AddArticleRequest request) {
        articleService.update(request);
        return ApiResult.ok();
    }

    @RequestMapping("/article/delete/{id}")
    public ApiResult<?> delete(@PathVariable Long id) {
        articleService.removeById(id);
        return ApiResult.ok();
    }

    @RequestMapping("/article/query")
    public ApiResult<?> query(@Valid @RequestBody QueryArticleRequest request) {
        return articleService.query(request);
    }
}