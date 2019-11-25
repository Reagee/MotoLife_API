package api.motolife.service;

import api.motolife.db.User;

public interface UserService {
    User findByUsername(String username);
    void updateUser(User user);
    User findByEmail(String email);
}
