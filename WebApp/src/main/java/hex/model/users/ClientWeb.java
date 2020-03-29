package hex.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hex.model.events.EventWeb;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@JsonIgnoreProperties("events")
public class ClientWeb extends UserWeb {

    private List<EventWeb> events = new ArrayList<>();

    public ClientWeb(String userEmail, String userName, String userSurname, String userType, UUID userId, String password) {
        super(userEmail, userName, userSurname, userType, userId, password);
    }

    public ClientWeb() { }

    public List<EventWeb> getEvents() {
        return events;
    }

    public void setEvents(List<EventWeb> events) {
        this.events = events;
    }

    public void addEvent(EventWeb event) {
        events.add(event);
    }

    public void removeEvent(EventWeb event) {
        Optional<EventWeb> eventToRemove =  events.stream()
                                .filter(var -> var.getId().equals(event.getId()))
                                .findFirst();
        eventToRemove.ifPresent(value -> events.remove(value));
    }
}
