package com.example.business.article.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.business.article.dto.request.QueryNameRequest;
import com.example.business.article.entity.Label;
import com.example.business.article.mapper.LabelMapper;
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
public class LabelService extends ServiceImpl<LabelMapper, Label> {

    public ApiResult<?> query(QueryNameRequest request) {
        LambdaQueryWrapper<Label> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(request.getName())) {
            wrapper.like(Label::getName, request.getName());
        }
        long count = this.count(wrapper);
        Page<Label> page = PageHelper.startPage(request.getPage(), request.getPage_size());
        List<Label> labels = this.list(wrapper);
        page.close();
        return ApiResult.ok(labels, String.valueOf(count));
    }
}
