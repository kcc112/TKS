package hex.services;

import hex.adapters.ports.input.technologies.ITechnologiesUseCase;
import hex.adapters.ports.output.technologies.IGetAllTechnologies;
import hex.adapters.ports.output.technologies.IGetAllTechnologiesBackEnd;
import hex.adapters.ports.output.technologies.IGetAllTechnologiesFrontEnd;
import hex.adapters.ports.output.technologies.IGetTechnologyByName;
import hex.model.technologies.Technology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologiesService implements ITechnologiesUseCase {

    private final IGetAllTechnologies getAllTechnologies;

    private final IGetAllTechnologiesBackEnd getAllTechnologiesBackEnd;

    private final IGetAllTechnologiesFrontEnd getAllTechnologiesFrontEnd;

    private final IGetTechnologyByName selectTechnologyByName;

    @Autowired
    public TechnologiesService(IGetAllTechnologies getAllTechnologies, IGetAllTechnologiesBackEnd getAllTechnologiesBackEnd,
                               IGetAllTechnologiesFrontEnd getAllTechnologiesFrontEnd, IGetTechnologyByName selectTechnologyByName) {

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
    public List<Technology> selectByName(String technologyName) {
        return selectTechnologyByName.selectTechnologyByName(technologyName);
    }
}
