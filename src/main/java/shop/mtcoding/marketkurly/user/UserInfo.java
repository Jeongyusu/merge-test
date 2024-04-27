package shop.mtcoding.marketkurly.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfo {
    
    private int id;
    private String username;
    private String role;
}
