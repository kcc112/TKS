package hex.model.users;

import java.util.UUID;

public class ResourceAdministratorWeb extends UserWeb {
    public ResourceAdministratorWeb(String userEmail, String userName, String userSurname, String userType, UUID userId, String password) {
        super(userEmail, userName, userSurname, userType, userId, password);
    }
}
