package hex.service;

import hex.model.events.Event;
import hex.model.users.Client;
import hex.repository.interfaces.IEventRepository;
import hex.service.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService implements IEventService {

    private final IEventRepository eventRepository;

    @Autowired
    public EventService(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    @Override
    public void addEvent(Event event) {
        if (!event.getUser().getIsActive() || event.getDeveloper().isHired()) {
            return;
        }
        LocalDate date = LocalDate.now();
        event.setStartDate(date);
        if (event.getUser() instanceof Client) {
            ((Client) event.getUser()).addEvent(event);
        }
        event.getDeveloper().setEvent(event);
        event.getDeveloper().setHired(true);
        eventRepository.addEvent(event);
    }

    @Override
    public void destroyEvent(UUID id) {
        Optional<Event> event = eventRepository.selectEventById(id);
        if (event.isPresent() && event.get().getEndDate() == null) {
            event.get().getDeveloper().setHired(false);
            event.get().getDeveloper().setEvent(null);
            if (event.get().getUser() instanceof Client) {
                ((Client) event.get().getUser()).removeEvent(event.get());
            }
            eventRepository.destroyEvent(id);
        }
    }

    @Override
    public void finishEvent(UUID id) {
        Optional<Event> event = eventRepository.selectEventById(id);
        if (event.isPresent()) {
            if (event.get().getDeveloper() != null) {
                event.get().getDeveloper().setHired(false);
                event.get().getDeveloper().setEvent(null);
            }
            event.get().setEndDate(LocalDate.now());
        }
    }

    @Override
    public List<Event> getAllEventsWithUser(String email) {
        return eventRepository.getAllEventsWithUser(email);
    }
}
