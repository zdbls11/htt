package com.example.business.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/27
 */
@TableName("`article_type`")
@Getter
@Setter
public class ArticleType implements Serializable {
    @TableId(value = "`id`", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 文章分类名称
     */
    @TableField("`name`")
    private String name;
    /**
     * 是否启用
     */
    @TableField("`is_enable`")
    private Boolean isEnable = true;
}
