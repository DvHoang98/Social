package social.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import social.entity.User;
import social.exception.BadRequestException;
import social.model.request.CreateUserRequest;
import social.repository.UserRepository;
import social.service.UserService;
import social.model.mapper.UserMapper;
import social.security.JwtTokenUtil;

import javax.servlet.http.HttpServletRequest;

@Component
@EnableCaching
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserMapper mapper;

    @Override
    public User createUser(CreateUserRequest createUserRequest){
        User user = userRepository.findByUserNameOrEmail(createUserRequest.getUserName(), createUserRequest.getEmail());
        if (user != null) {
            throw new BadRequestException("UserName or Email already exists in the system. Please try again!");
        }else{
            user = mapper.toUser(createUserRequest);
            userRepository.save(user);
        }
        return user;
    }

    @Cacheable(cacheNames="cache1",key = "'#key'")
    public User getUserByUserName(String userName) {
        User user= userRepository.findByUserName(userName);
        return user;
    }

    @Override
    public User getUserFromToken(HttpServletRequest request) {
        String token=request.getHeader("Authorization").substring(7);
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        return getUserByUserName(userName);
    }

}
