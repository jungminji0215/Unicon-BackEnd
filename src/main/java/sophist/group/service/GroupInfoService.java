package sophist.group.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sophist.mem.model.SopiMemInfo;

public interface GroupInfoService {

	public Page<Map<String, Object>> findAllGroupListByStarPoint(Pageable pageable);

	public Page<Map<String, Object>> findAllGroupListByCurrent(Pageable pageable);

	public Page<Map<String, Object>> findByGroupCd(String groupDetailCd, Pageable pageable);

	public SopiMemInfo findGroupLeaderByMemId(String memId);
}
