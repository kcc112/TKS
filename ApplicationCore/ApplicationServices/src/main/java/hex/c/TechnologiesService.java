package hex.c;

import hex.adapters.ports.IGetAllTechnologies;
import hex.adapters.ports.IShowAllUseCase;
import hex.model.technologies.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologiesService implements IShowAllUseCase {

    private final IGetAllTechnologies<Technology> technologies;

    @Autowired
    public TechnologiesService(IGetAllTechnologies<Technology> technologies) {
        this.technologies = technologies;
    }

    @Override
    public List<Technology> getAll() {
        return technologies.getAllTechnologies();
    }
}
