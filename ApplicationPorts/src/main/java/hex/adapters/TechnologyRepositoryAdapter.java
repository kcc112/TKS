package hex.adapters;


import hex.adapters.ports.IGetAllTechnologies;
import hex.adapters.ports.IGetAllTechnologiesBackEnd;
import hex.adapters.ports.IGetAllTechnologiesFrontEnd;
import hex.adapters.ports.ISelectTechnologyByName;
import hex.model.technologies.Technology;
import hex.model.technologies.TechnologyEnt;
import hex.repository.interfaces.ITechnologyRepositoryEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static hex.adapters.converter.ToDomainConverterTechnology.convertTechnology;

@Component
public class TechnologyRepositoryAdapter implements IGetAllTechnologies,
        IGetAllTechnologiesBackEnd, IGetAllTechnologiesFrontEnd, ISelectTechnologyByName {

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
    public Optional<Technology> selectTechnologyByName(String technologyName) {
        TechnologyEnt technologyEnt = technologyRepositoryEnt.selectTechnologyByName(technologyName).get();
        return Optional.of(convertTechnology(technologyEnt));
    }
}
