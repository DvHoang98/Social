package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import social.entity.Group;
import social.entity.User;
import social.entity.UserGroup;

import java.util.List;

public interface UserGroupRepository extends JpaRepository<UserGroup,Long> {

    public UserGroup findByUserAndGroup(User user, Group group);
    public List<UserGroup> findByUser(User user);

//    @Query(value = """
//            SELECT ug. FROM UserGroup ug
//            INNER JOIN DongSp dsp ON sp.dongSp.id = dsp.id
//                INNER JOIN Loai l ON sp.loai.id = l.id
//            WHERE (:typeP is null or :typeP = sp.loai.id)
//            AND (:classP is  null or :classP = sp.dongSp.id)
//            AND (sp.trangThai = 1)
//            """)
//    List<String> findGroupIdByUserName (@Param("typeP")Integer typeP , @Param("classP")Integer classP );
    @Query(value = "SELECT group_id FROM group_user WHERE user_id = :userID", nativeQuery = true)
    List<Long> findAllGroupOfUser(String userID);
}
