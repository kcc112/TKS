package hex.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hex.model.developers.Developer;
import hex.model.users.User;

import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties("developer")
public class Event {
    private UUID id;
    private User user;
    private Developer developer;
    private LocalDate startDate;
    private LocalDate endDate;

    public Event(UUID id, User user, Developer developer, LocalDate startDate) {
        this.id = id;
        this.user = user;
        this.developer = developer;
        this.startDate = startDate;
        this.endDate = null;
    }

    public Event() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
