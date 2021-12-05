package sophist.log.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import sophist.log.model.SopiIpLog;
import sophist.log.model.SopiSearchLog;
import sophist.log.service.LogInfoService;

public class LogInfoController {
	
	@Resource
	private LogInfoService logInfoService;
	
	@GetMapping
	public Page<SopiIpLog> selectIpLogList(Pageable pageable) throws Throwable{
		return logInfoService.selectIpLogList(pageable);
		
	}
	
	@GetMapping
	public Page<SopiSearchLog> selectSearchLogList(Pageable pageable) throws Throwable{
		return logInfoService.selectSearchLogList(pageable);
		
	}

}
