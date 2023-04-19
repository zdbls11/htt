package com.example.business.article.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.business.article.dto.request.AddArticleRequest;
import com.example.business.article.dto.request.IsEnableRequest;
import com.example.business.article.dto.request.QueryArticleRequest;
import com.example.business.article.dto.response.QueryArticleResponse;
import com.example.business.article.entity.Article;
import com.example.business.article.entity.ArticleLabel;
import com.example.business.article.entity.ArticleType;
import com.example.business.article.entity.Label;
import com.example.business.article.mapper.ArticleMapper;
import com.example.business.user.entity.ApiResult;
import com.example.business.user.entity.User;
import com.example.business.user.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/27
 */
@Service
public class ArticleService extends ServiceImpl<ArticleMapper, Article> {

    @Resource
    private ArticleLabelService articleLabelService;

    @Resource
    private LabelService labelService;

    @Resource
    private ArticleTypeService articleTypeService;

    @Resource
    private UserService userService;

    public void add(AddArticleRequest request) {
        Article article = new Article();
        article.setName(request.getName());
        article.setContent(request.getContent());
        article.setAuthor(request.getAuthor());
        article.setIcon(request.getIcon());
        article.setTypeId(request.getType_id());
        article.setUserId(request.getUser_id());
        this.save(article);
    }

    public void update(AddArticleRequest request) {
        Article article = this.getById(request.getId());
        article.setName(request.getName());
        article.setContent(request.getContent());
        article.setAuthor(request.getAuthor());
        article.setIcon(request.getIcon());
        article.setTypeId(request.getType_id());
        article.setUserId(request.getUser_id());
        this.updateById(article);
    }

    public ApiResult<?> query(QueryArticleRequest request) {
        List<QueryArticleResponse> responses = new ArrayList<>();
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        Map<Long, String> type_map = articleTypeService.list().stream().collect(Collectors.toMap(ArticleType::getId, ArticleType::getName));
        Map<Long, String> user_map = userService.list().stream().collect(Collectors.toMap(User::getId, User::getName));

        if (request.getIs_enable() != null) wrapper.eq(Article::getIsEnable, request.getIs_enable());
        if (StringUtils.isNotBlank(request.getName())) wrapper.like(Article::getName, request.getName());
        if (StringUtils.isNotBlank(request.getAuthor())) wrapper.like(Article::getAuthor, request.getAuthor());
        if (request.getType_id() != null) wrapper.eq(Article::getTypeId, request.getType_id());
        if (request.getUser_id() != null) wrapper.eq(Article::getUserId, request.getUser_id());
        if (request.getLabel_id() != null) {
            LambdaQueryWrapper<ArticleLabel> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ArticleLabel::getLabelId, request.getLabel_id());
            List<ArticleLabel> articleLabels = articleLabelService.list(lambdaQueryWrapper);
            if (articleLabels.size() != 0) {
                List<Long> article_ids = articleLabels.stream().map(ArticleLabel::getArticleId).collect(Collectors.toList());
                wrapper.in(Article::getId, article_ids);
            } else {
                return ApiResult.ok(responses, String.valueOf(0));
            }
        }
        int count = this.list(wrapper).size();
        Page<Article> page = PageHelper.startPage(request.getPage(), request.getPage_size());
        List<Article> articles = this.list(wrapper);
        page.close();
        for (Article article : articles) {
            QueryArticleResponse response = new QueryArticleResponse();
            response.setId(article.getId());
            response.setName(article.getName());
            response.setContent(article.getContent());
            response.setIs_enable(article.getIsEnable());
            response.setAuthor(article.getAuthor());
            response.setIcon(article.getIcon());
            response.setType_id(article.getTypeId());
            response.setUser_id(article.getUserId());

            LambdaQueryWrapper<ArticleLabel> wrapper1 = new LambdaQueryWrapper<>();
            wrapper1.eq(ArticleLabel::getArticleId, article.getId());
            List<ArticleLabel> articleLabels = articleLabelService.list(wrapper1);
            if (articleLabels.size() != 0) {
                List<Long> label_ids = articleLabels.stream().map(ArticleLabel::getLabelId).collect(Collectors.toList());
                LambdaQueryWrapper<Label> labelWrapper = new LambdaQueryWrapper<>();
                labelWrapper.in(Label::getId, label_ids);
                List<Label> labels = labelService.list(labelWrapper);
                response.setLabels(labels);
            } else {
                response.setLabels(new ArrayList<>());
            }
            response.setType(type_map.get(article.getTypeId()));
            response.setUser(user_map.get(article.getUserId()));
            responses.add(response);
        }
        return ApiResult.ok(responses, String.valueOf(count));
    }

    public void is_enable(IsEnableRequest request) {
        for (Long id : request.getArticle_ids()) {
            Article article = this.getById(id);
            article.setIsEnable(request.getIs_enable());
            this.updateById(article);
        }
    }
}
