/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.sg.fidgetblog.dto.AdminEditPost;
import com.sg.fidgetblog.dto.AdminNotes;
import com.sg.fidgetblog.dto.Category;
import com.sg.fidgetblog.dto.Graphic;
import com.sg.fidgetblog.dto.NewPost;
import com.sg.fidgetblog.dto.Post;
import com.sg.fidgetblog.dto.StaticPage;
import com.sg.fidgetblog.dto.User;
import com.sg.fidgetblog.service.CategoryService;
import com.sg.fidgetblog.service.Category_PostService;
import com.sg.fidgetblog.service.CommentService;
import com.sg.fidgetblog.service.GraphicService;
import com.sg.fidgetblog.service.PostService;
import com.sg.fidgetblog.service.StaticPageService;
import com.sg.fidgetblog.service.UserService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vincentsiciliano
 */
@CrossOrigin
@Controller
public class AdminController {

    UserService userService;
    PostService postService;
    StaticPageService staticPageService;
    Category_PostService categoryPostService;
    CategoryService categoryService;
    GraphicService graphicService;
    CommentService commentService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Inject
    public AdminController(UserService userService, PostService postService,
            StaticPageService staticPageService, Category_PostService categoryPostService,
            CategoryService categoryService, GraphicService graphicService,
            CommentService commentService) {
        this.userService = userService;
        this.postService = postService;
        this.staticPageService = staticPageService;
        this.categoryPostService = categoryPostService;
        this.categoryService = categoryService;
        this.graphicService = graphicService;
        this.commentService = commentService;

    }

    @RequestMapping(value = "/adminhome", method = RequestMethod.GET)
    public String adminHome(Model model, HttpServletRequest request) {

        List<Post> postList = postService.readAllPosts(0);

        model.addAttribute("postList", postList);

        List<User> userList = userService.readAllUsers(0);

        List<Integer> commentList = userService.getNumCommentsForUserList(userList);
        Map<User, Integer> userCommentMap = userService.createUserCommentMap(userList, commentList);

        model.addAttribute("userCommentsMap", userCommentMap);

        model.addAttribute("userList", userList);

        List<StaticPage> staticPageList = staticPageService.readAllStaticPage(0);

        model.addAttribute("staticPageList", staticPageList);

        model.addAttribute("postIndex", 0);
        model.addAttribute("userIndex", 0);
        model.addAttribute("staticIndex", 0);

        return "adminhome";

    }

    @RequestMapping(value = "/admineditpostform/{id}", method = RequestMethod.GET)
    public String adminPostForm(Model model, @PathVariable("id") int postId) {

        Post post = postService.readPostById(postId);
        List<Post> posts = new ArrayList();
        posts.add(post);
        List<Graphic> header = graphicService.getGraphicByPostId(posts);
        Graphic graphic = header.get(0);

        model.addAttribute("imageId", graphic.getImageId());

        model.addAttribute("postId", post.getPostId());
        model.addAttribute("userId", post.getUser().getUserId());

        model.addAttribute("title", post.getTitle());
        model.addAttribute("userName", post.getUser().getUserName());
        model.addAttribute("postBody", post.getPostBody());
        model.addAttribute("startDate", post.getStartDate());
        model.addAttribute("endDate", post.getEndDate());

        Map<String, String> flagStrings = postService.convertFlagsToText(post);
        model.addAttribute("flagMap", flagStrings);
        model.addAttribute("imageFlag", post.getImageFlag());
        model.addAttribute("headerFlag", post.getHeaderFlag());
        model.addAttribute("titleFlag", post.getTitleFlag());
        model.addAttribute("authorFlag", post.getAuthorFlag());
        model.addAttribute("bodyFlag", post.getBodyFlag());
        model.addAttribute("startDateFlag", post.getStartDateFlag());
        model.addAttribute("endDateFlag", post.getEndDateFlag());
        model.addAttribute("categoryFlag", post.getCategoryFlag());

        List<User> userList = userService.readAllUsers(0);
        List<Category> categoryList = categoryService.getCategoriesByPostId(postId);
        String[] allCategories = categoryService.readAllCategories();

        model.addAttribute("allCategories", allCategories);
        model.addAttribute("categories", categoryList);
        model.addAttribute("userList", userList);

        return "admin-edit-post-form";

    }

