package hex.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hex.model.developers.DeveloperWeb;
import hex.model.users.UserWeb;

import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties("developer")
public class EventWeb {
    private UUID id;
    private UserWeb user;
    private DeveloperWeb developer;
    private LocalDate startDate;
    private LocalDate endDate;

    public EventWeb(UUID id, UserWeb user, DeveloperWeb developer, LocalDate startDate) {
        this.id = id;
        this.user = user;
        this.developer = developer;
        this.startDate = startDate;
        this.endDate = null;
    }

    public EventWeb() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UserWeb getUser() {
        return user;
    }

    public DeveloperWeb getDeveloper() {
        return developer;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDeveloper(DeveloperWeb developer) {
        this.developer = developer;
    }

    public void setUser(UserWeb user) {
        this.user = user;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
