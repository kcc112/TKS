package hex.api.v1;

import hex.adapters.ports.IShowAllUseCase;
import hex.model.technologies.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/technologies")
@RestController
public class TechnologiesApi {

    private final IShowAllUseCase IShowAllUseCase;

    @Autowired
    public TechnologiesApi(hex.adapters.ports.IShowAllUseCase developerService) {
        this.IShowAllUseCase = developerService;
    }

    @GetMapping
    public List<Technology> all() {
        return IShowAllUseCase.getAll();
    }
}
