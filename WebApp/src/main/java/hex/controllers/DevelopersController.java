package hex.controllers;


import hex.model.developers.BackendWeb;
import hex.model.developers.DeveloperTypeWeb;
import hex.model.developers.DeveloperWeb;
import hex.model.developers.FrontEndWeb;
import hex.model.technologies.TechnologyWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static hex.api.Api.*;

@RequestMapping("/hex/developers")
@Controller
public class DevelopersController {

    private final RestTemplate restTemplate;

    @Autowired
    public DevelopersController(@Qualifier("restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String index(Model model) {
        List<DeveloperWeb> developers = getAllDevelopers(restTemplate);
        model.addAttribute("developers", developers);
        model.addAttribute("page", "developers/index-hex");
        model.addAttribute("pageName", "developers");
        return "application/index";
    }

    @GetMapping("new/{type}")
    public String newForm(@PathVariable String type, Model model) {
        List<TechnologyWeb> technologies;
        DeveloperTypeWeb developerType;
        DeveloperWeb developer;
        model.addAttribute("pageName", "developers");
        if (type.equals("back-end")) {
            technologies = getAllBackendTechnologies(restTemplate);
            developer = new BackendWeb();
            developerType = new DeveloperTypeWeb("back-end");
        } else if (type.equals("front-end")) {
            technologies = getAllFrontendTechnologies(restTemplate);
            developer = new FrontEndWeb();
            developerType = new DeveloperTypeWeb("front-end");
        } else {
            model.addAttribute("pageName", "developers");
            return "redirect:/hex/developers";
        }
        TechnologyWeb technology = new TechnologyWeb();
        model.addAttribute("technologies", technologies);
        model.addAttribute("developer", developer);
        model.addAttribute("technology", technology);
        model.addAttribute("developerType", developerType);
        model.addAttribute("page", "developers/new-hex");
        return  "application/index-hex";
    }

    @PostMapping("front-end")
    private String createFrontEnd(@Validated @ModelAttribute("developer") FrontEndWeb developer,
                          BindingResult bindingResult,
                          @ModelAttribute("developer-type") DeveloperTypeWeb developerType,
                          @ModelAttribute("technology") TechnologyWeb technology,
                          Model model) {
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("developer", developer);
            model.addAttribute("technologies", technology);
            model.addAttribute("page", "developers/new-hex");
            return "application/index";
        }
        developer.setDeveloperTechnology(technology);
        postFrontEnd(restTemplate, developer);
        return "redirect:/hex/developers";
    }

    @PostMapping("back-end")
    private String createBackEnd(@Validated @ModelAttribute("developer") BackendWeb developer,
                          BindingResult bindingResult,
                          @ModelAttribute("developerType") DeveloperTypeWeb developerType,
                          @ModelAttribute("technology") TechnologyWeb technology,
                          Model model) {
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("developer", developer);
            model.addAttribute("technologies", technology);
            model.addAttribute("page", "developers/new-hex");
            return "application/index";
        }
        developer.setDeveloperTechnology(technology);
        postBackEnd(restTemplate,developer);
        return "redirect:/hex/developers";
    }


    @PostMapping("{id}/delete")
    private String destroy(@PathVariable UUID id, Model model) {
        destroyDeveloper(restTemplate, id);
        model.addAttribute("pageName", "developers");
        return "redirect:/hex/developers";
    }

    @PostMapping("{id}/back-end")
    private String updateBackEnd(@PathVariable UUID id,
                          @Validated @ModelAttribute("developer") BackendWeb developer,
                          BindingResult bindingResult,
                          @ModelAttribute("technology") TechnologyWeb technology,
                          @ModelAttribute DeveloperTypeWeb developerType,
                          Model model) {
        developer.setDeveloperId(id);
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("technologies", technology);
            model.addAttribute("developer", developer);
            model.addAttribute("page", "developers/edit-hex");
            return "application/index";
        }
        developer.setDeveloperTechnology(technology);
        putBackEnd(restTemplate, developer);
        return "redirect:/hex/developers";
    }

    @PostMapping("{id}/front-end")
    private String updateFrontEnd(@PathVariable UUID id,
                          @Validated @ModelAttribute("developer") FrontEndWeb developer,
                          BindingResult bindingResult,
                          @ModelAttribute("technology") TechnologyWeb technology,
                          @ModelAttribute("developerType") DeveloperTypeWeb developerType,
                          Model model) {
        developer.setDeveloperId(id);
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("technologies", technology);
            model.addAttribute("developer", developer);
            model.addAttribute("page", "developers/edit-hex");
            return "application/index";
        }
        developer.setDeveloperTechnology(technology);
        putFrontEnd(restTemplate, developer);
        return "redirect:/hex/developers";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable UUID id, Model model) {
        DeveloperTypeWeb developerType;
        List<TechnologyWeb> technologies;
        ResponseEntity<String> result = getDeveloperByIdAsResponseEntity(restTemplate, id);
        model.addAttribute("pageName", "developers");
        if (Objects.requireNonNull(result.getBody()).contains("dummyAttribute")) {
            List<FrontEndWeb> developers = getFrontEndDeveloperById(restTemplate, id);
            technologies = getAllFrontendTechnologies(restTemplate);
            developerType = new DeveloperTypeWeb("front-end");
            if (developers != null) {
                model.addAttribute("developer", developers.get(0));
                model.addAttribute("technology", developers.get(0).getDeveloperTechnology());
            }
        } else {
            List<BackendWeb> developers = getBackEndDeveloperById(restTemplate, id);
            technologies = getAllBackendTechnologies(restTemplate);
            developerType = new DeveloperTypeWeb("back-end");
            if (developers != null) {
                model.addAttribute("developer", developers.get(0));
                model.addAttribute("technology", developers.get(0).getDeveloperTechnology());
            }
        }

        model.addAttribute("developerType", developerType);
        model.addAttribute("technologies", technologies);
        model.addAttribute("page", "developers/edit-hex");
        return  "/application/index";
    }

    @GetMapping("{id}/info")
    public String info(@PathVariable UUID id, Model model) {
        List<FrontEndWeb> developers = getFrontEndDeveloperById(restTemplate, id);
        model.addAttribute("pageName", "developers");
        if (developers != null) {
            model.addAttribute("developer", developers.get(0));
            model.addAttribute("page", "developers/info-hex");
            return "application/index";
        }
        return "redirect:/hex/developers";
    }

    @GetMapping("search")
    public String search(@RequestParam(value="search", required=false) String name, Model model) {
        List<DeveloperWeb> developers;
        model.addAttribute("pageName", "developers");
        if (name.isBlank() || name.isEmpty()) {
            developers = getAllDevelopers(restTemplate);
        } else {
            developers = getDeveloperByName(restTemplate, name);
        }
        model.addAttribute("developers", developers);
        model.addAttribute("page", "developers/index-hex");
        return "application/index-hex";
    }
}
