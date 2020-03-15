package hex.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hex.model.events.EventEnt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@JsonIgnoreProperties("events")
public class ClientEnt extends UserEnt {

    private List<EventEnt> events = new ArrayList<>();

    public ClientEnt(String userEmail, String userName, String userSurname, String userType, UUID userId, String password) {
        super(userEmail, userName, userSurname, userType, userId, password);
    }

    public ClientEnt() { }

    public List<EventEnt> getEvents() {
        return events;
    }

    public void setEvents(List<EventEnt> events) {
        this.events = events;
    }

    public void addEvent(EventEnt event) {
        events.add(event);
    }

    public void removeEvent(EventEnt event) {
        Optional<EventEnt> eventToRemove =  events.stream()
                                .filter(var -> var.getId().equals(event.getId()))
                                .findFirst();
        eventToRemove.ifPresent(value -> events.remove(value));
    }
}
