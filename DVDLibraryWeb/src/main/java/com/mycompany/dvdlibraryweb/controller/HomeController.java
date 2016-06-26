package com.mycompany.dvdlibraryweb.controller;

import com.mycompany.dvdlibraryweb.dao.DvdDao;
import com.mycompany.dvdlibraryweb.dto.Dvd;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private DvdDao dvdDao;

    @Inject
    public HomeController(DvdDao dao) {
        this.dvdDao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Map model) {

        List<Dvd> dvds = dvdDao.getList();

        model.put("dvds", dvds);
        model.put("dvd", new Dvd());

        return "home";
    }

}
