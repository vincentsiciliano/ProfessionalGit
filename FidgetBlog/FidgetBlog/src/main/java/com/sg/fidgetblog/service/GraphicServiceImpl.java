/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.service;

import com.sg.fidgetblog.dao.GraphicDao;
import com.sg.fidgetblog.dto.Graphic;
import com.sg.fidgetblog.dto.Post;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jono
 */
public class GraphicServiceImpl implements GraphicService {

    GraphicDao graphicDao;

    @Inject
    public GraphicServiceImpl(GraphicDao graphicDao) {
        this.graphicDao = graphicDao;
    }

    @Override
    public int createGraphic(MultipartFile graphic) {
        return graphicDao.createGraphic(graphic);
    }

    @Override
    public Graphic readGraphicById(int graphicId) {
        return graphicDao.readGraphicById(graphicId);
    }

    @Override
    public void deleteGraphicById(int graphicId) {
        graphicDao.deleteGraphicById(graphicId);
    }

    @Override
    public void updateGraphicPostId(Graphic newGraphic) {
        graphicDao.updateGraphicPostId(newGraphic);
    }

    @Override
    public List<Graphic> getGraphicByPostId(List<Post> postList) {
        List<Graphic> graphics = new ArrayList<>();

        for (Post post : postList) {
            Graphic graphic = graphicDao.readGraphicByPostId(post.getPostId());
            graphics.add(graphic);
        }
        return graphics;

    }

}
