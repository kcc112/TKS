package hex.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hex.model.developers.DeveloperEnt;
import hex.model.users.UserEnt;

import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties("developer")
public class EventEnt {
    private UUID id;
    private UserEnt user;
    private DeveloperEnt developer;
    private LocalDate startDate;
    private LocalDate endDate;

    public EventEnt(UUID id, UserEnt user, DeveloperEnt developer, LocalDate startDate) {
        this.id = id;
        this.user = user;
        this.developer = developer;
        this.startDate = startDate;
        this.endDate = null;
    }

    public EventEnt() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserEnt getUser() {
        return user;
    }

    public DeveloperEnt getDeveloper() {
        return developer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDeveloper(DeveloperEnt developer) {
        this.developer = developer;
    }

    public void setUser(UserEnt user) {
        this.user = user;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
