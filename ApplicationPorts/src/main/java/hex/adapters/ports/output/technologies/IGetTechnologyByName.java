package hex.adapters.ports.output.technologies;

import hex.model.technologies.Technology;

import java.util.List;

public interface IGetTechnologyByName {
    List<Technology> selectTechnologyByName(String technologyName);
}