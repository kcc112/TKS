package hex.controllers;


import hex.model.developers.Backend;
import hex.model.developers.Developer;
import hex.model.developers.DeveloperType;
import hex.model.developers.FrontEnd;
import hex.model.technologies.Technology;
import hex.service.interfaces.IDeveloperService;
import hex.service.interfaces.ITechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestMapping("/developers")
@Controller
public class DeveloperController {

    private final IDeveloperService developerService;
    private final ITechnologyService technologyService;
    private final RestTemplate restTemplate;

    @Autowired
    public DeveloperController(IDeveloperService developerService, ITechnologyService technologyService, @Qualifier("restTemplate") RestTemplate restTemplate) {
        this.developerService = developerService;
        this.technologyService = technologyService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String index(Model model) {
        List<Developer> developers = restTemplate.exchange("https://localhost:3443/api/v1/developers",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Developer>>() {
                }).getBody();
        // List<Developer> developers = developerService.getAllDevelopers();
        model.addAttribute("developers", developers);
        model.addAttribute("page", "developers/index");
        model.addAttribute("pageName", "developers");
        return "application/index";
    }

    @GetMapping("new/{type}")
    public String newForm(@PathVariable String type, Model model) {
        List<Technology> technologies;
        DeveloperType developerType;
        Developer developer;
        model.addAttribute("pageName", "developers");
        if (type.equals("back-end")) {
            technologies = technologyService.getAllTechnologiesBackEnd();
            developer = new Backend();
            developerType = new DeveloperType("back-end");
        } else if (type.equals("front-end")) {
            technologies = technologyService.getAllTechnologiesFrontEnd();
            developer = new FrontEnd();
            developerType = new DeveloperType("front-end");
        } else {
            model.addAttribute("pageName", "developers");
            return "redirect: /developers";
        }
        Technology technology = new Technology();
        model.addAttribute("technologies", technologies);
        model.addAttribute("developer", developer);
        model.addAttribute("technology", technology);
        model.addAttribute("developerType", developerType);
        model.addAttribute("page", "developers/new");
        return  "application/index";
    }

    @PostMapping("front-end")
    private String createFrontEnd(@Validated @ModelAttribute("developer") FrontEnd developer,
                          BindingResult bindingResult,
                          @ModelAttribute("developer-type") DeveloperType developerType,
                          @ModelAttribute("technology") Technology technology,
                          Model model) {
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("developer", developer);
            model.addAttribute("technologies", technology);
            model.addAttribute("page", "developers/new");
            return "application/index";
        }
       // developerService.addDeveloper(developer, technology);
        developer.setDeveloperTechnology(technology);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<FrontEnd> entity = new HttpEntity<FrontEnd>(developer, headers);
        restTemplate.exchange("https://localhost:3443/api/v1/developers/front-end", HttpMethod.POST, entity, String.class);
        return "redirect:/developers";
    }

