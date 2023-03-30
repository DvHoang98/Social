package social.service;

import social.entity.Group;
import social.entity.User;
import social.entity.UserGroup;

import java.util.List;

public interface UserGroupService {
    public UserGroup saveUserGroup (UserGroup userGroup);

    public boolean checkUserInGroup(User user, Group group);
    public List<Long> getAllGroupOfUser(String idUser);
}
