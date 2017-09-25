/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dto.Graphic;
import com.sg.fidgetblog.dto.Post;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jono
 */
public interface GraphicService {

    public int createGraphic(MultipartFile graphicFile);

    public Graphic readGraphicById(int graphicId);

    public void deleteGraphicById(int graphicId);

    public void updateGraphicPostId(Graphic newGraphic);

    public List<Graphic> getGraphicByPostId(List<Post> postList);
}
