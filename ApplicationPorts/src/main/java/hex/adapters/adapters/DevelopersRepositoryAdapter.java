package hex.adapters.adapters;

import hex.adapters.ports.output.developers.*;
import hex.model.developers.Developer;
import hex.model.developers.DeveloperEnt;
import hex.model.technologies.Technology;
import hex.model.technologies.TechnologyEnt;
import hex.repository.interfaces.IDeveloperRepositoryEnt;
import hex.repository.interfaces.ITechnologyRepositoryEnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static hex.adapters.converters.fromDomain.FromDomainConverterDeveloper.convertDeveloperEnt;
import static hex.adapters.converters.toDomain.ToDomainConverterDeveloper.convertDeveloper;

@Component
public class DevelopersRepositoryAdapter implements IAddDeveloper, IDeleteDeveloper, IGetAllDevelopers, IUpdateDeveloper, IGetDeveloperById, IGetDeveloperByName {

    private final IDeveloperRepositoryEnt developerRepositoryEnt;
    private final ITechnologyRepositoryEnt technologyRepositoryEnt;

    @Autowired
    DevelopersRepositoryAdapter(IDeveloperRepositoryEnt developerRepositoryEnt, ITechnologyRepositoryEnt technologyRepositoryEnt) {
        this.developerRepositoryEnt = developerRepositoryEnt;
        this.technologyRepositoryEnt = technologyRepositoryEnt;
    }

    @Override
    public List<Developer> getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();
        for (DeveloperEnt developerEnt : developerRepositoryEnt.getAllDevelopers()) {
            Developer developer = convertDeveloper(developerEnt);
            developers.add(developer);
        }
        return developers;
    }

    @Override
    public void addDeveloper(Developer developer, Technology technology) {
        List<TechnologyEnt> technologiesEnt = technologyRepositoryEnt.getAllTechnologies();
        DeveloperEnt developerEnt = convertDeveloperEnt(developer);

        for (TechnologyEnt tech : technologiesEnt) {
            if (tech.getTechnologyName().equals(technology.getTechnologyName())) {
                developer.setDeveloperTechnology(technology);
                developerRepositoryEnt.addDeveloper(developerEnt);
            }
        }
    }

    @Override
    public void deleteDeveloper(UUID id) {
        developerRepositoryEnt.destroyDeveloper(id);
    }

    @Override
    public void updateDeveloper(Developer developer) {

    }

    @Override
    public Developer getDeveloperById(UUID id) {
        Optional<DeveloperEnt> developerEnt = developerRepositoryEnt.selectDeveloperById(id);
        return convertDeveloper(developerEnt.orElseGet(developerEnt::get));
    }

    @Override
    public List<Developer> getDeveloperByName(String name) {
        List<Developer> developers = new ArrayList<>();
        for (DeveloperEnt developerEnt : developerRepositoryEnt.getDevelopersByName(name)) {
            Developer developer = (convertDeveloper(developerEnt));
            developers.add(developer);
        }
        return developers;
    }
}
