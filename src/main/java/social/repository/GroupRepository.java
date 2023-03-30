package social.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import social.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
