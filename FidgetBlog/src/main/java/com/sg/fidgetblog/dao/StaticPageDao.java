/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dao;

import com.sg.fidgetblog.dto.StaticPage;
import java.util.List;

/**
 *
 * @author jono
 */
public interface StaticPageDao {

    public void createStaticPage(StaticPage staticPage);

    public StaticPage readStaticPageById(int staticPageId);

    public List<StaticPage> readAllStaticPage(int numChoice);

    public void updateStaticPageOrder(String[] staticPageOrderList);

    public void updateStaticPage(StaticPage staticPage);

    public void deleteStaticPageById(int staticPageId);

    public void restoreStaticPageById(int staticPageId);
}
