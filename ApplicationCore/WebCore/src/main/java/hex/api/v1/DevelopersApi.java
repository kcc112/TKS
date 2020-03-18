package hex.api.v1;

import hex.model.developers.Backend;
import hex.model.developers.Developer;
import hex.model.developers.FrontEnd;
import hex.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/developers")
@RestController
public class DevelopersApi {

    private final DeveloperService developerService;

    @Autowired
    public DevelopersApi(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @PostMapping("/front-end")
    public ResponseEntity addDeveloperFrontEnd(@Validated @RequestBody FrontEnd developer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        developerService.addDeveloper(developer, developer.getDeveloperTechnology());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/back-end")
    public ResponseEntity addDeveloperBackEnd(@Validated @RequestBody Backend developer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        developerService.addDeveloper(developer, developer.getDeveloperTechnology());
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Developer> getDevelopers(@RequestParam(name = "id", required = false) UUID id, @RequestParam(name = "name", required = false) String name)  {
        if ((id == null || id.toString().isBlank()) && (name == null || name.isBlank())) {
            return developerService.getAllDevelopers();
        } else if (id != null){
            List<Developer> developers = new ArrayList<>();
            developers.add(developerService.selectDeveloperById(id).orElse(null));
            return developers;
        } else {
            List<Developer> developers;
            developers = developerService.getDevelopersByName(name);
            return developers;
        }
    }

    @PutMapping("/front-end")
    public ResponseEntity updateDeveloperFrontEnd(@Validated @RequestBody FrontEnd developer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        developerService.updateDeveloper(developer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/back-end")
    public ResponseEntity updateDeveloperBackEnd(@Validated @RequestBody Backend developer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        developerService.updateDeveloper(developer);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = "{id}")
    public void deleteDeveloper(@PathVariable UUID id) {
        developerService.destroyDeveloper(id);
    }
}
