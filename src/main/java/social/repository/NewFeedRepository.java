package social.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import social.document.NewFeed;

import java.util.List;

@Repository
public interface NewFeedRepository extends MongoRepository<NewFeed,String> {

    @Query("{$or:[{groupId: { $in:  ?0 }} , {groupId: null , userId : ?1}] }")
    Page<NewFeed> findPrivateNewFeed ( List<Long> groupid, Long userId,Pageable pageable);
}
