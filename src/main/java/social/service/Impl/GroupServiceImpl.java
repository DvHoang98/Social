package social.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import social.model.request.CreateGroupRequest;
import social.entity.Group;
import social.repository.GroupRepository;
import social.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;
    @Override
    public Group saveGroup(CreateGroupRequest groupCreate) {
        Group newGroup = new Group( groupCreate.getName(), groupCreate.getDescription());
        return groupRepository.save(newGroup);
    }

}
