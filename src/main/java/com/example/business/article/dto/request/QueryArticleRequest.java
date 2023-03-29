package com.example.business.article.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/29
 */
@Getter
@Setter
public class QueryArticleRequest {
    /**
     * 标题
     */
    private String name;

    private Boolean is_enable;
    /**
     * 页码
     */
    private Integer page;
    /**
     * 页大小
     */
    private Integer page_size;

    private Long label_id;

    private String author;

    private Long type_id;
}
