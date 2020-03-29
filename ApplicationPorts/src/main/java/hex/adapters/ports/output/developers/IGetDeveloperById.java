package hex.adapters.ports.output.developers;

import hex.model.developers.Developer;

import java.util.UUID;

public interface IGetDeveloperById {
    Developer getDeveloperById(UUID uuid);
}
