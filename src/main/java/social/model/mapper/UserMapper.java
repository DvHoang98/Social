package social.model.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import social.entity.User;
import social.model.dto.UserDTO;
import social.model.request.CreateUserRequest;

@Component
public class UserMapper {

    @Autowired
    private  ModelMapper mapper ;
    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return userDTO;
    }
    public User toUser(CreateUserRequest createUserRequest) {
        User user = mapper.map(createUserRequest, User.class);
        String hash = BCrypt.hashpw(createUserRequest.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        return user;
    }
}
