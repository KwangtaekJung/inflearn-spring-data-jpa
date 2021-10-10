package study.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.account.Member;
import study.datajpa.account.MemberRepository;

@Component
@Transactional
public class AppRunner implements ApplicationRunner {

    @Autowired
    MemberRepository accountRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Member member = new Member();
        member.setUsername("ktjung");

        Member savedMember = accountRepository.save(member);

        System.out.println("savedAccount = " + savedMember);
    }
}
