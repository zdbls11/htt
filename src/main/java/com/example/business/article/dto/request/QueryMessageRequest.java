package com.example.business.article.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: liuwenpeng
 * @Date : 2023/4/18
 */
@Getter
@Setter
public class QueryMessageRequest {
    private Long article_id;

    private Long user_id;

    //是否正序
    private Boolean sort;
}
