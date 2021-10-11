package study.datajpa.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class MemberProjections {

//    private Long id;
    private String username;
    private String teamName;
}
