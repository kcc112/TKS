package hex.adapters.converters.toDomain;

import hex.model.developers.Developer;
import hex.model.developers.DeveloperEnt;

public class ToDomainConverterDeveloper {
    public static Developer convertDeveloper(DeveloperEnt developerEnt) {
        Developer developer = new Developer();
        developer.setDeveloperId(developerEnt.getDeveloperId());
        developer.setDeveloperName(developerEnt.getDeveloperName());
        developer.setDeveloperSurname(developerEnt.getDeveloperSurname());
        developer.setDeveloperTechnology(ToDomainConverterTechnology.convertTechnology(developerEnt.getDeveloperTechnology()));
        developer.setEvent(null);
        developer.setHired(developerEnt.isHired());
        return developer;
    }
}
