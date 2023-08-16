//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.javaguides.todo.service.impl;

import com.javaguides.todo.entity.Role;
import com.javaguides.todo.entity.User;
import com.javaguides.todo.repository.RoleRepository;
import com.javaguides.todo.repository.UserRepository;
import com.javaguides.todo.service.UserService;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public User createUser(User user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        String roleName = ((Role)user.getRoles().iterator().next()).getName();
        Optional<Role> role = this.roleRepository.findByName(roleName);
        if (role.isPresent()) {
            user.setRoles(Set.of((Role)role.get()));
        } else {
            user.setRoles(Set.of((Role)user.getRoles().stream().findFirst().orElseThrow()));
        }

        return (User)this.userRepository.save(user);
    }
}
