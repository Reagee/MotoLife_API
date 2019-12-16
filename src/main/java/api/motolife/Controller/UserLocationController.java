package api.motolife.Controller;

import api.motolife.db.User;
import api.motolife.db.UserLocation;
import api.motolife.model.UserLoc;
import api.motolife.service.UserLocationService;
import api.motolife.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class UserLocationController {

    @Autowired
    private UserLocationService userLocationService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getLocations")
    public String getUserLocations() throws JsonProcessingException {
        List<UserLocation> userLocations = userLocationService.findAll();
        List<User> usersList = userService.findAll();

        List<UserLoc> userLocList = new ArrayList<>();
        UserLoc loc;
        ObjectMapper mapper = new ObjectMapper();
        if (Objects.nonNull(userLocations) && Objects.nonNull(usersList)) {
            for (int i = 0; i < userLocations.size(); i++) {
                User currentUser = usersList.get(i);
                UserLocation currentUserLocation = userLocations.get(i);
                loc = new UserLoc();
                loc.setEmail(currentUser.getEmail());
                loc.setUsername(currentUser.getUsername());
                loc.setUser(currentUser);
                loc.setLast_location_update(currentUserLocation.getLast_location_update());
                loc.setLatitude(currentUserLocation.getLatitude());
                loc.setLongitude(currentUserLocation.getLongitude());
                userLocList.add(loc);
            }

            return mapper.writeValueAsString(userLocList);
        }
        return mapper.writeValueAsString(null);
    }

    @RequestMapping(value = "/updateUserLocation")
    public String updateUserLocation(@RequestParam("email") String email,
                                     @RequestParam("latitude") Double latitude,
                                     @RequestParam("longitude") Double longitude) {
        UserLocation userLocation;
        User user;
        user = userService.findByEmail(email);
        userLocation = userLocationService.findFirstByUser(user);
        if(Objects.nonNull(userLocation)){
            userLocation.setLast_location_update(new Timestamp(System.currentTimeMillis()));
            userLocation.setLatitude(latitude);
            userLocation.setLongitude(longitude);
            userLocationService.updateUserLocation(userLocation);
            return "success";
        }
        else if (Objects.nonNull(user)) {
            userLocation = UserLocation.builder()
                    .last_location_update(new Timestamp((System.currentTimeMillis())))
                    .latitude(latitude)
                    .longitude(longitude)
                    .user(user)
                    .build();

            userLocationService.updateUserLocation(userLocation);
            return "success";
        }
        return "cannot find user, location not updated";
    }

    @RequestMapping(value = "/")
    public String home() {
        return "MotoLife API v1.1";
    }
}
