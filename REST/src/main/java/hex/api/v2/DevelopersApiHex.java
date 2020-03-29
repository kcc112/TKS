package hex.api.v2;

import hex.adapters.ports.input.developers.IDevelopersUseCase;
import hex.model.developers.Backend;
import hex.model.developers.Developer;
import hex.model.developers.FrontEnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v2/developers")
@RestController
public class DevelopersApiHex {

    private final IDevelopersUseCase developersUseCase;

    @Autowired
    public DevelopersApiHex(IDevelopersUseCase developersUseCase) {
        this.developersUseCase = developersUseCase;
    }

    @PostMapping("front-end")
    public ResponseEntity<FrontEnd> addDeveloperFrontEnd(@Validated @RequestBody FrontEnd developer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        developersUseCase.addDeveloper(developer, developer.getDeveloperTechnology());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("back-end")
    public ResponseEntity<Backend> addDeveloperBackEnd(@Validated @RequestBody Backend developer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        developersUseCase.addDeveloper(developer, developer.getDeveloperTechnology());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public List<Developer> getDevelopers(@RequestParam(name = "id", required = false) UUID id, @RequestParam(name = "name", required = false) String name)  {
        if ((id == null || id.toString().isBlank()) && (name == null || name.isBlank())) {
            return developersUseCase.getAllDevelopers();
        } else if (id != null){
            List<Developer> developers = new ArrayList<>();
            developers.add(developersUseCase.getDeveloperById(id));
            return developers;
        } else {
            List<Developer> developers;
            developers = developersUseCase.getDeveloperByName(name);
            return developers;
        }
    }


    @DeleteMapping(path = "{id}")
    public void deleteDeveloper(@PathVariable UUID id) {
        developersUseCase.deleteDeveloper(id);
    }
}
