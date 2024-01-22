package com.maquinaria.ecommerce.backend.infrastructure.Controller;


import com.maquinaria.ecommerce.backend.application.service.UserService;
import com.maquinaria.ecommerce.backend.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/users")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody  User user){
        return  ResponseEntity.ok(userService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }

    public User findByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }

}
