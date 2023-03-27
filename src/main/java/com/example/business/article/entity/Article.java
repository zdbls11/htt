package com.example.business.article.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/27
 */
@TableName("`article`")
@Getter
@Setter
public class Article implements Serializable {
    @TableId(value = "`id`", type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 标题
     */
    @TableField("`name`")
    private String name;
    /**
     * 文章内容
     */
    @TableField("`content`")
    private String content;
    /**
     * 是否启用
     */
    @TableField("`is_enable`")
    private Boolean isEnable = true;
    /**
     * 作者
     */
    @TableField("`author`")
    private String author;
    /**
     * 封面配图路径
     */
    @TableField("`icon`")
    private String icon;

    /**
     * 文章分类id
     */
    @TableField("`type_id`")
    private Long typeId;
    /**
     * 上传用户id
     */
    @TableField("`user_id`")
    private Long userId;
}
