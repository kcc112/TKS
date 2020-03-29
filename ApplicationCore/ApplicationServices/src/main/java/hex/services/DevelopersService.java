package hex.services;

import hex.adapters.ports.input.developers.IDevelopersUseCase;
import hex.adapters.ports.output.developers.*;
import hex.model.developers.Developer;
import hex.model.technologies.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DevelopersService implements IDevelopersUseCase {

    private final IAddDeveloper addDeveloper;
    private final IDeleteDeveloper deleteDeveloper;
    private final IGetAllDevelopers getAllDevelopers;
    private final IGetDeveloperById getDeveloperById;
    private final IGetDeveloperByName getDeveloperByName;

    @Autowired
    public DevelopersService(IAddDeveloper addDeveloper, IDeleteDeveloper deleteDeveloper, IGetAllDevelopers getAllDevelopers, IGetDeveloperById getDeveloperById, IGetDeveloperByName getDeveloperByName) {
        this.addDeveloper = addDeveloper;
        this.deleteDeveloper = deleteDeveloper;
        this.getAllDevelopers = getAllDevelopers;
        this.getDeveloperById = getDeveloperById;
        this.getDeveloperByName = getDeveloperByName;
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return getAllDevelopers.getAllDevelopers();
    }

    @Override
    public void addDeveloper(Developer developer, Technology technology) {
        addDeveloper.addDeveloper(developer, technology);
    }

    @Override
    public void deleteDeveloper(UUID id) {
        deleteDeveloper.deleteDeveloper(id);
    }

    @Override
    public List<Developer> getDeveloperByName(String name) {
        return getDeveloperByName.getDeveloperByName(name);
    }

    @Override
    public Developer getDeveloperById(UUID id) {
        return getDeveloperById.getDeveloperById(id);
    }
}

