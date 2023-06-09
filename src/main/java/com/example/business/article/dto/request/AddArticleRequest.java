package com.example.business.article.dto.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/29
 */
@Getter
@Setter
public class AddArticleRequest {
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
    /**
     * 上传用户id
     */
    private Long user_id;
}
