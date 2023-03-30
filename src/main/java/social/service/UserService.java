package social.service;

import org.springframework.stereotype.Service;
import social.entity.User;
import social.exception.BadRequestException;
import social.model.request.CreateUserRequest;

import javax.servlet.http.HttpServletRequest;

@Service
public interface UserService {

    User createUser(CreateUserRequest createUserRequest) throws BadRequestException;
    public User getUserByUserName(String userName);
    public User getUserFromToken (HttpServletRequest request);
}
