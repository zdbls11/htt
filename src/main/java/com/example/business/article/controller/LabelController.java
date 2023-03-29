package com.example.business.article.controller;

import com.example.business.article.dto.request.AddArticleLabelRequest;
import com.example.business.article.dto.request.QueryNameRequest;
import com.example.business.article.entity.Label;
import com.example.business.article.service.ArticleLabelService;
import com.example.business.article.service.LabelService;
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
public class LabelController {
    //标签控制器
    @Resource
    private LabelService labelService;

    @Resource
    private ArticleLabelService articleLabelService;

    //标签接口
    @RequestMapping("/label/add/{name}")
    public ApiResult<?> add(@PathVariable String name) {
        Label label = new Label();
        label.setName(name);
        labelService.save(label);
        return ApiResult.ok("添加标签成功");
    }

    @RequestMapping("/label/query")
    public ApiResult<?> query(@Valid @RequestBody QueryNameRequest request) {
        return labelService.query(request);
    }

    @RequestMapping("/label/delete/{id}")
    public ApiResult<?> add(@PathVariable Long id) {
        labelService.removeById(id);
        return ApiResult.ok("删除标签成功");
    }

    //文章关联标签接口
    @RequestMapping("/article_label/add")
    public ApiResult<?> addArticleLabel(@Valid @RequestBody AddArticleLabelRequest request) {
        articleLabelService.addArticleLabel(request);
        return ApiResult.ok();
    }
}
