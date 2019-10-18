package api.motolife.repository;

import api.motolife.db.UserLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLocationRepository extends JpaRepository<UserLocation, Integer> {
    List<UserLocation> findAllByIdIsNotNull();
    UserLocation findFirstByUsername(String username);
}
