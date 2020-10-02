package com.ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.Service.SearchService;

@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/search")
    public void getSearchByTerm(@RequestParam String search_term){
        searchService.search(search_term);
    }

    @GetMapping("/search/stream")
    public void getSearchByTermStream(@RequestParam String search_term){
        searchService.searchStream(search_term);
    }
}
