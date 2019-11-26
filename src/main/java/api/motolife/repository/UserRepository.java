package api.motolife.repository;

import api.motolife.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findAllById(Integer id);
    User findUserById(Integer id);
    List<User> findAllByIdIsNotNull();
}