    @RequestMapping(value = "/admincreatepost")
    public String adminCreatePost(@RequestParam("newPostCategories[]") String[] newPostCategories,
            @ModelAttribute("newPost") NewPost newPost, Principal principal,
            @RequestParam("graphic") MultipartFile graphicFile) {

        Post post = new Post();

        User user = userService.readUserByUsername(principal.getName());

        post.setUser(user);
        post.setStartDate(newPost.getNewPostStartDate());
        postService.setPostEndDate(newPost, post);
        post.setPostBody(newPost.getNewPostBody());
        post.setTitle(newPost.getNewPostTitle());

        post.setAuthorFlag(0);
        post.setBodyFlag(0);
        post.setCategoryFlag(0);
        post.setEndDateFlag(0);
        post.setImageFlag(0);
        post.setTitleFlag(0);
        post.setApprovalStatus(0);
        post.setHeaderFlag(0);
        post.setColorStatus("green");

        postService.createPostAdmin(post);

        //save header graphic with postId
        Graphic newGraphic = new Graphic();
        newGraphic.setImageId(graphicService.createGraphic(graphicFile));
        newGraphic.setPost(post);
        newGraphic.setUser(user);
        graphicService.updateGraphicPostId(newGraphic);

        //take newPostCategories string and separate into hashtags
        //add categories to DB
        categoryService.createCategoriesFromArray(newPostCategories);
        //create and Add Category_Post Bridge items
        categoryPostService.createMultipleCategoryPosts(newPostCategories, post);

        return "redirect:/adminhome";
    }

    @RequestMapping(value = "/makeadminedit", method = RequestMethod.POST)
    public String makeAdminEdit(@ModelAttribute AdminEditPost adminEditPost, HttpServletRequest request, AdminNotes notes) {

        Post post = new Post();

        String postBody = request.getParameter("post-Body");

        post.setPostId(adminEditPost.getPostId());
        post.setUserId(Integer.parseInt(adminEditPost.getUserId()));
        post.setTitle(adminEditPost.getTitle());
        post.setPostBody(adminEditPost.getPostBody());
        post.setStartDate(LocalDate.parse(adminEditPost.getStartDate(), formatter));
        postService.setPostEndDate(adminEditPost, post);
        post.setUser(userService.readUserById(Integer.parseInt(adminEditPost.getUserId())));

        post.setAuthorFlag(Integer.parseInt(adminEditPost.getAuthorFlag()));
        post.setBodyFlag(Integer.parseInt(adminEditPost.getBodyFlag()));
        post.setStartDateFlag(Integer.parseInt(adminEditPost.getStartDateFlag()));
        post.setEndDateFlag(Integer.parseInt(adminEditPost.getEndDateFlag()));
        post.setTitleFlag(Integer.parseInt(adminEditPost.getTitleFlag()));
        post.setImageFlag(Integer.parseInt(adminEditPost.getImageFlag()));

        post.setCategoryFlag(Integer.parseInt(adminEditPost.getCategoryFlag()));
        post.setHeaderFlag(Integer.parseInt(adminEditPost.getHeaderFlag()));

        post.setAdminComment(notes.toString());

        postService.updatePost(post);

        return "redirect:/adminhome";

    }

    @RequestMapping(value = "/staticpageform", method = RequestMethod.GET)
    public String staticPageForm(Model model) {

        return "staticpage-form";

    }

    @RequestMapping(value = "/createstaticpage", method = RequestMethod.POST)
    public String createStaticPage(@ModelAttribute StaticPage staticPage) {

        staticPageService.createStaticPage(staticPage);

        return "redirect:/adminhome";

    }

