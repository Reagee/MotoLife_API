package api.motolife.model;

import api.motolife.db.User;
import api.motolife.db.UserLocation;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.sql.Timestamp;

@Getter
public class UserLoc extends UserLocation {
    private String username;
    private String email;

    @Builder
    public UserLoc(@Nullable Integer id, User user, Timestamp last_location_update, double latitute, double longitude, String username, String email) {
        super(id, user, last_location_update, latitute, longitude);
        this.username = username;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername() + ", Email: " + getEmail() + "\n";
    }
}
