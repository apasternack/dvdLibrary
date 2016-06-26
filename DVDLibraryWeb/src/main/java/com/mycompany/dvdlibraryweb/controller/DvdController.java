/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.controller;

import com.mycompany.dvdlibraryweb.dao.DvdDao;
import com.mycompany.dvdlibraryweb.dto.Dvd;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/dvd")
public class DvdController {

    private DvdDao dvdDao;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Inject
    public DvdController(DvdDao dao) {
        this.dvdDao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public Dvd create(@Valid @RequestBody Dvd dvd) {

        return dvdDao.create(dvd);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") Integer contactId) {

        dvdDao.delete(contactId);

    }

//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public String edit(@PathVariable("id") Integer dvdId, Map model) {
//
//        Dvd dvd = dvdDao.get(dvdId);
//
//        model.put("dvd", dvd);
//
//        return "edit";
//    }
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    @ResponseBody
    public Dvd editSubmit(@Valid @RequestBody Dvd dvd) {

        dvdDao.update(dvd);

        return dvd;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Dvd show(@PathVariable("id") Integer contactId) {

        Dvd dvd = dvdDao.get(contactId);

        return dvd;

    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search() {

        return "search";

    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchSubmit(@RequestParam("search") String search, @RequestParam("dateSearch") String dateSearch, @RequestParam("searchBy") String searchBy, Map model) {

        List<Dvd> searchResults;
        Date searchDate = null;

        if ("releaseDate".equals(searchBy)) {

            try {
                searchDate = dateFormat.parse(dateSearch);
            } catch (ParseException ex) {
                Logger.getLogger(DvdController.class.getName()).log(Level.SEVERE, null, ex);
            }

            searchResults = dvdDao.searchByDate(searchDate);

        } else {

            searchResults = dvdDao.searchBy(searchBy, search);

        }

        model.put("searchResults", searchResults);

        return "search";
    }

//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(@Valid @ModelAttribute("dvd") Dvd dvd, BindingResult bindingResult, Map model) {
//
//        if (bindingResult.hasErrors()) {
//            model.put("dvd", dvd); //passing info to the jsp, home.jsp
//            return "home"; //NOT redirect since we want to see the error messages which will be gone if we refresh with a redirect
//
//        } else {
//
//            dvdDao.create(dvd);
//
//            return "redirect:/";
//        }
//    }
//
//    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//    public String delete(@PathVariable("id") Integer contactId) {
//
//        dvdDao.delete(contactId);
//
//        return "redirect:/";
//
//    }
//
//    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//    public String edit(@PathVariable("id") Integer dvdId, Map model) {
//
//        Dvd dvd = dvdDao.get(dvdId);
//
//        model.put("dvd", dvd);
//
//        return "edit";
//    }
//
//    @RequestMapping(value = "/edit", method = RequestMethod.POST)
//    public String editSubmit(@ModelAttribute Dvd dvd) {
//
//        dvdDao.update(dvd);
//
//        return "redirect:/";
//
//    }
//
//    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
//    public String show(@PathVariable("id") Integer contactId, Map model) {
//
//        Dvd dvd = dvdDao.get(contactId);
//
//        model.put("dvd", dvd);
//
//        return "show";
//
//    }
//
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public String search() {
//
//        return "search";
//
//    }
//
//    @RequestMapping(value = "/search", method = RequestMethod.POST)
//    public String searchSubmit(@RequestParam("search") String search, @RequestParam("dateSearch") String dateSearch, @RequestParam("searchBy") String searchBy, Map model) {
//
//        List<Dvd> searchResults;
//        Date searchDate = null;
//
//        if ("releaseDate".equals(searchBy)) {
//
//            try {
//                searchDate = dateFormat.parse(dateSearch);
//            } catch (ParseException ex) {
//                Logger.getLogger(DvdController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            searchResults = dvdDao.searchByDate(searchDate);
//
//        } else {
//
//            searchResults = dvdDao.searchBy(searchBy, search);
//
//        }
//
//        model.put("searchResults", searchResults);
//
//        return "search";
//    }
}
