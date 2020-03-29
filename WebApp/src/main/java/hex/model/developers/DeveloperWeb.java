package hex.model.developers;

import hex.model.events.EventWeb;
import hex.model.technologies.TechnologyWeb;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

public class DeveloperWeb {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 12, message = "Size must be between 3 and 12")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid name")
    private String developerName;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, max = 12, message = "Size must be between 3 and 12")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid surname")
    private String developerSurname;

    private EventWeb event;
    private TechnologyWeb developerTechnology;
    private UUID developerId;
    private boolean isHired;

    DeveloperWeb(String developerName, String developerSurname, TechnologyWeb developerTechnology, UUID developerId) {
        this.developerName = developerName;
        this.developerSurname = developerSurname;
        this.developerTechnology = developerTechnology;
        this.developerId = developerId;
        this.isHired = false;
    }

    public DeveloperWeb() { }

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

    public TechnologyWeb getDeveloperTechnology() {
        return developerTechnology;
    }

    public void setDeveloperTechnology(TechnologyWeb developerTechnology) {
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

    public EventWeb getEvent() {
        return event;
    }

    public void setEvent(EventWeb event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return developerName + " " + developerSurname + " " + developerTechnology.getTechnologyName();
    }
}
