/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.fidgetblog.dto;

/**
 *
 * @author vincentsiciliano
 */
public class AdminNotes {

    private String headerNote;
    private String titleNote;
    private String authorNote;
    private String startDateNote;
    private String endDateNote;
    private String bodyNote;
    private String bodyImageNote;
    private String categoriesNote;

    public String getCategoriesNote() {
        return categoriesNote;
    }

    public void setCategoriesNote(String categoriesNote) {
        this.categoriesNote = categoriesNote;
    }

    public String getHeaderNote() {
        return headerNote;
    }

    public void setHeaderNote(String headerNote) {
        this.headerNote = headerNote;
    }

    public String getTitleNote() {
        return titleNote;
    }

    public void setTitleNote(String titleNote) {
        this.titleNote = titleNote;
    }

    public String getAuthorNote() {
        return authorNote;
    }

    public void setAuthorNote(String authorNote) {
        this.authorNote = authorNote;
    }

    public String getStartDateNote() {
        return startDateNote;
    }

    public void setStartDateNote(String startDateNote) {
        this.startDateNote = startDateNote;
    }

    public String getEndDateNote() {
        return endDateNote;
    }

    public void setEndDateNote(String endDateNote) {
        this.endDateNote = endDateNote;
    }

    public String getBodyNote() {
        return bodyNote;
    }

    public void setBodyNote(String bodyNote) {
        this.bodyNote = bodyNote;
    }

    public String getBodyImageNote() {
        return bodyImageNote;
    }

    public void setBodyImageNote(String bodyImageNote) {
        this.bodyImageNote = bodyImageNote;
    }

    @Override
    public String toString() {
        return "Header: " + getHeaderNote() + " Title: " + getTitleNote()
                + " Author: " + getAuthorNote() + " Start Date: " + getStartDateNote() + " End Date: " + getEndDateNote()
                + " Body: " + getBodyNote() + " Body Images: " + getBodyImageNote()
                + " Categories: " + getCategoriesNote();
    }

}
