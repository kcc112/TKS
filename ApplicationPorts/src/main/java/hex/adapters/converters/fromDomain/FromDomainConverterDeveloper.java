package hex.adapters.converters.fromDomain;

import hex.model.developers.Developer;
import hex.model.developers.DeveloperEnt;

public class FromDomainConverterDeveloper {
    public static DeveloperEnt convertDeveloperEnt(Developer developer) {
        DeveloperEnt developerEnt = new DeveloperEnt();
        developerEnt.setDeveloperId(developer.getDeveloperId());
        developerEnt.setDeveloperName(developer.getDeveloperName());
        developerEnt.setDeveloperSurname(developer.getDeveloperSurname());
        developerEnt.setDeveloperTechnology(FromDomainConverterTechnology.convertTechnologyEnt(developer.getDeveloperTechnology()));
        developerEnt.setEvent(null);
        developerEnt.setHired(developerEnt.isHired());
        return developerEnt;
    }
}
