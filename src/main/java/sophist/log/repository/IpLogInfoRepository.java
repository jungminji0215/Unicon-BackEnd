package sophist.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sophist.log.model.SopiIpLog;

@Repository
public interface IpLogInfoRepository  extends JpaRepository<SopiIpLog, String> {
	
}
