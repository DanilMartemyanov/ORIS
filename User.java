package oop;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@EqualsAndHashCode
@Builder

public class User {
    private  String email ;
    private  String password ;

    public User(String email , String password) {
        this.email = email ;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public  String getPassword()
    {
        return password;
    }
}
