package api.motolife.service;

import api.motolife.db.User;
import api.motolife.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllById(Integer id) {
        return null;
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByIdIsNotNull();
    }
}
