package com.fastcampus.sns.controller;

import com.fastcampus.sns.controller.reqeust.PostCreateRequest;
import com.fastcampus.sns.controller.response.Response;
import com.fastcampus.sns.service.PostService;
import lombok.RequiredArgsConstructor;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    @PostMapping
    public Response<Void> create(@RequestBody PostCreateRequest request, Authentication authentication){
        // 여기서 username을 받아오기 위해서 spring security를 이용하겠다.
        postService.create(request.getTitle(), request.getBody(), authentication.getName());
        return Response.success();
    }
}
