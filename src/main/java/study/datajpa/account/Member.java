package study.datajpa.account;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString  //연관 관계 없는 필드만
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

}
