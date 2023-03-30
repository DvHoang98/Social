package social.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import social.document.NewFeed;
import social.entity.User;

import java.util.Optional;


public interface NewFeedService {

    public Page<NewFeed> getPageNewFeed (Pageable pageable, User user);
    public NewFeed createNewFeed(NewFeed newFeed);
    Optional<NewFeed> findById(String s);
    void deleteNewFeed(String id);
}
