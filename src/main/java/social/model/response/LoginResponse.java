package social.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private User user;
    private String jwtToken;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                ", jwtToken='" + jwtToken + '\'' +
                '}';
    }
}
