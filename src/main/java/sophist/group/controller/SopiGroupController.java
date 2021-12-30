package sophist.group.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sophist.group.model.SopiGroupDetail;
import sophist.group.model.SopiGroupMaster;
import sophist.group.service.GroupInfoService;


@RestController
public class SopiGroupController {
	
	@Resource
	private GroupInfoService groupInfoService;
	
	@GetMapping(value="/bestGroupList")
	public Page<SopiGroupMaster> findAllGroupListByStarPoint(@PageableDefault(size = 3,sort = "sgd.groupStarPoint",direction = Direction.DESC)Pageable pageable) throws Throwable{
		return groupInfoService.findAllGroupListByStarPoint(pageable);
	}
	
	@GetMapping(value="/currentGroupList")
	public Page<SopiGroupMaster> findAllGroupListByCurrent(@PageableDefault(size = 3,sort = "sgd.groupCreateDate",direction = Direction.DESC)Pageable pageable) throws Throwable{
		return groupInfoService.findAllGroupListByCurrent(pageable); 
	}
	
	
	@GetMapping(value="/group/{groupDetailCd}")
	public Page<SopiGroupMaster> findByGroupCd(@PathVariable("groupDetailCd") String groupDetailCd,@PageableDefault(size = 3,sort = "sgd.groupCreateDate",direction = Direction.DESC)Pageable pageable) {
		return groupInfoService.findByGroupCd(groupDetailCd, pageable);
	}
}
