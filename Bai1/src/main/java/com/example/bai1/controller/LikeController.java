package com.example.bai1.controller;

import com.example.bai1.dto.LikeDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping("/like")
@Controller
public class LikeController {
    @GetMapping
    public ModelAndView showPlayerLike(@SessionAttribute(value = "like",required = false)LikeDto likeDto){
        return new ModelAndView("like/list_like","like",likeDto);
    }
}
