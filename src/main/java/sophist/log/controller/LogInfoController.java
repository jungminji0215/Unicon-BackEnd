package sophist.log.controller;

import javax.annotation.Resource;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import sophist.log.model.SopiIpLog;
import sophist.log.model.SopiSearchLog;
import sophist.log.service.LogInfoService;


@RestController
public class LogInfoController {
	
	@Resource
	private LogInfoService logInfoService;
	
	@GetMapping(value = "/sophist/ipLog")
	public Page<SopiIpLog> selectIpLogList(Pageable pageable) throws Throwable{
		return logInfoService.selectIpLogList(pageable);
		
	}
	
	@GetMapping(value="/sophist/searchLog")
	public Page<SopiSearchLog> selectSearchLogList(Pageable pageable) throws Throwable{
		return logInfoService.selectSearchLogList(pageable);
	}
	
	@PostMapping(value = "/sophist/saveLog")
	public void insertSearchLog(SopiSearchLog sopiSearchLog) throws Throwable{
		logInfoService.insertSearchLog(sopiSearchLog);
	}
	
	@GetMapping(value="/sophist/searchLogDetail")
	public SopiSearchLog selectSearchLogDetail(String searchCd) throws Throwable{
		return logInfoService.selectSearchLogDetail(searchCd);
	}
	
	

}
