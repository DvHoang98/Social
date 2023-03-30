package social.service;

import social.entity.Group;
import social.model.request.CreateGroupRequest;


public interface GroupService {
    public Group saveGroup(CreateGroupRequest groupRequset);
}
