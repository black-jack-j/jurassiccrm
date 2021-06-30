package com.jurassic.jurassiccrm.wiki.service;

import com.jurassic.jurassiccrm.wiki.model.WikiPage;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WikiPagesService {
    private final List<WikiPage> pages = Arrays.asList(
            new WikiPage("Tyrannosaurus", "Tyrannosaurus is a genus of tyrannosaurid theropod dinosaur. The species Tyrannosaurus rex (rex meaning \"king\" in Latin), often called T. rex or colloquially T-Rex, is one of the best represented of these large theropods. Tyrannosaurus lived throughout what is now western North America, on what was then an island continent known as Laramidia. Tyrannosaurus had a much wider range than other tyrannosaurids. Fossils are found in a variety of rock formations dating to the Maastrichtian age of the Upper Cretaceous period, 68 to 66 million years ago. It was the last known member of the tyrannosaurids and among the last non-avian dinosaurs to exist before the Cretaceous–Paleogene extinction event.", "tyrannosaurus.jpg", Arrays.asList("Triceratops")),
            new WikiPage("Triceratops", "Triceratops has been documented by numerous remains collected since the genus was first described in 1889 by American paleontologist Othniel Charles Marsh. Specimens representing life stages from hatchling to adult have been found. As the archetypal ceratopsid, Triceratops is one of the most popular dinosaurs, and has been featured in film, postal stamps, and many other types of media.", "triceratops.webp", Arrays.asList("Tyrannosaurus"))
    );

    public List<WikiPage> findAll(){
        return pages;
    }

    public WikiPage findByName(String name){
        return pages
                .stream()
                .filter(page -> page.getTitle().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
