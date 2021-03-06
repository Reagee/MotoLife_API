package api.motolife.service;

import api.motolife.db.User;
import api.motolife.db.UserLocation;

import java.util.List;

public interface UserLocationService {
    List<UserLocation> findAll();
    void updateUserLocation(UserLocation userLocation);
    UserLocation findFirstByUser(User user);

}
