package social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import social.service.UserService;
import social.entity.User;
import social.model.request.CreateUserRequest;
import social.model.request.JwtRequest;
import social.model.response.JwtResponse;
import social.model.response.LoginResponse;
import social.model.response.UserInfoResponse;
import social.security.JwtTokenUtil;
import social.service.Impl.JwtUserDetailsService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController

public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    public static final String HASH_KEY = "Users";
    @Autowired
    private RedisTemplate template;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody CreateUserRequest user) {
        User newUser = userService.createUser(user);
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(user.getUserName());
        final String token = jwtTokenUtil.generateToken(userDetails);
        template.opsForHash().put(HASH_KEY, newUser.getUserName(), token);
        return ResponseEntity.ok(new LoginResponse(newUser, token));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpServletResponse repo) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    @GetMapping("/getInfo/{name}")
    public ResponseEntity<?> getInfo( @PathVariable("name") String username)throws Exception {
        try{
            User currentUser = userService.getUserByUserName(username);
            return ResponseEntity.ok(new UserInfoResponse(currentUser.getUserName(), currentUser.getEmail(), currentUser.getFullName(),
                    currentUser.getDepartment(), currentUser.getBio()));
        }catch(Exception e){
            throw new Exception("USER_DISABLED", e);
        }

    }


}
