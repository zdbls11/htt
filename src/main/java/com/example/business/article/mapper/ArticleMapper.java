package com.example.business.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.business.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/27
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
