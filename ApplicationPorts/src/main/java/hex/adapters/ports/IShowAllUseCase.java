package hex.adapters.ports;

import hex.model.technologies.Technology;

import java.util.List;

public interface IShowAllUseCase {
    List<Technology> getAll();
}