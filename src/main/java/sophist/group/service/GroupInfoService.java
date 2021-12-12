package sophist.group.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sophist.group.model.SopiGroupMaster;


public interface GroupInfoService {

	public List<SopiGroupMaster> findAllWithFileUsingJoin();
	
}
