package social.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.entity.Group;
import social.entity.User;
import social.entity.UserGroup;
import social.repository.UserGroupRepository;
import social.service.UserGroupService;

import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    UserGroupRepository userGroupRepository;

    @Override
    public UserGroup saveUserGroup(UserGroup userGroup) {
        return userGroupRepository.save(userGroup);
    }
    @Override
    public boolean checkUserInGroup(User user, Group group) {
        return userGroupRepository.findByUserAndGroup(user,group) ==null ? false : true;
    }

    @Override
    public List<Long> getAllGroupOfUser(String idUser) {
        List<Long>lst = userGroupRepository.findAllGroupOfUser(idUser);
        return lst;
    }
}