    @RequestMapping(value = "/changestaticpageorder", method = RequestMethod.POST)
    public String changeStaticPageOrder(HttpServletRequest request, Principal principal) {

        String[] staticPageOrderList = request.getParameterValues("staticPageOrderList");

        for (int i = 0; i < staticPageOrderList.length; i++) {

            StaticPage staticPage = staticPageService.readStaticPageById(Integer.parseInt(staticPageOrderList[i]));
            staticPage.setStaticPageIndex(i);
            staticPageService.updateStaticPage(staticPage);
        }

        return "redirect:/adminhome";

    }

    @RequestMapping(value = "/removestaticpage/{id}", method = RequestMethod.POST)
    public String removeStaticPage(@PathVariable("id") String postId, Principal principal) {

        staticPageService.deleteStaticPageById(Integer.parseInt(postId));

        return "redirect:/adminhome";
    }

    @RequestMapping(value = "/restorestaticpage/{id}", method = RequestMethod.POST)
    public String restoreStaticPage(@PathVariable("id") String postId, Principal principal) {

        staticPageService.restoreStaticPageById(Integer.parseInt(postId));

        return "redirect:/adminhome";
    }

    @RequestMapping(value = "/deactivateuser/{id}", method = RequestMethod.POST)
    public String removeUser(@PathVariable("id") String userId, Principal principal) {

        User user = userService.readUserById(Integer.parseInt(userId));

        userService.deleteUser(user.getUserName());

        return "redirect:/adminhome";
    }

    @RequestMapping(value = "/restoreuser/{id}", method = RequestMethod.POST)
    public String restoreUser(@PathVariable("id") String userId, Principal principal) {

        User user = userService.readUserById(Integer.parseInt(userId));
        user.setIsActive(true);
        userService.updateUser(user);

        return "redirect:/adminhome";
    }

    @RequestMapping(value = "/editstaticpageform/{id}", method = RequestMethod.GET)
    public String editStaticPageForm(Model model, @PathVariable("id") String staticPageId) {

        StaticPage staticPage = staticPageService.readStaticPageById(Integer.parseInt(staticPageId));

        model.addAttribute("staticPageTitle", staticPage.getStaticPageTitle());
        model.addAttribute("staticPageBody", staticPage.getStaticPageBody());

        return "admin-edit-static-page-form";
    }

    @RequestMapping(value = "/updatestaticpage", method = RequestMethod.POST)
    public String editStaticPage(@ModelAttribute StaticPage staticPage) {

        staticPageService.updateStaticPage(staticPage);

        return "redirect:/adminhome";
    }

    @RequestMapping(value = "/admin/update/pages/{userIndex}/{postIndex}/{staticIndex}", method = RequestMethod.GET)
    public String userPagination(Model model, @PathVariable("userIndex") int userIndex,
            @PathVariable("postIndex") int postIndex,
            @PathVariable("staticIndex") int staticIndex) {

        int userQuery = userIndex * 20;
        int postQuery = postIndex * 10;
        int staticQuery = staticIndex * 20;

        List<User> userList = userService.readAllUsers(userQuery);
        List<Post> postList = postService.readAllPosts(postQuery);
        List<Integer> commentList = userService.getNumCommentsForUserList(userList);
        Map<User, Integer> userCommentMap = userService.createUserCommentMap(userList, commentList);
        List<StaticPage> staticPageList = staticPageService.readAllStaticPage(staticQuery);

        model.addAttribute("userCommentsMap", userCommentMap);
        model.addAttribute("userList", userList);
        model.addAttribute("postList", postList);
        model.addAttribute("staticPageList", staticPageList);

        model.addAttribute("userIndex", userIndex);
        model.addAttribute("postIndex", postIndex);
        model.addAttribute("staticIndex", staticIndex);

        return "adminhome";
    }

}
