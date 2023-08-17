//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.javaguides.todo.controller;

import com.javaguides.todo.entity.User;
import com.javaguides.todo.service.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping({"/user"})
public class UserController {
    private UserService userService;

    @PostMapping({"/create"})
    @PermitAll
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println("non");
        User userSave = this.userService.createUser(user);
        System.out.println("ok");
        return ResponseEntity.ok(userSave);
    }

}
