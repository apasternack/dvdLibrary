/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author apprentice
 */
public class Dvd {

    private int id;
    @NotEmpty(message = "You must supply a DVD title")
    private String title;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy", timezone = "EST")  //this dictates the format of the JSON date string when converting from Java Date object
    private Date releaseDate;
    @NotEmpty(message = "You must supply a DVD rating")
    private String rating;
    @NotEmpty(message = "You must supply a director name")
    private String director;
    @NotEmpty(message = "You must supply a studio name")
    private String studio;
    private String userNote;

    public Dvd() {
    }

    public Dvd(String title, String rating, String director, String studio) {
        this.title = title;
        this.rating = rating;
        this.director = director;
        this.studio = studio;
        userNote = "";
    }

    public Dvd(String title, String rating, String director, String studio, String userNote) {
        this.title = title;
        this.rating = rating;
        this.director = director;
        this.studio = studio;
        this.userNote = userNote;
    }

    public String searchBy(String field) {

        switch (field) {

            case "title":
                return getTitle();
            case "releaseDate":
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                return dateFormat.format(releaseDate);
            case "director":
                return getDirector();
            case "rating":
                return getRating();
            case "studio":
                return getStudio();
        }
        return null;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

}
