package api.motolife.service;

import api.motolife.db.User;
import api.motolife.db.UserLocation;
import api.motolife.repository.UserLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserLocationService")
public class UserLocationServiceImpl implements UserLocationService {

    @Autowired
    private UserLocationRepository userLocationRepository;

    @Override
    public List<UserLocation> findAll() {
        return userLocationRepository.findAllByIdIsNotNull();
    }

    @Override
    public void updateUserLocation(UserLocation userLocation) {
        userLocationRepository.save(userLocation);
    }

    @Override
    public UserLocation findFirstByUser(User user) {
        return userLocationRepository.findFirstByUser(user);
    }
}
