package HomeWork2;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Builder
public class User{
    String login;
    public  User(String login){
        this.login = login;
    }

}