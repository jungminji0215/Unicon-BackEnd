package sophist.log.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sophist.log.model.SopiIpLog;
import sophist.log.model.SopiSearchLog;

public interface LogInfoService {
	
	public Page<SopiIpLog> selectIpLogList(Pageable pageable) throws Throwable;
	
	public Page<SopiSearchLog> selectSearchLogList(Pageable pageable) throws Throwable;
}
