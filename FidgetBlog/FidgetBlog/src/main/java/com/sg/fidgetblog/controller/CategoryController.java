/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.sg.fidgetblog.dto.Graphic;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.service.CategoryService;
import com.sg.fidgetblog.service.GraphicService;
import com.sg.fidgetblog.service.PostService;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vincentsiciliano
 */
@Controller
public class CategoryController {

    PostService postService;
    CategoryService categoryService;
    GraphicService graphicService;

    @Inject
    public CategoryController(PostService postService, CategoryService categoryService, GraphicService graphicService) {
        this.postService = postService;
        this.categoryService = categoryService;
        this.graphicService = graphicService;
    }

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public String loginToPostComment(@PathVariable("categoryId") String categoryId, Model model) {

        List<Post> posts = postService.readPostsByCategoryId(categoryId, 0);
        List<Graphic> headerList = graphicService.getGraphicByPostId(posts);
        Map<Post, Graphic> postHeaderMap = postService.createPostHeaderMap(posts, headerList);

        model.addAttribute("postHeaderMap", postHeaderMap);

        return "jinjahome";
    }

}
