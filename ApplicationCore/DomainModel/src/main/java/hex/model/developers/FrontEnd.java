package hex.model.developers;



import hex.model.technologies.Technology;

import java.util.UUID;

public class FrontEnd extends Developer {
    private String dummyAttribute;

    public FrontEnd(String developerName, String developerSurname, Technology developerTechnology, UUID developerId) {
        super(developerName, developerSurname, developerTechnology, developerId);
    }

    public FrontEnd() {}

    public String getDummyAttribute() {
        return dummyAttribute;
    }

    public void setDummyAttribute(String dummyAttribute) {
        this.dummyAttribute = dummyAttribute;
    }
}
