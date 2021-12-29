package sophist.group.repository;


import sophist.group.model.SopiGroupMaster;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface GroupRepository extends JpaRepository<SopiGroupMaster,String>{
	
	@Query("SELECT gm FROM SopiGroupMaster gm "
			+ " inner join SopiFileMaster sfm on gm.fileCd=sfm.fileCd"
			+ " inner join SopiFileDetail sfd on sfd.fileCd=sfm.fileCd"
			+ " where sfd.fileCd= :fileCd"
			)
	public Page<SopiGroupMaster> findAllWithFileUsingJoin(String fileCd,Pageable pageable);
}
