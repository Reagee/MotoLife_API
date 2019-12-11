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
        if (!Objects.equals(userLocations, null) && !Objects.equals(usersList, null)) {
            for (int i = 0; i < userLocations.size(); i++) {
                loc = new UserLoc();
                loc.setUsername(usersList.get(i).getUsername());
                loc.setEmail(usersList.get(i).getEmail());
                loc.setLast_location_update(userLocations.get(i).getLast_location_update());
                loc.setLatitude(userLocations.get(i).getLatitude());
                loc.setLongitude(userLocations.get(i).getLongitude());
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
        if (Objects.equals(userLocation, null)) {
            userLocation = new UserLocation();
        }
        userLocation.setLast_location_update(new Timestamp(System.currentTimeMillis()));
        userLocation.setLatitude(latitude);
        userLocation.setLongitude(longitude);
        userLocation.setUser(user);
        userLocationService.updateUserLocation(userLocation);
        return "success";
    }

    @RequestMapping(value = "/")
    public String home() {
        return "MotoLife API v1.0";
    }
}
