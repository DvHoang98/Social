package social.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoResponse {
    private String userName;
    private String email;
    private String fullName;
    private String department;
    private String bio;
}
