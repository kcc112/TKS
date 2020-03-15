package hex.model.developers;

import hex.model.technologies.TechnologyEnt;

import java.util.UUID;

public class BackendEnt extends DeveloperEnt {
    public BackendEnt(String developerName, String developerSurname, TechnologyEnt developerTechnology, UUID developerId) {
        super(developerName, developerSurname, developerTechnology, developerId);
    }

    public BackendEnt() {}
}
