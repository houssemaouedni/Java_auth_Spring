//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.javaguides.todo.security;

import com.javaguides.todo.entity.User;
import com.javaguides.todo.repository.UserRepository;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = (User)this.userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> {
            return new UsernameNotFoundException("User Not Found with username or email : " + usernameOrEmail);
        });
        Set<GrantedAuthority> authorities = (Set)user.getRoles().stream().map((role) -> {
            return new SimpleGrantedAuthority(role.getName());
        }).collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(usernameOrEmail, user.getPassword(), authorities);
    }

    public CustomUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
