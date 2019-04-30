package pl.sda.puzzle.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.method.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import pl.sda.puzzle.UserRepository;
import pl.sda.puzzle.tables.User;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.xml.ws.http.HTTPException;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Collection<User> get() {
        return userRepository.findAll();
    }

    @PostMapping
    @ExceptionHandler(IllegalArgumentException.class)
    public User post(@Valid @RequestBody User user){
        boolean exist = userRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(user.getEmail()))
                .findFirst()
                .isPresent();

        if(!exist) {
            user.setRole(User.Role.USER);
            return userRepository.save(user);
        } else {
            throw new ValidationException();
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Collection<PropertyValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new PropertyValidationError(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
    }


    @GetMapping("/me/ver1")
    public User me(Principal principal) {
        String username = principal.getName();
        return userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
