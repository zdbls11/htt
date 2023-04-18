package com.example.business.article.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: liuwenpeng
 * @Date : 2023/4/18
 */
@Getter
@Setter
public class AddMessageRequest {
    private Long article_id;

    private Long user_id;

    private String message;
}
