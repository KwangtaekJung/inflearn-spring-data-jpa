package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query(
        "select new study.datajpa.repository.MemberDto(m.id, m.username, t.name) " +
        "from Member m join m.team t")
    List<MemberDto> findMemberDto();

    List<MemberProjections> findMemberProjectionsBy();

    Page<Member> findByAgeGreaterThan(int age, Pageable pageable);

    @Query("select m from Member m where m.team.name = :teamName")
    Page<Member> findByTeamName(@Param("teamName") String teamName, Pageable pageable);
}
