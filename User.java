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
    private int id ;
    private  String email ;
    private  String password ;

    public User(Integer id ,String email , String password) {
        this.id = id ;
        this.email = email ;
        this.password = password;
    }

}
