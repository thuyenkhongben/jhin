package com.code.jhin.controller;

import com.code.jhin.model.username.Role;
import com.code.jhin.model.username.RoleName;
import com.code.jhin.model.username.User;
import com.code.jhin.playLoad.request.LoginRequest;
import com.code.jhin.playLoad.request.SignUpRequest;
import com.code.jhin.playLoad.response.ApiResponse;
import com.code.jhin.playLoad.response.JwtAuthenticationResponse;
import com.code.jhin.repository.userRepository.RoleRepository;
import com.code.jhin.repository.userRepository.UserRepository;
import com.code.jhin.security.JwtTokenProvider;
import com.code.jhin.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
//        System.out.println("ok");
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = tokenProvider.generateToken(authentication);
//        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = tokenProvider.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            UserPrincipal user = (UserPrincipal) userDetails;
            Long id = user.getId();

            return new  ResponseEntity<ApiResponse>(
                    new ApiResponse(true,"succsess"+loginRequest.getPassword(),new JwtAuthenticationResponse( id, jwt, userDetails.getUsername(), userDetails.getAuthorities())),
                    HttpStatus.OK);
        } catch (DisabledException e) {
            e.printStackTrace();
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        }


    @PostMapping("/signup/thanh")
    public ResponseEntity<ApiResponse> registerAdmin(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse(false, "Username is already taken!", null ),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Email Address already in use!", null ),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(),passwordEncoder.encode(signUpRequest.getPassword()));

        Set<Role>roles = new HashSet<>();

        Role adminRole = roleRepository.findByName(RoleName.ADMIN)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not set."));
        roles.add(adminRole);

        user.setRoles(roles);


        User result = userRepository.save(user);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/api/users/{username}")
//                .buildAndExpand(result.getUsername()).toUri();

        return new  ResponseEntity<ApiResponse>(new ApiResponse(true,"Admin Register successfully" , null),
                HttpStatus.OK);
}

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse>registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        //check xem username đã tồn tại chưa
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false , "Username is already taken!" , null),
                    HttpStatus.BAD_REQUEST);
        }
        //check xem email đó đã tồn tại hay chưa
        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false , " Email is alreday taken!" , null),
                    HttpStatus.BAD_REQUEST);
        }
        //create user
        User user = new User(signUpRequest.getName() , signUpRequest.getUsername() ,signUpRequest.getEmail() ,
                passwordEncoder.encode(signUpRequest.getPassword()));

        //create role user
        Set<Role>roles = new HashSet<>();

        Role userRole = roleRepository.findByName(RoleName.USER).orElseThrow(() ->
                new RuntimeException("Fail! -> Cause:User Role not set."));

        // gan role cho user
        roles.add(userRole);

        user.setRoles(roles);
        //luu user va role vua tao
        User result = userRepository.save(user);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true , "User Register Successfully" , null),
                HttpStatus.OK);

    }
}


