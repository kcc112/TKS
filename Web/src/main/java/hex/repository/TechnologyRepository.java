package hex.repository;

import hex.model.technologies.NodeJs;
import hex.model.technologies.React;
import hex.model.technologies.RubyOnRails;
import hex.model.technologies.Technology;
import hex.repository.interfaces.ITechnologyRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TechnologyRepository implements ITechnologyRepository {

    private List<Technology> technologies;

    public TechnologyRepository() {
        this.technologies = new ArrayList<>();
        technologies.add(new RubyOnRails());
        technologies.add(new React());
        technologies.add(new NodeJs());
    }

    @Override
    public Optional<Technology> selectTechnologyByName(String technologyName) {
        return technologies.stream()
                .filter(technology -> technology.getTechnologyName().equals(technologyName))
                .findFirst();
    }

    @Override
    public List<Technology> getAllTechnologies() {
        return technologies;
    }

    @Override
    public List<Technology>  getAllTechnologiesFrontEnd() {
        List<Technology> frontendTechnologies = new ArrayList<>();
        for (Technology tech : technologies) {
            if (tech.getClass().equals(React.class)) {
                frontendTechnologies.add(tech);
            }
        }
        return frontendTechnologies;
    }

    @Override
    public List<Technology> getAllTechnologiesBackEnd() {
        List<Technology> backendTechnologies = new ArrayList<>();
        for (Technology tech : technologies) {
            if (tech.getClass().equals(RubyOnRails.class) || tech.getClass().equals(NodeJs.class)) {
                backendTechnologies.add(tech);
            }
        }
        return backendTechnologies;
    }
}
