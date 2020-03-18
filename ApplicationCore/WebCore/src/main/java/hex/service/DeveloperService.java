package hex.service;

import hex.model.developers.Developer;
import hex.model.developers.FrontEnd;
import hex.model.events.Event;
import hex.model.technologies.Technology;
import hex.repository.interfaces.IDeveloperRepository;
import hex.repository.interfaces.IEventRepository;
import hex.repository.interfaces.ITechnologyRepository;
import hex.service.interfaces.IDeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeveloperService implements IDeveloperService {

    private final IDeveloperRepository developerRepository;
    private final ITechnologyRepository technologyRepository;
    private final IEventRepository eventRepository;

    @Autowired
    public DeveloperService(IDeveloperRepository developerRepository, ITechnologyRepository technologyRepository, IEventRepository eventRepository) {
        this.developerRepository = developerRepository;
        this.technologyRepository = technologyRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Developer> getAllDevelopers() {
        return developerRepository.getAllDevelopers();
    }

    @Override
    public void addDeveloper(Developer developer, Technology technology) {
        List<Technology> technologies = technologyRepository.getAllTechnologies();

        for (Technology tech : technologies) {
            if (tech.getTechnologyName().equals(technology.getTechnologyName())) {
                developer.setDeveloperTechnology(technology);
                developerRepository.addDeveloper(developer);
            }
        }
    }

    @Override
    public void destroyDeveloper(UUID id) {
        Optional<Event> event = eventRepository.getEventsWithDevelopId(id);
        if (event.isPresent()) {
            event.get().setDeveloper(null);
            developerRepository.destroyDeveloper(id);
        } else {
            developerRepository.destroyDeveloper(id);
        }
    }

    @Override
    public void updateDeveloper(Developer developer) {
        Optional<Developer> currentDeveloper = developerRepository.selectDeveloperById(developer.getDeveloperId());
        if (currentDeveloper.isPresent()) {
            Technology newTechnology = developer.getDeveloperTechnology();
            currentDeveloper.get().setDeveloperTechnology(newTechnology);

            if(!developer.getDeveloperName().isBlank()) {
                String name = developer.getDeveloperName();
                currentDeveloper.get().setDeveloperName(name);
            }

            if(!developer.getDeveloperSurname().isBlank()) {
                String surname = developer.getDeveloperSurname();
                currentDeveloper.get().setDeveloperSurname(surname);
            }

            if (currentDeveloper.get() instanceof FrontEnd && developer instanceof FrontEnd ) {
                ((FrontEnd) currentDeveloper.get()).setDummyAttribute(((FrontEnd) developer).getDummyAttribute());
            }

            developerRepository.updateDeveloper(currentDeveloper.get());
        }
    }

    @Override
    public Optional<Developer> selectDeveloperById(UUID id) {
        return developerRepository.selectDeveloperById(id);
    }

    @Override
    public List<Developer> getAllUnemployedDevelopers() {
        return developerRepository.getAllUnemployedDevelopers();
    }

    @Override
    public List<Developer> getDevelopersByName(String name) {
        return developerRepository.getDevelopersByName(name);
    }
}
