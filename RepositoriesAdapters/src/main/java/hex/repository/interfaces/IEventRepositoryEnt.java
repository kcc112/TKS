package hex.repository.interfaces;

import hex.model.events.EventEnt;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IEventRepositoryEnt {
    void addEvent(UUID id, EventEnt event);
    void destroyEvent(UUID id);
    Optional<EventEnt> selectEventById(UUID id);
    List<EventEnt> getAllEvents();
    Optional<EventEnt> getEventsWithDevelopId(UUID id);
    List<EventEnt> getAllEventsWithUser(String email);

    default void addEvent(EventEnt event) {
        UUID id = UUID.randomUUID();
        addEvent(id, event);
    }
}
