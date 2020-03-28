package hex.c;

import hex.adapters.ports.*;
import hex.model.technologies.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologiesService implements IShowAllUseCase {

    private final IGetAllTechnologies getAllTechnologies;

    private final IGetAllTechnologiesBackEnd getAllTechnologiesBackEnd;

    private final IGetAllTechnologiesFrontEnd getAllTechnologiesFrontEnd;

    private final ISelectTechnologyByName selectTechnologyByName;

    @Autowired
    public TechnologiesService(IGetAllTechnologies getAllTechnologies, IGetAllTechnologiesBackEnd getAllTechnologiesBackEnd,
                               IGetAllTechnologiesFrontEnd getAllTechnologiesFrontEnd, ISelectTechnologyByName selectTechnologyByName) {

        this.getAllTechnologies = getAllTechnologies;
        this.getAllTechnologiesBackEnd = getAllTechnologiesBackEnd;
        this.getAllTechnologiesFrontEnd = getAllTechnologiesFrontEnd;
        this.selectTechnologyByName = selectTechnologyByName;
    }

    @Override
    public List<Technology> getAll() {
        return getAllTechnologies.getAllTechnologies();
    }

    @Override
    public List<Technology> getAllBackend() {
        return getAllTechnologiesBackEnd.getAllTechnologiesBackEnd();
    }

    @Override
    public List<Technology> getAllFrontend() {
        return getAllTechnologiesFrontEnd.getAllTechnologiesFrontEnd();
    }

    @Override
    public Optional<Technology> selectByName(String technologyName) {
        return selectTechnologyByName.selectTechnologyByName(technologyName);
    }
}
