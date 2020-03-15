package hex.model.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import hex.model.users.custom_validator.EmailUnique;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@JsonIgnoreProperties("password")
public class UserEnt {

    @NotBlank(message = "Email is mandatory")
    @Pattern(regexp =  "^[A-Za-z0-9]+@(.+)$", message = "Invalid email")
    @EmailUnique(message = "Such email already exists")
    private String userEmail;

    @NotBlank(message = "Name is eventsmandatory")
    @Size(min = 3, max = 12, message = "Size must be between 3 and 12")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid name")
    private String userName;

    @NotBlank(message = "Surname is mandatory")
    @Size(min = 3, max = 12, message = "Size must be between 3 and 12")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Invalid surname")
    private String userSurname;

    @NotBlank(message = "Password is mandatory")
    private String password;

    private String userType;

    private UUID userId;
    private boolean isActive;

    UserEnt(String userEmail, String userName, String userSurname, String userType, UUID userId, String password) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userType = userType;
        this.userId = userId;
        this.isActive = true;
        this.password = password;
    }

    public UserEnt() {}

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return userName + " " + userSurname + " " + userType;
    }
}
