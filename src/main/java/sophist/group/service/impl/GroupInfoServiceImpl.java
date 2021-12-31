package sophist.group.service.impl;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sophist.group.model.SopiGroupDetail;
import sophist.group.model.SopiGroupMaster;
import sophist.group.repository.GroupRepository;
import sophist.group.service.GroupInfoService;
import sophist.mem.model.SopiMemInfo;
import sophist.mem.repository.MemberRepository;

@Service
public class GroupInfoServiceImpl implements GroupInfoService{
	
	@Resource
	private GroupRepository groupRepository;
	
	@Resource
	private MemberRepository memberRepository; 

	@Override
	public Page<Map<String,Object>> findAllGroupListByStarPoint(Pageable pageable) {
		Page<Map<String,Object>> findAllGroupListByStarPoint=groupRepository.findAllGroupListByStarPoint(pageable);
		return findAllGroupListByStarPoint;
	}

	@Override
	public Page<Map<String,Object>> findAllGroupListByCurrent(Pageable pageable) {
		return groupRepository.findAllGroupListByCurrent(pageable);
	}

	@Override
	public Page<Map<String,Object>> findByGroupCd(String groupDetailCd,Pageable pageable) {
		return groupRepository.findByGroupCd(groupDetailCd, pageable);
	}

	@Override
	public SopiMemInfo findGroupLeaderByMemId(String memId) {
		return memberRepository.getById(memId);
	}

	
}
