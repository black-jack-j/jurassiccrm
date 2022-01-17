package com.jurassic.jurassiccrm.wiki;

import com.jurassic.jurassiccrm.wiki.entity.Wiki;
import com.jurassic.jurassiccrm.wiki.repository.WikiRepository;
import com.jurassic.jurassiccrm.wiki.service.WikiPagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/wiki")
    public String getHomePage(Model model) {
        model.addAttribute("allPages", service.findAll());
        return "wiki.html";
    }

    @GetMapping(value = "/page", params = {"pageName"})
    public String getWikiPage(Model model, final HttpServletRequest req) {
        model.addAttribute("page", service.findByName(req.getParameter("pageName")));
        return "/wiki/page";
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

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/api/wiki/{title}")
    @ResponseBody
    public ResponseEntity<Long> deleteWikiByTitle(@PathVariable String title){
        Wiki wiki = wikiRepository.findByTitle(title);
        if (wiki == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            List<Wiki> allWikis = wikiRepository.findAll();
            for (int i=0; i<allWikis.size(); i++){
                Wiki wiki1 = allWikis.get(i);
                List<Wiki> related = wiki1.getRelatedPages();
                related.remove(wiki);
                wiki1.setRelatedPages(related);
                wikiRepository.save(wiki1);
            }
            wikiRepository.deleteById(wiki.getId());
        }
        wikiRepository.flush();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/api/wiki/{id}")
    public ResponseEntity<Long> updateWikiPage(@PathVariable Long id, @RequestParam String title,
                                               @RequestParam String text, @RequestParam byte[] image,
                                               @RequestParam List<String> relatedPages
                                               ){
        Wiki wikiToUpdate = wikiRepository.findById(id).orElse(null);
        wikiToUpdate.setTitle(title);
        wikiToUpdate.setText(text);
        String img = new String(image, StandardCharsets.UTF_8);
        if (!("default".equals(img) || image.length == 0)){
            wikiToUpdate.setImage(image);
        }
        List<Wiki> relatedWiki = new ArrayList<>();
        for (String t: relatedPages){
            relatedWiki.add(wikiRepository.findByTitle(t));
        }
        wikiToUpdate.setRelatedPages(relatedWiki);
        wikiRepository.save(wikiToUpdate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ResponseBody
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(value = "/api/wiki")
    public ResponseEntity<Long> createWikiPage(@RequestParam String title, @RequestParam String text,
                                               @RequestParam byte[] image, @RequestParam List<String> relatedPages
    ){
        Wiki newWiki = new Wiki();
        newWiki.setTitle(title);
        newWiki.setText(text);
        if (image.length != 0){
            newWiki.setImage(image);
        }
        List<Wiki> relatedWiki = new ArrayList<>();
        for (String t: relatedPages){
            relatedWiki.add(wikiRepository.findByTitle(t));
        }
        newWiki.setRelatedPages(relatedWiki);
        wikiRepository.save(newWiki);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
