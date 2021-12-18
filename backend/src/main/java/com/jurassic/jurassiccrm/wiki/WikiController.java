package com.jurassic.jurassiccrm.wiki;

import com.jurassic.jurassiccrm.wiki.service.WikiPagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/wiki")
public class WikiController {

    @Autowired
    private WikiPagesService service;

    @GetMapping({"/", "/home"})
    public String getHomePage(Model model) {
        model.addAttribute("allPages", service.findAll());
        return "/wiki/home";
    }

    @GetMapping(value = "/page", params = {"pageName"})
    public String getWikiPage(Model model, final HttpServletRequest req) {
        model.addAttribute("page", service.findByName(req.getParameter("pageName")));
        return "/wiki/page";
    }
}
