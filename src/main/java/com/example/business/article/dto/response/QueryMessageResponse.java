package com.example.business.article.dto.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: liuwenpeng
 * @Date : 2023/4/18
 */
@Getter
@Setter
public class QueryMessageResponse {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论人
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long user_id;

    private String user;
    /**
     * 评论时间
     */
    private String create_date ;
}
