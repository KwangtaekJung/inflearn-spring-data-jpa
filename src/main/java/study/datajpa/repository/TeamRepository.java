package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.datajpa.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("select new study.datajpa.repository.TeamDto(t.name, count(m.id)) " +
           "from Team t join t.members m group by t.name")
    List<TeamDto> findTeamDto();

    List<TeamProjections> findTeamProjectionsBy();
}
