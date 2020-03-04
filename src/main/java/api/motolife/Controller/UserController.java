package api.motolife.Controller;

import api.motolife.db.User;
import api.motolife.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @SneakyThrows
    @RequestMapping(value = "/check")
    public String checkLocation() {
        Logger.getLogger(UserController.class.getName()).log(Level.INFO, "API Status has been sent!");
        return "ok";
    }

    @RequestMapping(value = "/addUser")
    public String addUser(@RequestParam(name = "username") String username, @RequestParam(name = "email") String email, @RequestParam(name = "token") String token) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setToken(token);

        userService.updateUser(user);
        return "user_added";
    }

    @RequestMapping(value = "/getUsername")
    public String getUsername(@RequestParam(name = "email") String email) {
        User user = userService.findByEmail(email);
        return Objects.nonNull(user) ? user.getUsername() : null;
    }
}
