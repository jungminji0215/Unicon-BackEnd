package sophist.group.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sophist.group.model.SopiGroupMaster;
import sophist.group.repository.GroupRepository;
import sophist.group.service.GroupInfoService;

@Service
public class GroupInfoServiceImpl implements GroupInfoService{
	
	@Resource
	private GroupRepository groupRepository;

	@Override
	public List<SopiGroupMaster> findAllWithFileUsingJoin() {
		return groupRepository.findAllWithFileUsingJoin();
	}


}
