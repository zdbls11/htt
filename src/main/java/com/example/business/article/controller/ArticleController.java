package com.example.business.article.controller;

import com.example.business.article.dto.request.*;
import com.example.business.article.service.ArticleService;
import com.example.business.article.service.MessageService;
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

    @Resource
    private MessageService messageService;

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

    @RequestMapping("/article/is_enable")
    public ApiResult<?> is_enable(@Valid @RequestBody IsEnableRequest request) {
        articleService.is_enable(request);
        return ApiResult.ok();
    }


    //发表评论
    @RequestMapping("/message/add")
    public ApiResult<?> messageAdd(@Valid @RequestBody AddMessageRequest request) {
        messageService.messageAdd(request);
        return ApiResult.ok();
    }

    //查询评论
    @RequestMapping("/message/query")
    public ApiResult<?> messageQuery(@Valid @RequestBody QueryMessageRequest request) {
        return messageService.messageQuery(request);
    }

    @RequestMapping("/message/delete/{id}")
    public ApiResult<?> messageDelete(@PathVariable Long id) {
        messageService.removeById(id);
        return ApiResult.ok();
    }
}
