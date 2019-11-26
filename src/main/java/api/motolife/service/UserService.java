package api.motolife.service;

import api.motolife.db.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    void updateUser(User user);
    User findByEmail(String email);
    List<User> findAllById(Integer id);
    User findUserById(Integer id);
    List<User> findAll();
}
