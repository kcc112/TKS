package hex.service.interfaces;

import hex.model.events.Event;

import java.util.List;
import java.util.UUID;

public interface IEventService {
    List<Event> getAllEvents();
    void addEvent(Event event);
    void destroyEvent(UUID id);
    void finishEvent(UUID id);
    List<Event> getAllEventsWithUser(String email);
}
