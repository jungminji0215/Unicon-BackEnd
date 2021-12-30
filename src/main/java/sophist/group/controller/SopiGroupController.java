package sophist.group.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import sophist.group.service.GroupInfoService;
import sophist.mem.model.SopiMemInfo;

@RestController
public class SopiGroupController {

	@Resource
	private GroupInfoService groupInfoService;

	@GetMapping(value = "/bestGroupList", produces = MediaType.APPLICATION_JSON_VALUE)
	public Page<Map<String,Object>> findAllGroupListByStarPoint(@PageableDefault(size = 3,sort ="sgd.groupStarPoint",direction = Direction.DESC) Pageable pageable)
			throws Throwable {
		return groupInfoService.findAllGroupListByStarPoint(pageable);
	}

	@GetMapping(value = "/currentGroupList")
	public Page<Map<String,Object>> findAllGroupListByCurrent(@PageableDefault(size = 3, sort = "sgd.groupCreateDate", direction = Direction.DESC) Pageable pageable) throws Throwable {
		return groupInfoService.findAllGroupListByCurrent(pageable);
	}

	@GetMapping(value = "/group/{groupDetailCd}")
	public Page<Map<String,Object>> findByGroupCd(@PathVariable("groupDetailCd") String groupDetailCd,@PageableDefault(size = 3, sort = "sgd.groupCreateDate", direction = Direction.DESC) Pageable pageable){
		return groupInfoService.findByGroupCd(groupDetailCd, pageable);
	}
	
	@GetMapping(value="/group/groupLeader")
	public SopiMemInfo findGroupLeaderByMemId(String memId) {
		return groupInfoService.findGroupLeaderByMemId(memId);
	}
}
