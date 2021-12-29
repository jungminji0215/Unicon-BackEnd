package sophist.mem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import sophist.mem.model.SopiMemInfo;

@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<SopiMemInfo,String>{
	
}

