package hex.adapters.converters.fromDomain;


import hex.model.technologies.Technology;
import hex.model.technologies.TechnologyEnt;

public class FromDomainConverterTechnology {

    public static TechnologyEnt convertTechnologyEnt(Technology technology) {
        TechnologyEnt technologyEnt = new TechnologyEnt();
        technologyEnt.setTechnologyName(technology.getTechnologyName());
        return technologyEnt;
    }
}
