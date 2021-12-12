package sophist.group.controller;

import java.util.List;

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
	
	@GetMapping(value="/sophist/groupList")
	public List<SopiGroupMaster> selectGroupList(Pageable pageable) throws Throwable{
		return groupInfoService.findAllWithFileUsingJoin(pageable);
	}
}
