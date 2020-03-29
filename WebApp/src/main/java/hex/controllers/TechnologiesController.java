package hex.controllers;


import hex.model.technologies.TechnologyWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static hex.api.Api.getAllTechnologies;

@RequestMapping("/hex/technologies")
@Controller
public class TechnologiesController {


    private final RestTemplate restTemplate;

    @Autowired
    public TechnologiesController(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String index(Model model) {
        List<TechnologyWeb> technologies = getAllTechnologies(restTemplate);
        model.addAttribute("technologies", technologies);
        model.addAttribute("page", "technologies/index-hex");
        model.addAttribute("pageName", "technologies");
        return "application/index";
    }
}
