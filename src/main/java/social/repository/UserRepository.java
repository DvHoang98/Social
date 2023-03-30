package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //username
//    @Query(value = "SELECT * " +
//            "FROM users u WHERE u.userName " +
//            "AND u.email",nativeQuery = true)
    User findByUserNameOrEmail(String userName,String email);


    User findByUserName(String userName);

//    //User findALl
//    Optional<User> findAllUser(String username, String email);
}