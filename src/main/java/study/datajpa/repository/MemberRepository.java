package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    //name이라는 필드는 없기 때문에 NamedQuery를 설정하지 않으면 Error가 발생한다.
    @Query(name = "Member.findByName")
    List<Member> findByName(@Param("username") String username);

    List<Member> findByUsername(String username);
}
