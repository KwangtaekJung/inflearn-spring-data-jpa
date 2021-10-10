package study.datajpa.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Transactional
@Rollback(value = false)  //H2DB를 인메모리로 사용할 경우에는 테스트 종료 후 어차피 사라질것이므로 의미 없다.
class MemberRepositoryTest {

    @Autowired
    MemberRepository accountRepository;

    @Autowired
    TeamRepository teamRepository;

    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll");
    }

    @Test
    public void createMemberTest() {
        Team team = new Team("testTeam");
        teamRepository.save(team);

        Member member = new Member("testUser", 20, team);
        Member savedMember = accountRepository.save(member);

        Optional<Member> findAccount = accountRepository.findById(savedMember.getId());

        assertThat(findAccount.isEmpty()).isFalse();
        assertThat(findAccount.get().getId()).isEqualTo(savedMember.getId());
        assertThat(findAccount.get().getUsername()).isEqualTo("testUser");
        assertThat(findAccount.get()).isEqualTo(savedMember);
    }
}