package com.example.business.article.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.business.article.dto.request.AddArticleLabelRequest;
import com.example.business.article.entity.ArticleLabel;
import com.example.business.article.mapper.ArticleLabelMapper;
import com.example.business.user.entity.ApiResult;
import org.springframework.stereotype.Service;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/27
 */
@Service
public class ArticleLabelService extends ServiceImpl<ArticleLabelMapper, ArticleLabel> {

    public void addArticleLabel(AddArticleLabelRequest request) {
        LambdaQueryWrapper<ArticleLabel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleLabel::getArticleId, request.getArticle_id());
        this.remove(wrapper);

        for (Long label_id : request.getLabel_ids()) {
            ArticleLabel articleLabel = new ArticleLabel();
            articleLabel.setArticleId(request.getArticle_id());
            articleLabel.setLabelId(label_id);
            this.save(articleLabel);
        }
        ApiResult.ok();
    }
}
