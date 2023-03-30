package social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import social.service.UserService;
import social.document.NewFeed;
import social.entity.User;
import social.model.mapper.NewFeedMapper;
import social.model.request.NewFeedRequest;
import social.model.response.MessageResponse;
import social.service.NewFeedService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/newfeeds")
public class NewFeedController {

    @Autowired
    NewFeedService newFeedService;
    @Autowired
    UserService userService;
    @Autowired
    NewFeedMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<?> createNewStory( @Valid @RequestBody NewFeedRequest newFeed , HttpServletRequest request){
        User user = userService.getUserFromToken(request);
        newFeed.setUserId(user.getId());
        newFeed.setCreateDate(new java.util.Date());
        return ResponseEntity.ok(newFeedService.createNewFeed(mapper.toNewFeed(newFeed)));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNewFeed(@PathVariable("id") String idNewFeed,@Valid @RequestBody NewFeedRequest newFeed) {
        Optional<NewFeed> entity = newFeedService.findById(idNewFeed);
        if (entity.isPresent()) {
            NewFeed newFeed1 = entity.get();
            newFeed1.setContent(newFeed.getContent() != null ? newFeed.getContent() : newFeed1.getContent());
            newFeed1.setImage(newFeed.getImage() != null ? newFeed.getImage() : newFeed1.getImage());
            newFeed1.setUpdateDate(new Date(System.currentTimeMillis()));
            newFeedService.createNewFeed(newFeed1);
            return new ResponseEntity<>(newFeed1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not found NewsFeed " + idNewFeed, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNewFeed(@PathVariable("id") String id){
        try{
           newFeedService.deleteNewFeed(id);
            return ResponseEntity.ok(new MessageResponse(HttpStatus.OK,"Successfully deleted NewsFeed with id "+ id));
        } catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/Get-Story")
    public ResponseEntity<?> get5NewStory (@RequestParam int page,
                                           @RequestParam int size,
                                           HttpServletRequest request){
        User user = userService.getUserFromToken(request);
        Pageable paging = PageRequest.of(page-1, size, Sort.by("createDate").descending());
        return ResponseEntity.ok(newFeedService.getPageNewFeed(paging,user).getContent());
    }
}
