package com.example.business.article.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.business.article.entity.Article;
import com.example.business.article.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/27
 */
@Service
public class ArticleService extends ServiceImpl<ArticleMapper, Article> {
}
