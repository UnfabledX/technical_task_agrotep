package technikal.task.fishmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    //register, reset the password, email confirmation etc.
}
