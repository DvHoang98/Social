package social.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.model.request.NewFeedRequest;
import social.document.NewFeed;
import social.entity.User;
import social.model.dto.UserDTO;

@Service
public class NewFeedMapper {
    @Autowired
    private ModelMapper mapper;

    private UserDTO toUserDTO(User user) {
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return userDTO;
    }
    public NewFeed toNewFeed(NewFeedRequest newFeedRequest) {
        NewFeed newFeed = mapper.map(newFeedRequest, NewFeed.class);
        return newFeed;
    }
}
