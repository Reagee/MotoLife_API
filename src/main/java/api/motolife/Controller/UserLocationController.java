package api.motolife.Controller;

import api.motolife.db.UserLocation;
import api.motolife.service.UserLocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@RestController
public class UserLocationController {

    @Autowired
    private UserLocationService userLocationService;

    @RequestMapping(value = "/getLocations")
    public String getUserLocations() throws JsonProcessingException {
        List<UserLocation> userLocations = userLocationService.findAll();
        ObjectMapper mapper = new ObjectMapper();
        if (!Objects.equals(userLocations, null))
            return mapper.writeValueAsString(userLocations);
        return mapper.writeValueAsString(null);
    }

    @RequestMapping(value = "/updateUserLocation")
    public String updateUserLocation(@RequestParam("username") String username,
                                     @RequestParam("latitude") Double latitude,
                                     @RequestParam("longitude") Double longitude) {
        UserLocation userLocation;
        userLocation = userLocationService.findFirstByUsername(username);
        if (!Objects.equals(userLocation, null)) {
            userLocation.setLast_location_update(new Timestamp(System.currentTimeMillis()));
            userLocation.setLatitude(latitude);
            userLocation.setLongitude(longitude);
        } else {
            userLocation = new UserLocation();
            userLocation.setUsername(username);
            userLocation.setLast_location_update(new Timestamp(System.currentTimeMillis()));
            userLocation.setLatitude(latitude);
            userLocation.setLongitude(longitude);
        }
        userLocationService.updateUserLocation(userLocation);
        return "success";
    }

    @RequestMapping(value = "/")
    public String home() {
        return "MotoLife API v1.0";
    }
}
