package hex.repository;

import hex.model.events.Event;
import hex.repository.interfaces.IEventRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EventRepository implements IEventRepository {

    private List<Event> events = new ArrayList<>();

    @Override
    public void addEvent(UUID id, Event event) {
        event.setId(id);
        synchronized (this) {
            events.add(event);
        }
    }

    @Override
    public void destroyEvent(UUID id) {
        Optional<Event> eventToDelete = selectEventById(id);
        synchronized (this) {
            eventToDelete.ifPresent(event -> events.remove(event));
        }
    }

    @Override
    public Optional<Event> selectEventById(UUID id) {
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<Event> getEventsWithDevelopId(UUID id) {
        return events.stream()
                .filter(event ->  Objects.nonNull(event.getDeveloper()))
                .filter(event -> event.getDeveloper().getDeveloperId().equals(id))
                .findFirst();
    }

    @Override
    public List<Event> getAllEvents() {
        return events;
    }

    @Override
    public List<Event> getAllEventsWithUser(String email) {
        List<Event> userEvents = new ArrayList<>();
        for (Event event : events) {
            if (event.getUser().getUserEmail().equals(email)) {
                userEvents.add(event);
            }
        }
        return userEvents;
    }
}
