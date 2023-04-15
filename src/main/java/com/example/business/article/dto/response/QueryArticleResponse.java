package com.example.business.article.dto.response;

import com.example.business.article.entity.Label;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/29
 */
@Getter
@Setter
public class QueryArticleResponse {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 标题
     */
    private String name;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 是否启用
     */
    private Boolean is_enable ;
    /**
     * 作者
     */
    private String author;
    /**
     * 封面配图路径
     */
    private String icon;

    /**
     * 文章分类id
     */
    private Long type_id;

    private String type;
    /**
     * 上传用户id
     */
    private Long user_id;

    private String user;

    private List<Label> labels;
}
