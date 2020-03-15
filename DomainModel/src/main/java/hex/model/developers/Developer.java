package hex.model.developers;



import hex.model.events.Event;
import hex.model.technologies.Technology;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

public class Developer {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 12, message = "Size must be between 3 and 12")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid name")
    private String developerName;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, max = 12, message = "Size must be between 3 and 12")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid surname")
    private String developerSurname;

    private Event event;
    private Technology developerTechnology;
    private UUID developerId;
    private boolean isHired;

    Developer(String developerName, String developerSurname, Technology developerTechnology, UUID developerId) {
        this.developerName = developerName;
        this.developerSurname = developerSurname;
        this.developerTechnology = developerTechnology;
        this.developerId = developerId;
        this.isHired = false;
    }

    public Developer() { }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getDeveloperSurname() {
        return developerSurname;
    }

    public void setDeveloperSurname(String developerSurname) {
        this.developerSurname = developerSurname;
    }

    public Technology getDeveloperTechnology() {
        return developerTechnology;
    }

    public void setDeveloperTechnology(Technology developerTechnology) {
        this.developerTechnology = developerTechnology;
    }

    public UUID getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(UUID developerId) {
        this.developerId = developerId;
    }

    public boolean isHired() {
        return isHired;
    }

    public void setHired(boolean hired) {
        isHired = hired;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return developerName + " " + developerSurname + " " + developerTechnology.getTechnologyName();
    }
}
