package api.motolife.Controller;

import api.motolife.db.User;
import api.motolife.service.UserLocationService;
import api.motolife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserTokenController {

    @Autowired
    private UserLocationService userLocationService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/setUserToken")
    public String setUserToken(@RequestParam(name = "token") String token, @RequestParam(name = "username") String username) {
        User user = userService.findByUsername(username);
        if (!Objects.equals(user, null)) {
            user.setToken(token);
            userService.updateUser(user);
            return "token_set_success";
        }
        return "token_set_failure";
    }
}
