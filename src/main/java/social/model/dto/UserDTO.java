package social.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
    private Long id;
    private String userName;
    private String email;
    private String fullName;
    private String password;
    private String department;
    private String bio;
}