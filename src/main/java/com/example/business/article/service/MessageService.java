package com.example.business.article.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.business.article.dto.request.AddMessageRequest;
import com.example.business.article.dto.request.QueryMessageRequest;
import com.example.business.article.dto.response.QueryMessageResponse;
import com.example.business.article.entity.Message;
import com.example.business.article.mapper.MessageMapper;
import com.example.business.user.entity.ApiResult;
import com.example.business.user.entity.User;
import com.example.business.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: liuwenpeng
 * @Date : 2023/3/27
 */
@Service
public class MessageService extends ServiceImpl<MessageMapper, Message> {

    @Resource
    private UserService userService;

    public void messageAdd(AddMessageRequest request){
        Date date = new Date();
        Message message = new Message();
        message.setArticleId(request.getArticle_id());
        message.setUserId(request.getUser_id());
        message.setContent(request.getMessage());
        message.setCreateDate(date);
        this.save(message);
    }

    public ApiResult<?> messageQuery(QueryMessageRequest request){
        LambdaQueryWrapper<Message> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Message::getArticleId,request.getArticle_id());
        if(request.getUser_id()!=null){
            wrapper.eq(Message::getUserId,request.getUser_id());
        }
        if(request.getSort()){
            wrapper.orderByAsc(Message::getCreateDate);
        }else {
            wrapper.orderByDesc(Message::getCreateDate);
        }
        List<Message> messages = this.list(wrapper);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<Long, String> user_map = userService.list().stream().collect(Collectors.toMap(User::getId, User::getName));
        List<QueryMessageResponse> responses = new ArrayList<>();
        for(Message message:messages){
            QueryMessageResponse response = new QueryMessageResponse();
            response.setContent(message.getContent());
            response.setId(message.getId());
            response.setUser_id(message.getUserId());
            response.setCreate_date(sdf.format(message.getCreateDate()));
            response.setUser(user_map.get(message.getUserId()));
            responses.add(response);
        }
        return ApiResult.ok(responses);
    }
}
