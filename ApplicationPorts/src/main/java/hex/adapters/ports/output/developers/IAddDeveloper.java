package hex.adapters.ports.output.developers;

import hex.model.developers.Developer;
import hex.model.technologies.Technology;

public interface IAddDeveloper {
    void addDeveloper(Developer developer, Technology technology);
}
