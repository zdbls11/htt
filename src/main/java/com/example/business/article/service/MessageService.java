package com.example.business.article.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.business.article.entity.Message;
import com.example.business.article.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/27
 */
@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> {
}
