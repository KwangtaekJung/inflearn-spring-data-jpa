package study.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;
import study.datajpa.entity.Team;
import study.datajpa.repository.TeamRepository;

@Component
@Transactional
public class AppRunner implements ApplicationRunner {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //Test에 영향을 줄 수 있으므로 주석처리한다.
//        Team team = new Team("TeamA");
//        teamRepository.save(team);
//
//        Member member = new Member("User01", 20, team);
//        Member savedMember = memberRepository.save(member);
//
//        System.out.println("savedAccount = " + savedMember);
    }
}
