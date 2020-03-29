package hex.adapters.ports.output.developers;

import hex.model.developers.Developer;

import java.util.List;

public interface IGetDeveloperByName {
    List<Developer> getDeveloperByName(String name);
}
