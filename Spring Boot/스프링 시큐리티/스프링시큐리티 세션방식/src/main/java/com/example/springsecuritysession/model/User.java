package com.example.springsecuritysession.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
//    @NotEmpty(message = "닉네임은"+MUST_INPUT)
//    @Length(min =3, message = "닉네임은 3글자 이상이어야 합니다")
//    @Pattern(regexp = "^[a-zA-Z0-9]{3,}$", message = "닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)입력 가능합니다.")
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //저장될때는 string으로 저장되도록
    private UserRoleEnum role;

    @Column(unique = true)
    private Long kakaoId;

    public User(String nickname, String password, UserRoleEnum role) {
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        //일반 사용자는 카카오아이디가 없다
        this.kakaoId = null;
    }

    //카카오사용자를 위한 생성자
    public User(String nickname, String password, UserRoleEnum role, Long kakaoId) {
        this.nickname = nickname;
        this.password = password;
        this.role = role;
        this.kakaoId = kakaoId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
