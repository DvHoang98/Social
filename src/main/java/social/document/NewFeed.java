package social.document;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("new_feed")
public class NewFeed {
    @Id
    private String id;
    private Long userId;
    private String content;
    private String image;
    private Long groupId;
    private Date createDate;
    private Date updateDate;
}
