package pl.sda.puzzle.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.puzzle.UserRepository;

import java.security.Principal;

@RestController
@RequestMapping("/login")
public class LoginController {

    private UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public void login(){}


    @GetMapping
    public String getEmail(Principal principal){
       return principal.getName();
    }
}
