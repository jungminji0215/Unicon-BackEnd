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
	
	@Query("SELECT sgm.groupName as groupName,sgm.groupCd,sgm.groupDesc,sfd.filePath,sfd.originFileName,sctg.categoryName,sgd.groupStarPoint "
			+ " FROM SopiGroupMaster sgm "
			+ " inner join SopiFileMaster sfm on sgm.fileCd=sfm.fileCd"
			+ " inner join SopiFileDetail sfd on sfm.fileCd=sfd.fileCd"
			+ " inner join SopiGroupMemMapping sgmm on sgm.groupCd = sgmm.groupCd"
			+ " inner join SopiCategory sctg on sgmm.mappingCd =sctg.mappingCd"
			+ " inner join SopiGroupDetail sgd on sgmm.mappingCd = sgd.mappingCd"
			+ " where sctg.categoryState ='Y'"
			+ " and sgm.groupState='Y' "
			+ " and sgd.groupStarPoint >= 4 "
			)
	public Page<SopiGroupMaster> findAllGroupListByStarPoint(Pageable pageable);
	
	@Query("SELECT sgm.groupName as groupName,sgm.groupCd,sgm.groupDesc,sfd.filePath,sfd.originFileName,sctg.categoryName,sgd.groupStarPoint "
			+ " FROM SopiGroupMaster sgm "
			+ " inner join SopiFileMaster sfm on sgm.fileCd=sfm.fileCd"
			+ " inner join SopiFileDetail sfd on sfm.fileCd=sfd.fileCd"
			+ " inner join SopiGroupMemMapping sgmm on sgm.groupCd = sgmm.groupCd"
			+ " inner join SopiCategory sctg on sgmm.mappingCd =sctg.mappingCd"
			+ " inner join SopiGroupDetail sgd on sgmm.mappingCd = sgd.mappingCd"
			+ " where sctg.categoryState ='Y'"
			+ " and sgm.groupState='Y' "
			+ " and sgd.groupCreateDate > now() +'-1 days'"
			)
	public Page<SopiGroupMaster> findAllGroupListByCurrent(Pageable pageable);
}
