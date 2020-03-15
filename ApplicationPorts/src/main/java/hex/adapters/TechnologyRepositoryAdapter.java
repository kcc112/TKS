package hex.adapters;


import hex.adapters.ports.IGetAllTechnologies;
import hex.model.technologies.Technology;
import hex.model.technologies.TechnologyEnt;
import hex.repository.interfaces.ITechnologyRepositoryEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static hex.adapters.converter.ToDomainConverterTechnology.convertTechnology;

@Component
public class TechnologyRepositoryAdapter implements IGetAllTechnologies<Technology> {

    private final ITechnologyRepositoryEnt technologyRepositoryEnt;

    @Autowired
    TechnologyRepositoryAdapter(ITechnologyRepositoryEnt technologyRepositoryEnt) {
        this.technologyRepositoryEnt = technologyRepositoryEnt;
    }

    @Override
    public List<Technology> getAllTechnologies() {
        List<Technology> technologies = new ArrayList<>();
        for(TechnologyEnt technologyEnt : technologyRepositoryEnt.getAllTechnologies()) {
            Technology technology = convertTechnology(technologyEnt);
            technologies.add(technology);
        }
        return technologies;
    }
}
