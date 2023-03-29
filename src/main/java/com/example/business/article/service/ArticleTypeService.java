package com.example.business.article.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.business.article.dto.request.QueryNameRequest;
import com.example.business.article.entity.ArticleType;
import com.example.business.article.mapper.ArticleTypeMapper;
import com.example.business.user.entity.ApiResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/27
 */
@Service
public class ArticleTypeService extends ServiceImpl<ArticleTypeMapper, ArticleType> {

    public ApiResult<?> query(QueryNameRequest request) {
        LambdaQueryWrapper<ArticleType> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(request.getName())) {
            wrapper.like(ArticleType::getName, request.getName());
        }
        if (request.getIs_enable() != null) {
            wrapper.eq(ArticleType::getIsEnable, request.getIs_enable());
        }
        long count = this.count(wrapper);
        Page<ArticleType> page = PageHelper.startPage(request.getPage(), request.getPage_size());
        List<ArticleType> articleTypes = this.list(wrapper);
        page.close();
        return ApiResult.ok(articleTypes, String.valueOf(count));
    }
}
