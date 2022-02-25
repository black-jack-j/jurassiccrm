package com.jurassic.jurassiccrm.wiki;

import com.jurassic.jurassiccrm.accesscontroll.model.JurassicUserDetails;
import com.jurassic.jurassiccrm.logging.model.LogActionType;
import com.jurassic.jurassiccrm.logging.service.LogService;
import com.jurassic.jurassiccrm.wiki.entity.Wiki;
import com.jurassic.jurassiccrm.wiki.repository.WikiRepository;
import com.jurassic.jurassiccrm.wiki.service.WikiPagesService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class WikiController {

    @Autowired
    private WikiPagesService service;

    @Autowired
    private WikiRepository wikiRepository;

    @Autowired
    private LogService logService;

    @GetMapping("/wiki/home")
    public String getHomePage(Model model) {
        model.addAttribute("allPages", service.findAll());
        System.out.println(service.findAll().size());
        return "wiki/home";
    }

    @GetMapping("/wiki/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("allPages", service.findAll());
        return "wiki/admin";
    }

    @GetMapping(value = "/wiki/page", params = {"pageName"})
    public String getWikiPage(Model model, final HttpServletRequest req) {
        model.addAttribute("page", service.findByTitle(req.getParameter("pageName")));
        return "wiki/page";
    }

    @GetMapping(value = "/wiki/create")
    public String getCreationPage(Model model){
        model.addAttribute("wiki", new WikiDTOCreate());
        List<Wiki> wikis = wikiRepository.findAll();
        List<String> allTitles = new ArrayList<>();
        for (Wiki wiki : wikis) {
            allTitles.add(wiki.getTitle());
        }
        Collections.sort(allTitles);
        model.addAttribute("allTitles", allTitles);
        return "wiki/create";
    }

    @PostMapping(value = "/wiki/createWiki")
    public RedirectView createWikiPage(
            @ModelAttribute("wiki") WikiDTOCreate wiki,
            @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        Wiki wiki1 = new Wiki();
        wiki1.setTitle(wiki.getTitle());
        wiki1.setText(wiki.getText());
        try {
            wiki1.setImage(wiki.getImage().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Wiki> related = new ArrayList<>();
        for (String title : wiki.getRelatedPages()) {
            related.add(wikiRepository.findByTitle(title));
        }
        wiki1.setRelatedPages(related);
        Wiki saved = wikiRepository.save(wiki1);
        logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, "wiki page", saved.getTitle());
        return new RedirectView("/wiki/home");
    }

    @GetMapping(value = "/wiki/edit", params = {"pageName"})
    public String editWikiPage(Model model, final HttpServletRequest req) {
        model.addAttribute("page", service.findByTitle(req.getParameter("pageName")));
        Wiki wiki = wikiRepository.findByTitle(req.getParameter("pageName"));
        model.addAttribute("wiki", new WikiDTO(wiki));

        List<Wiki> wikis = wikiRepository.findAll();
        List<String> allTitles = new ArrayList<>();
        for (Wiki w : wikis) {
            allTitles.add(w.getTitle());
        }
        Collections.sort(allTitles);
        model.addAttribute("allTitles", allTitles);
        return "wiki/edit";
    }

    @PostMapping(value = "/wiki/editWiki1")
    public RedirectView editWikiPage(
            @ModelAttribute("wiki") WikiDTOCreate wiki,
            @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        Wiki wiki1 = wikiRepository.findById(wiki.getId()).orElse(null);
        if (wiki1 != null) {
            wiki1.setTitle(wiki.getTitle());
            wiki1.setText(wiki.getText());
            if (wiki.getRelatedPages() != null) {
                List<Wiki> related = new ArrayList<>();
                for (String title : wiki.getRelatedPages()) {
                    related.add(wikiRepository.findByTitle(title));
                    wiki1.setRelatedPages(related);
                }
            }
            try {
                wiki1.setImage(wiki.getImage().getBytes());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Wiki saved = wikiRepository.save(wiki1);
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.UPDATE, "wiki page", saved.getTitle());
            return new RedirectView("/wiki/edit?pageName=" + wiki1.getTitle());
        }
        return new RedirectView("/wiki/home");
    }

    @PostMapping(value = "/wiki/editWiki2")
    public RedirectView editWikiPage(
            @ModelAttribute("wiki") WikiDTO wiki,
            @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        Wiki wiki1 = wikiRepository.findById(wiki.getId()).orElse(null);
        if (wiki1 != null) {
            wiki1.setTitle(wiki.getTitle());
            wiki1.setText(wiki.getText());
            if (wiki.getImage() == null) {
            } else if (wiki.getImage().equals("delete")) {
                wiki1.setImage(new byte[0]);
            }
            List<Wiki> related = new ArrayList<>();
            if (wiki.getRelatedPages() != null) {
                for (String title : wiki.getRelatedPages()) {
                    related.add(wikiRepository.findByTitle(title));
                }
                wiki1.setRelatedPages(related);
            }
            Wiki saved = wikiRepository.save(wiki1);
            logService.logCrudAction(userDetails.getUserInfo(), LogActionType.UPDATE, "wiki page", saved.getTitle());
            return new RedirectView("/wiki/edit?pageName=" + wiki1.getTitle());
        }
        return new RedirectView("/wiki/home");
    }


    @ResponseBody
    @GetMapping(value = "/api/wiki/title")
    public ResponseEntity<List<String>> getAllTitles(){
        List<Wiki> wikis = wikiRepository.findAll();
        List<String> allTitles = new ArrayList<>();
        for (Wiki wiki : wikis) {
            allTitles.add(wiki.getTitle());
        }
        Collections.sort(allTitles);
        return ResponseEntity.ok(allTitles);
    }

    @ResponseBody
    @GetMapping(value = "/api/wiki")
    public ResponseEntity<WikiDTO> getWikiByTitle(@RequestParam String title){
        Wiki wiki = wikiRepository.findByTitle(title);
        List<String> relatedPages = new ArrayList<>();
        List<Wiki> related = wiki.getRelatedPages();
        for (Wiki wiki_ : related){
            relatedPages.add(wiki_.getTitle());
        }
        return ResponseEntity.ok(new WikiDTO(wiki.getId(), wiki.getTitle(), wiki.getText(), wiki.getImage(), relatedPages));
    }

    @DeleteMapping(value = "/api/wiki/{title}")
    @ResponseBody
    public ResponseEntity<Long> deleteWikiByTitle(@PathVariable String title,
                                                  @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails) {
        Wiki wiki = wikiRepository.findByTitle(title);
        if (wiki == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Wiki> allWikis = wikiRepository.findAll();
            for (int i = 0; i < allWikis.size(); i++) {
                Wiki wiki1 = allWikis.get(i);
                List<Wiki> related = wiki1.getRelatedPages();
                related.remove(wiki);
                wiki1.setRelatedPages(related);
                wikiRepository.save(wiki1);
            }
            wikiRepository.deleteById(wiki.getId());
        }
        wikiRepository.flush();
        logService.logCrudAction(userDetails.getUserInfo(), LogActionType.DELETE, "wiki page", wiki.getTitle());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PutMapping(value = "/api/wiki/{id}")
    public ResponseEntity<Long> updateWikiPage(@PathVariable Long id, @RequestParam String title,
                                               @RequestParam String text, @RequestParam byte[] image,
                                               @RequestParam List<String> relatedPages,
                                               @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails
    ){
        Wiki wikiToUpdate = wikiRepository.findById(id).orElse(null);
        wikiToUpdate.setTitle(title);
        wikiToUpdate.setText(text);
        String img = new String(image, StandardCharsets.UTF_8);
        if (!("default".equals(img) || image.length == 0)) {
            wikiToUpdate.setImage(image);
        }
        List<Wiki> relatedWiki = new ArrayList<>();
        for (String t : relatedPages) {
            relatedWiki.add(wikiRepository.findByTitle(t));
        }
        wikiToUpdate.setRelatedPages(relatedWiki);
        Wiki saved = wikiRepository.save(wikiToUpdate);
        logService.logCrudAction(userDetails.getUserInfo(), LogActionType.UPDATE, "wiki page", saved.getTitle());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/api/wiki")
    public ResponseEntity<Long> createWikiPage(@RequestParam String title, @RequestParam String text,
                                               @RequestParam byte[] image, @RequestParam List<String> relatedPages,
                                               @Parameter(hidden = true) @AuthenticationPrincipal JurassicUserDetails userDetails
    ){
        Wiki newWiki = new Wiki();
        newWiki.setTitle(title);
        newWiki.setText(text);
        if (image.length != 0) {
            newWiki.setImage(image);
        }
        List<Wiki> relatedWiki = new ArrayList<>();
        for (String t : relatedPages) {
            relatedWiki.add(wikiRepository.findByTitle(t));
        }
        newWiki.setRelatedPages(relatedWiki);
        Wiki saved = wikiRepository.save(newWiki);
        logService.logCrudAction(userDetails.getUserInfo(), LogActionType.CREATE, "wiki page", saved.getTitle());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