    @PostMapping("back-end")
    private String createBackEnd(@Validated @ModelAttribute("developer") Backend developer,
                          BindingResult bindingResult,
                          @ModelAttribute("developerType") DeveloperType developerType,
                          @ModelAttribute("technology") Technology technology,
                          Model model) {
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("developer", developer);
            model.addAttribute("technologies", technology);
            model.addAttribute("page", "developers/new");
            return "application/index";
        }
        // developerService.addDeveloper(developer, technology);
        developer.setDeveloperTechnology(technology);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Backend> entity = new HttpEntity<Backend>(developer, headers);
        restTemplate.exchange("https://localhost:3443/api/v1/developers/back-end", HttpMethod.POST, entity, String.class);
        return "redirect:/developers";
    }


    @PostMapping("{id}/delete")
    private String destroy(@PathVariable UUID id, Model model) {
        restTemplate.delete("https://localhost:3443/api/v1/developers/" + id);
        model.addAttribute("pageName", "developers");
        // developerService.destroyDeveloper(id);
        return "redirect:/developers";
    }

    @PostMapping("{id}/back-end")
    private String updateBackEnd(@PathVariable UUID id,
                          @Validated @ModelAttribute("developer") Backend developer,
                          BindingResult bindingResult,
                          @ModelAttribute("technology") Technology technology,
                          @ModelAttribute DeveloperType developerType,
                          Model model) {
        developer.setDeveloperId(id);
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("technologies", technology);
            model.addAttribute("developer", developer);
            model.addAttribute("page", "/developers/edit");
            return "application/index";
        }
        developer.setDeveloperTechnology(technology);
        // developerService.updateDeveloper(developer);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Backend> entity = new HttpEntity<Backend>(developer, headers);
        restTemplate.exchange("https://localhost:3443/api/v1/developers/back-end", HttpMethod.PUT, entity, String.class);
        return "redirect:/developers";
    }

    @PostMapping("{id}/front-end")
    private String updateFrontEnd(@PathVariable UUID id,
                          @Validated @ModelAttribute("developer") FrontEnd developer,
                          BindingResult bindingResult,
                          @ModelAttribute("technology") Technology technology,
                          @ModelAttribute("developerType") DeveloperType developerType,
                          Model model) {
        developer.setDeveloperId(id);
        model.addAttribute("pageName", "developers");
        if (bindingResult.hasErrors()) {
            model.addAttribute("developerType", developerType);
            model.addAttribute("technologies", technology);
            model.addAttribute("developer", developer);
            model.addAttribute("page", "/developers/edit");
            return "application/index";
        }
        developer.setDeveloperTechnology(technology);
        // developerService.updateDeveloper(developer);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<FrontEnd> entity = new HttpEntity<FrontEnd>(developer, headers);
        restTemplate.exchange("https://localhost:3443/api/v1/developers/front-end", HttpMethod.PUT, entity, String.class);
        return "redirect:/developers";
    }

    @GetMapping("{id}/edit")
    public String edit(@PathVariable UUID id, Model model) {
        DeveloperType developerType;
        List<Technology> technologies;
        ResponseEntity<String> result = restTemplate.getForEntity("https://localhost:3443/api/v1/developers?id=" + id, String.class);
        // Optional<Developer> developer = developerService.selectDeveloperById(id);
        model.addAttribute("pageName", "developers");
        if (result != null) {
            if (Objects.requireNonNull(result.getBody()).contains("dummyAttribute")) {
                List<FrontEnd> developers = restTemplate.exchange("https://localhost:3443/api/v1/developers?id=" + id,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<FrontEnd>>() {
                        }).getBody();
                technologies = technologyService.getAllTechnologiesFrontEnd();
                developerType = new DeveloperType("front-end");
                if (developers != null) {
                    model.addAttribute("developer", developers.get(0));
                    model.addAttribute("technology", developers.get(0).getDeveloperTechnology());
                }
            } else {
                List<Backend> developers = restTemplate.exchange("https://localhost:3443/api/v1/developers?id=" + id,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Backend>>() {
                        }).getBody();
                technologies = technologyService.getAllTechnologiesBackEnd();
                developerType = new DeveloperType("back-end");
                if (developers != null) {
                    model.addAttribute("developer", developers.get(0));
                    model.addAttribute("technology", developers.get(0).getDeveloperTechnology());
                }
            }

            model.addAttribute("developerType", developerType);
            model.addAttribute("technologies", technologies);
            model.addAttribute("page", "/developers/edit");
            return  "/application/index";
        } else {
            return "redirect:/developers";
        }
    }

    @GetMapping("{id}/info")
    public String info(@PathVariable UUID id, Model model) {
//      Optional<Developer> developer = developerService.selectDeveloperById(id);
        List<FrontEnd> developers = restTemplate.exchange("https://localhost:3443/api/v1/developers?id=" + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<FrontEnd>>() {
                }).getBody();
        model.addAttribute("pageName", "developers");
        if (developers != null) {
            model.addAttribute("developer", developers.get(0));
            model.addAttribute("page", "developers/info");
            return "application/index";
        }
        return "redirect:/developers";
    }

    @GetMapping("search")
    public String search(@RequestParam(value="search", required=false) String name, Model model) {
        List<Developer> developers;
        model.addAttribute("pageName", "developers");
        if (name.isBlank() || name.isEmpty()) {
            // developers = developerService.getAllDevelopers();
            developers = restTemplate.exchange("https://localhost:3443/api/v1/developers",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Developer>>() {
                    }).getBody();
        } else {
            // developers = developerService.getDevelopersByName(name);
            developers = restTemplate.exchange("https://localhost:3443/api/v1/developers?name=" + name,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Developer>>() {
                    }).getBody();
        }
        model.addAttribute("developers", developers);
        model.addAttribute("page", "developers/index");
        return "application/index";
    }
}
