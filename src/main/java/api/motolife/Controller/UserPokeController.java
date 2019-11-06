package api.motolife.Controller;

import api.motolife.model.UserPoke;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class UserPokeController {
    @RequestMapping(value = "/pokeUser")
    public String pokeUser(@RequestParam(name = "username") String username, @RequestParam(name = "userToPoke") String userToPoke) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Logger.getGlobal().log(Level.INFO,"Poke from {0} has been sent to {1}",new String[]{username,userToPoke});
        return mapper.writeValueAsString(new UserPoke(username, userToPoke));
    }
}
