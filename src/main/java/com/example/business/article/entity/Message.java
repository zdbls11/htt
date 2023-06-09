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
import java.util.Date;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/27
 */
@TableName("`message`")
@Getter
@Setter
public class Message implements Serializable {
    @TableId(value = "`id`", type = IdType.ASSIGN_ID)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 评论内容
     */
    @TableField("`content`")
    private String content;
    /**
     * 文章id
     */
    @TableField("`article_id`")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long articleId;
    /**
     * 评论人id
     */
    @TableField("`user_id`")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;
    /**
     * 评论时间
     */
    @TableField("`create_date`")
    private Date createDate = new Date();
}
