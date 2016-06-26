/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Dvd;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface DvdDao {

    Dvd create(Dvd dvd);

    void delete(Integer id);

    Dvd get(Integer id);

    List<Dvd> getList();

    void update(Dvd dvd);

    public List<Dvd> searchByDate(Date date);

    public List<Dvd> searchBy(String attributeType, String attributeValue);

    public Map<String, List<Dvd>> sortByDirectorRating(String director);

    public double calculateAverageDvdAge();

    public Dvd getNewestDvd();

    public Dvd getOldestDvd();

}
