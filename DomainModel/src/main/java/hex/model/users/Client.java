package hex.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hex.model.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@JsonIgnoreProperties("events")
public class Client extends User {

    private List<Event> events = new ArrayList<>();

    public Client(String userEmail, String userName, String userSurname, String userType, UUID userId, String password) {
        super(userEmail, userName, userSurname, userType, userId, password);
    }

    public Client() { }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void removeEvent(Event event) {
        Optional<Event> eventToRemove =  events.stream()
                                .filter(var -> var.getId().equals(event.getId()))
                                .findFirst();
        eventToRemove.ifPresent(value -> events.remove(value));
    }
}
