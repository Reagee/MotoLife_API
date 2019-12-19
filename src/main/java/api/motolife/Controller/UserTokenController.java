package api.motolife.Controller;

import api.motolife.db.User;
import api.motolife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserTokenController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/setUserToken")
    public String setUserToken(@RequestParam(name = "token") String token, @RequestParam(name = "email") String email) {
        User user = userService.findByEmail(email);
        if (Objects.nonNull(user)) {
            user.setToken(token);
            userService.updateUser(user);
            return "token_set_success";
        }
        return "token_set_failure";
    }
}
