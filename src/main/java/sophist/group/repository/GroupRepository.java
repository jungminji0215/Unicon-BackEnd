package sophist.group.repository;


import sophist.group.model.SopiGroupMaster;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<SopiGroupMaster,String>{
	
	@Query("SELECT gm,fm FROM SopiGroupMaster gm join fetch gm.sopiFileMaster inner join SopiFileDetail fm ON fm.fileCd=gm.fileCd")
	public List<SopiGroupMaster> findAllWithFileUsingJoin(Pageable pageable);
}
