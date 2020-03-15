package hex.adapters.converter;


import hex.model.technologies.Technology;
import hex.model.technologies.TechnologyEnt;

public class ToDomainConverterTechnology {

    public static Technology convertTechnology(TechnologyEnt technologyEnt) {
        Technology technology = new Technology();
        technology.setTechnologyName(technologyEnt.getTechnologyName());
        return technology;
    }
}
