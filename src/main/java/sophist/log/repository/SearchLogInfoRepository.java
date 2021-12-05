package sophist.log.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sophist.log.model.SopiIpLog;
import sophist.log.model.SopiSearchLog;

@Repository
public interface SearchLogInfoRepository  extends JpaRepository<SopiSearchLog, String> {
	
}
