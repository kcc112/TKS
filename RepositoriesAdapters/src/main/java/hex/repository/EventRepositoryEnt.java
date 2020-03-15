package hex.repository;

import hex.model.events.EventEnt;
import hex.repository.interfaces.IEventRepositoryEnt;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class EventRepositoryEnt implements IEventRepositoryEnt {

    private List<EventEnt> events = new ArrayList<>();

    @Override
    public void addEvent(UUID id, EventEnt event) {
        event.setId(id);
        synchronized (this) {
            events.add(event);
        }
    }

    @Override
    public void destroyEvent(UUID id) {
        Optional<EventEnt> eventToDelete = selectEventById(id);
        synchronized (this) {
            eventToDelete.ifPresent(event -> events.remove(event));
        }
    }

    @Override
    public Optional<EventEnt> selectEventById(UUID id) {
        return events.stream()
                .filter(event -> event.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<EventEnt> getEventsWithDevelopId(UUID id) {
        return events.stream()
                .filter(event ->  Objects.nonNull(event.getDeveloper()))
                .filter(event -> event.getDeveloper().getDeveloperId().equals(id))
                .findFirst();
    }

    @Override
    public List<EventEnt> getAllEvents() {
        return events;
    }

    @Override
    public List<EventEnt> getAllEventsWithUser(String email) {
        List<EventEnt> userEvents = new ArrayList<>();
        for (EventEnt event : events) {
            if (event.getUser().getUserEmail().equals(email)) {
                userEvents.add(event);
            }
        }
        return userEvents;
    }
}
