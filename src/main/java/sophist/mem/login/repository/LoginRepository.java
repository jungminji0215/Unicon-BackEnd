package sophist.mem.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sophist.mem.model.SopiMemInfo;

@Repository
public interface LoginRepository extends JpaRepository<SopiMemInfo,String>{
	
	// SELECT * FROM sopimeminfo WHERE memid =?1 AND mempw = ?2;
	//SopiMemInfo findByMemIdAndMemPw(String memId, String memPw);
	public SopiMemInfo findByMemId(String memId);
}
