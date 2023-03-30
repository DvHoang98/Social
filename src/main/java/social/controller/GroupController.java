package social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import social.model.request.CreateGroupRequest;
import social.service.UserService;
import social.entity.Group;
import social.entity.User;
import social.entity.UserGroup;
import social.model.response.MessageResponse;
import social.security.JwtTokenUtil;
import social.service.GroupService;
import social.service.UserGroupService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> saveGroup (@RequestBody CreateGroupRequest groupRequest,
                                        HttpServletRequest request)throws Exception{
        User currentUser= userService.getUserFromToken(request);
        Group newGroup = new Group();
            newGroup= groupService.saveGroup(groupRequest);
            userGroupService.saveUserGroup(new UserGroup(
                    currentUser,newGroup)
            );
            return ResponseEntity.ok(newGroup);
    }

    @GetMapping("/add/user={user}/group={group}")
    public ResponseEntity<?> addUser(
            @PathVariable User user,
            @PathVariable Group group
    ){
        if(!userGroupService.checkUserInGroup(user,group)){
            userGroupService.saveUserGroup(new UserGroup(user,group));
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK,"Thêm thành công"));
        }else{
            return ResponseEntity.ok(new MessageResponse(HttpStatus.BAD_REQUEST,"Người dùng đã có trong group"));
        }
    }
}
