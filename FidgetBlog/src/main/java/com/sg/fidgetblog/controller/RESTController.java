/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sg.fidgetblog.dto.StaticPage;
import com.sg.fidgetblog.service.StaticPageService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vincentsiciliano
 */

@CrossOrigin
@Controller
public class RESTController {
    
    
    StaticPageService staticPageService;
    
    @Inject
    public RESTController(StaticPageService staticPageService){
        this.staticPageService = staticPageService;
    }
    
    
    
    @RequestMapping(value = "/getstaticpages", method = RequestMethod.GET)
    @ResponseBody
    public String loadStaticPagesinNavBar(Model model, HttpServletResponse response){
        
        List<StaticPage> staticPageList = staticPageService.readAllStaticPage(0);
        
        Gson gson = new GsonBuilder().create();
        JsonArray myCustomArray = gson.toJsonTree(staticPageList).getAsJsonArray();
       
        return myCustomArray.toString();
        
    }
    
}