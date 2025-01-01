package com.activity.Activity.service;

import com.activity.Activity.DTO.UserLoginDTO;
import com.activity.Activity.DTO.UserRegisterDTO;
import com.activity.Activity.model.ERole;
import com.activity.Activity.model.Role;
import com.activity.Activity.model.User;
import com.activity.Activity.repository.RoleRepository;
import com.activity.Activity.repository.UserRepository;
import com.activity.Activity.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.oauth2.login.UserInfoEndpointDsl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> login(UserLoginDTO userLoginDTO){
        System.out.println(userLoginDTO.getEmail() + " " + userLoginDTO.getPassword() + " " + userLoginDTO.getUsername());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());

        Map<String, String> response = new HashMap<>();
        response.put("username", userDetails.getUsername());
        response.put("id", userDetails.getId());
        response.put("email", userDetails.getEmail());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, token.toString())
                .body(response);
    }

    public String register(UserRegisterDTO userDTO){
        Optional<User> user = userRepository.findByUsername(userDTO.getUsername());
        boolean existingEmail = userRepository.existsByEmail(userDTO.getEmail());
        if(user.isPresent() && existingEmail)
            return "User Already Exist";
        try{
            Set<Role> roles = new HashSet<>();
            userDTO.getRoles().stream().forEach(elm -> {
                switch (elm.toLowerCase()) {
                    case "admin":
                        Role admin = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Admin is 'admin' & Role Was Not Found"));
                        roles.add(admin);
                        break;
                    case "mod":
                        Role mod = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Moderator is 'mod' & Role Was Not Found"));
                        roles.add(mod);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("User is 'user' & Role Was Not Found"));
                        roles.add(userRole);
                        break;
                }
                });

                User newUser = new User(
                        userDTO.getUsername(),
                        userDTO.getEmail(),
                        passwordEncoder.encode(userDTO.getPassword()),
                        userDTO.getGender()
                );

                newUser.setRoles(roles);
                userRepository.save(newUser);
                return "User Was Created Successfully !";
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
