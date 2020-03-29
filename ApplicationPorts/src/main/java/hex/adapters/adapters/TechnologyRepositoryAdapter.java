package hex.adapters.adapters;


import hex.adapters.ports.output.technologies.IGetAllTechnologies;
import hex.adapters.ports.output.technologies.IGetAllTechnologiesBackEnd;
import hex.adapters.ports.output.technologies.IGetAllTechnologiesFrontEnd;
import hex.adapters.ports.output.technologies.IGetTechnologyByName;
import hex.model.technologies.Technology;
import hex.model.technologies.TechnologyEnt;
import hex.repository.interfaces.ITechnologyRepositoryEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static hex.adapters.converters.toDomain.ToDomainConverterTechnology.convertTechnology;

@Component
public class TechnologyRepositoryAdapter implements IGetAllTechnologies,
        IGetAllTechnologiesBackEnd, IGetAllTechnologiesFrontEnd, IGetTechnologyByName {

    private final ITechnologyRepositoryEnt technologyRepositoryEnt;

    @Autowired
    TechnologyRepositoryAdapter(ITechnologyRepositoryEnt technologyRepositoryEnt) {
        this.technologyRepositoryEnt = technologyRepositoryEnt;
    }

    @Override
    public List<Technology> getAllTechnologies() {
        List<Technology> technologies = new ArrayList<>();
        for (TechnologyEnt technologyEnt : technologyRepositoryEnt.getAllTechnologies()) {
            Technology technology = convertTechnology(technologyEnt);
            technologies.add(technology);
        }
        return technologies;
    }

    @Override
    public List<Technology> getAllTechnologiesBackEnd() {
        List<Technology> backendTechnologies = new ArrayList<>();
        for (TechnologyEnt technologyEnt : technologyRepositoryEnt.getAllTechnologiesBackEnd()) {
            Technology technology = convertTechnology(technologyEnt);
            backendTechnologies.add(technology);
        }
        return backendTechnologies;
    }

    @Override
    public List<Technology> getAllTechnologiesFrontEnd() {
        List<Technology> frontendTechnologies = new ArrayList<>();
        for (TechnologyEnt technologyEnt : technologyRepositoryEnt.getAllTechnologiesFrontEnd()) {
            Technology technology = convertTechnology(technologyEnt);
            frontendTechnologies.add(technology);
        }
        return frontendTechnologies;
    }

    @Override
    public List<Technology> selectTechnologyByName(String technologyName) {
        List<Technology> technologies = new ArrayList<>();
        for (TechnologyEnt technologyEnt : technologyRepositoryEnt.selectTechnologyByName(technologyName)) {
            Technology technology = convertTechnology(technologyEnt);
            technologies.add(technology);
        }
        return technologies;
    }
}
