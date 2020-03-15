package hex.service;

import hex.model.technologies.Technology;
import hex.repository.interfaces.ITechnologyRepository;
import hex.service.interfaces.ITechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyService implements ITechnologyService {

    private final ITechnologyRepository technologyRepository;

    @Autowired
    public TechnologyService(ITechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<Technology> getAllTechnologies() {
        return technologyRepository.getAllTechnologies();
    }

    @Override
    public Optional<Technology> selectTechnologyByName(String technologyName) {
        return technologyRepository.selectTechnologyByName(technologyName);
    }

    @Override
    public List<Technology> getAllTechnologiesBackEnd() {
        return technologyRepository.getAllTechnologiesBackEnd();
    }

    @Override
    public List<Technology> getAllTechnologiesFrontEnd() {
        return technologyRepository.getAllTechnologiesFrontEnd();
    }
}
