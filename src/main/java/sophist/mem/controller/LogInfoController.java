package sophist.mem.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sophist.log.model.SopiIpLog;
import sophist.log.model.SopiSearchLog;
import sophist.log.service.LogInfoService;

@RestController
public class LogInfoController {
	
	@Resource
	private LogInfoService logInfoService;
	
	
	@GetMapping("/sophist/ipLog")
	public Page<SopiIpLog> selectIpLogList(@PageableDefault(size = 10,sort = "ipCd",direction = Direction.DESC ) Pageable pageable) throws Throwable{
		return logInfoService.selectIpLogList(pageable);
	}
	
	@GetMapping("/sophist/SearchLog")
	public Page<SopiSearchLog> selectSearchLogList(@PageableDefault(size = 10,sort = "searchCd",direction = Direction.DESC ) Pageable pageable) throws Throwable{
		return logInfoService.selectSearchLogList(pageable);
	}
	
}
