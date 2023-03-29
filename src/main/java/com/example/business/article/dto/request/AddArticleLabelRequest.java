package com.example.business.article.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/29
 */
@Getter
@Setter
public class AddArticleLabelRequest {
    private Long article_id;
    private List<Long> label_ids;
}
