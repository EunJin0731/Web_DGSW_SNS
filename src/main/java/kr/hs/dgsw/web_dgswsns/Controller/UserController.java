package kr.hs.dgsw.web_dgswsns.Controller;

import kr.hs.dgsw.web_dgswsns.Domain.User;
import kr.hs.dgsw.web_dgswsns.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public User user(@RequestBody User user){
        return this.userService.UserLogin(user);
    }
}
