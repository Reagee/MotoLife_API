package api.motolife.model;

import api.motolife.db.User;
import api.motolife.db.UserLocation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserLoc extends UserLocation {
    private String username;
    private String email;

    @Override
    public String toString() {
        return "Username: " + getUsername() + ", Email: " + getEmail() + "\n";
    }
}
