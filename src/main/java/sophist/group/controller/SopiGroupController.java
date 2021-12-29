package sophist.group.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sophist.group.model.SopiGroupMaster;
import sophist.group.service.GroupInfoService;


@RestController
public class SopiGroupController {
	
	@Resource
	private GroupInfoService groupInfoService;
	
	@GetMapping(value="/bestGroupList")
	public Page<SopiGroupMaster> findAllGroupListByStarPoint(Pageable pageable) throws Throwable{
		return groupInfoService.findAllGroupListByStarPoint(pageable);
	}
	
	@GetMapping(value="/CurrentGroupList")
	public Page<SopiGroupMaster> findAllGroupListByCurrent(Pageable pageable) throws Throwable{
		return groupInfoService.findAllGroupListByCurrent(pageable); 
	}
}
