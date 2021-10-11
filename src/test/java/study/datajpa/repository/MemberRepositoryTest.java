package study.datajpa.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Transactional
@Rollback(value = true)
//H2DB를 인메모리로 사용할 경우에는 테스트 종료 후 어차피 사라질것이므로 의미 없다. => 아니네. 테스트간 독립성을 유지하기 위해서는 RollBack 시켜야 하는군.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    TeamRepository teamRepository;

    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll");
    }

    @Test
    @Order(10)
    public void createMemberTest() {
        Team team = new Team("testTeam");
        teamRepository.save(team);

        Member member = new Member("testUser", 20, team);
        Member savedMember = memberRepository.save(member);

        Optional<Member> findAccount = memberRepository.findById(savedMember.getId());

        assertThat(findAccount.isEmpty()).isFalse();
        assertThat(findAccount.get().getId()).isEqualTo(savedMember.getId());
        assertThat(findAccount.get().getUsername()).isEqualTo("testUser");
        assertThat(findAccount.get()).isEqualTo(savedMember);
    }

    @Test
    @Order(20)
    public void findByUsernameAndAgeGreaterThan_JPA() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberJpaRepository.save(m1);
        memberJpaRepository.save(m2);

        List<Member> result = memberJpaRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @Order(21)
    public void findByUsernameAndAgeGreaterThan_SpringDataJPA() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    @Order(30)
    public void findByName_NamedQuery() {
        Member member = new Member("AAA");
        memberRepository.save(member);

        List<Member> result = memberRepository.findByName("AAA");

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
    }

    @Test
    @Order(31)
    public void findUser() {
        Member member = new Member("AAA", 10);
        memberRepository.save(member);

        List<Member> result = memberRepository.findUser("AAA", 10);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(10);
    }

    @Test
    @Order(32)
    public void findUsernameList() {
        createMembers();

        List<String> usernameList = memberRepository.findUsernameList();
        System.out.println("usernameList = " + usernameList.toString());
    }

    @Test
    @Order(33)
    public void findMemberDto() {
        createMembers();

        List<MemberDto> memberDtos = memberRepository.findMemberDto();

        for (MemberDto memberDto : memberDtos) {
            System.out.println("memberDto = " + memberDto);
        }
    }

    @Test
    @Order(34)
    public void findMemberProjections() {
        createMembers();

        List<MemberProjections> memberProjections = memberRepository.findMemberProjectionsByUsername("USER_1");

        for (MemberProjections memberProjection : memberProjections) {
            System.out.println("memberProjection = " + memberProjection);
        }
    }

    private void createMembers() {
        Team teamA = new Team("TeamA");
        teamRepository.save(teamA);
        Team teamB = new Team("TeamB");
        teamRepository.save(teamB);

        Member member;
        for (int i = 0; i < 5; i++) {
            member = new Member("USER_" + i, (10 + (int) (Math.random() * 100)), teamA);
            memberRepository.save(member);
        }
        for (int i = 5; i < 10; i++) {
            member = new Member("USER_" + i, (10 + (int) (Math.random() * 100)), teamB);
            memberRepository.save(member);
        }
    }
}