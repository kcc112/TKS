package hex.model.developers;

import hex.model.technologies.TechnologyWeb;

import java.util.UUID;

public class FrontEndWeb extends DeveloperWeb {
    private String dummyAttribute;

    public FrontEndWeb(String developerName, String developerSurname, TechnologyWeb developerTechnology, UUID developerId) {
        super(developerName, developerSurname, developerTechnology, developerId);
    }

    public FrontEndWeb() {}

    public String getDummyAttribute() {
        return dummyAttribute;
    }

    public void setDummyAttribute(String dummyAttribute) {
        this.dummyAttribute = dummyAttribute;
    }
}
