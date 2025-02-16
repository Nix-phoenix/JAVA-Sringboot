// src/main/java/com/example/sqlinjection/controller/SecureUserController.java
package Sqlinjection.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Sqlinjection.Entity.User;
import Sqlinjection.Repository.SecureUserRepository;

rt org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SecureUserController {

    @Autowired
    private SecureUserRepository userRepository;

    @GetMapping("/secure/users")
    public List<User> getUsersByUsername(@RequestParam String username) {
    
        return userRepository.findByUsername(username);
    }
}