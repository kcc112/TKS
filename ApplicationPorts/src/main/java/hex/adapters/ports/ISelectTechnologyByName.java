package hex.adapters.ports;

import java.util.Optional;

public interface ISelectTechnologyByName<T> {
    Optional<T> selectTechnologyByName(String technologyName);
}