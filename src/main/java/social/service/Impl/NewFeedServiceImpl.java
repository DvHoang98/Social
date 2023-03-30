package social.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import social.document.NewFeed;
import social.entity.User;
import social.repository.NewFeedRepository;
import social.service.NewFeedService;
import social.service.UserGroupService;

import java.util.List;
import java.util.Optional;

@Service
public class NewFeedServiceImpl implements NewFeedService {

    @Autowired
    NewFeedRepository newFeedRepository;

    @Autowired
    MongoTemplate template;
    @Autowired
    UserGroupService userGroupService;

    @Override
    public Page<NewFeed> getPageNewFeed(Pageable pageable, User user) {
        List<Long> lstGroupofUser = userGroupService.getAllGroupOfUser(String.valueOf(user.getId()));
        return newFeedRepository.findPrivateNewFeed( lstGroupofUser, user.getId(),pageable);
    }

    @Override
    public NewFeed createNewFeed(NewFeed newFeed) {
        return newFeedRepository.save(newFeed);
    }

    @Override
    public Optional<NewFeed> findById(String s) {
        return newFeedRepository.findById(s);
    }

    @Override
    public void deleteNewFeed(String id){
        newFeedRepository.deleteById(id);

    }


}
