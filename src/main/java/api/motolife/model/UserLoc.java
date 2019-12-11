package api.motolife.model;

import api.motolife.db.UserLocation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoc extends UserLocation {
    private String username;
    private String email;

    @Override
    public String toString() {
        return "Username: "+getUsername() + ", Email: "+getEmail()+"\n";
    }
}
