package hex.model.developers;


import hex.model.technologies.TechnologyWeb;

import java.util.UUID;

public class BackendWeb extends DeveloperWeb {
    public BackendWeb(String developerName, String developerSurname, TechnologyWeb developerTechnology, UUID developerId) {
        super(developerName, developerSurname, developerTechnology, developerId);
    }

    public BackendWeb() {}
}
