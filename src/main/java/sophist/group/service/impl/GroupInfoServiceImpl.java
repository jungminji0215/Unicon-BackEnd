package sophist.group.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sophist.group.model.SopiGroupDetail;
import sophist.group.model.SopiGroupMaster;
import sophist.group.repository.GroupRepository;
import sophist.group.service.GroupInfoService;

@Service
public class GroupInfoServiceImpl implements GroupInfoService{
	
	@Resource
	private GroupRepository groupRepository;

	@Override
	public Page<SopiGroupMaster> findAllGroupListByStarPoint(Pageable pageable) {
		return groupRepository.findAllGroupListByStarPoint(pageable);
	}

	@Override
	public Page<SopiGroupMaster> findAllGroupListByCurrent(Pageable pageable) {
		return groupRepository.findAllGroupListByCurrent(pageable);
	}

	@Override
	public Page<SopiGroupMaster> findByGroupCd(String groupDetailCd,Pageable pageable) {
		return groupRepository.findByGroupCd(groupDetailCd, pageable);
	}

	
}
