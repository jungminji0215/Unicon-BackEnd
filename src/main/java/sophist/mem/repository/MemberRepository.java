package sophist.mem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import sophist.mem.model.SopiMemInfo;

@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<SopiMemInfo,String>{
	
	@Query(value=" select new sophist.mem.model.SopiMemInfo(smi.memId as memId,smi.memNickname as memNickname"
			+ ",smi.memGender as memGender, smi.memContents as memContents) "
			+ "from SopiMemInfo smi where memId = :memId")
	public SopiMemInfo findGroupLeaderByMemId(String memId);
}
