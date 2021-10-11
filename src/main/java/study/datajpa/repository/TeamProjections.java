package study.datajpa.repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

public interface TeamProjections {

    String getName();

    //SpEL을 사용할 수 있다.
    //단, 이 경우 일단 모든 엔티티를 읽어서 처리하므로 쿼리 최적화를 할 수 없다.
    @Value("#{target.members.size()}")
    Long getMemberCount();
}
