package hex.model.developers;

import hex.model.technologies.TechnologyEnt;

import java.util.UUID;

public class FrontEndEnt extends DeveloperEnt {
    private String dummyAttribute;

    public FrontEndEnt(String developerName, String developerSurname, TechnologyEnt developerTechnology, UUID developerId) {
        super(developerName, developerSurname, developerTechnology, developerId);
    }

    public FrontEndEnt() {}

    public String getDummyAttribute() {
        return dummyAttribute;
    }

    public void setDummyAttribute(String dummyAttribute) {
        this.dummyAttribute = dummyAttribute;
    }
}
