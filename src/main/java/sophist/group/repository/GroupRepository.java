package sophist.group.repository;

import sophist.group.model.SopiGroupMaster;
import sophist.mem.model.SopiMemInfo;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface GroupRepository extends JpaRepository<SopiGroupMaster,String>{
	
	String findAllGroupListByStarPointQuery = 
			" SELECT new map(sgm.groupName as groupName,sgm.groupCd as groupCd,sgm.groupDesc as groupDesc ,sfd.filePath as filePath,sfd.originFileName as originFileName,sctg.categoryName as categoryName,sgd.groupStarPoint as groupStarPoint,sgd.groupDetailCd as groupDetailCd) "
			+ " FROM SopiGroupMaster sgm "
			+ " inner join SopiFileMaster sfm on sgm.fileCd=sfm.fileCd"
			+ " inner join SopiFileDetail sfd on sfm.fileCd=sfd.fileCd"
			+ " inner join SopiGroupMemMapping sgmm on sgm.groupCd = sgmm.groupCd"
			+ " inner join SopiCategory sctg on sgmm.mappingCd =sctg.mappingCd"
			+ " inner join SopiGroupDetail sgd on sgmm.mappingCd = sgd.mappingCd"
			+ " where sctg.categoryState ='Y'"
			+ " and sgm.groupState='Y' "
			+ " and sgd.groupStarPoint >= 4 ";
	@Query(value=findAllGroupListByStarPointQuery)
	public Page<Map<String,Object>> findAllGroupListByStarPoint(Pageable pageable);
	
	String findAllGroupListByCurrentQuery =
	" SELECT new map(sgm.groupName as groupName,sgm.groupCd as groupCd,sgm.groupDesc as groupDesc ,sfd.filePath as filePath,sfd.originFileName as originFileName,sctg.categoryName as categoryName,sgd.groupStarPoint as groupStarPoint,sgd.groupDetailCd as groupDetailCd)"
	+ " FROM SopiGroupMaster sgm "
	+ " inner join SopiFileMaster sfm on sgm.fileCd=sfm.fileCd"
	+ " inner join SopiFileDetail sfd on sfm.fileCd=sfd.fileCd"
	+ " inner join SopiGroupMemMapping sgmm on sgm.groupCd = sgmm.groupCd"
	+ " inner join SopiCategory sctg on sgmm.mappingCd =sctg.mappingCd"
	+ " inner join SopiGroupDetail sgd on sgmm.mappingCd = sgd.mappingCd"
	+ " where sctg.categoryState ='Y'"
	+ " and sgm.groupState='Y' "
	+ " and sgd.groupCreateDate > now() +'-1 weeks'";
	@Query(value=findAllGroupListByCurrentQuery)
	public Page<Map<String,Object>> findAllGroupListByCurrent(Pageable pageable);
	
	String findByGroupCdQuery = " SELECT new map(sgm.groupName as groupName,sgm.groupDesc as groupDesc"
			+ " ,sfd.filePath || sfd.originFileName as fileFullPath"
			+ " ,sctg.categoryName as categoryName,sgd.groupStarPoint as groupStarPoint"
			+ " ,sgd.groupHeadCount as groupHeadCount, sgd.groupStartDate || '~' || sgd.groupEndDate as groupDate"
			+ " ,sgd.groupStarPoint as groupStarPoint, sgd.groupLeader as groupLeader"
			+ " ,to_char(sgd.groupStartDate, 'HH'), sgd.groupStartDay as groupStartDay"
			+ " ,sgm.groupState as groupState  )   "
			+ " FROM  SopiGroupMaster sgm "
			+ " inner join SopiFileMaster sfm on sgm.fileCd=sfm.fileCd"
			+ " inner join SopiFileDetail sfd on sfm.fileCd=sfd.fileCd"
			+ " inner join SopiGroupMemMapping sgmm on sgm.groupCd = sgmm.groupCd"
			+ " inner join SopiMemInfo smi on smi.memId = sgmm.memId"
			+ " inner join SopiCategory sctg on sgmm.mappingCd =sctg.mappingCd"
			+ " inner join SopiGroupDetail sgd on sgmm.mappingCd = sgd.mappingCd"
			+ " where sctg.categoryState ='Y'"
			+ " and sgm.groupState='Y' "
			+ " and sgm.groupState='Y' "
			+ " and sgd.groupDetailCd = :groupDetailCd ";
	@Query(value=findByGroupCdQuery)
	public Page<Map<String,Object>> findByGroupCd(String groupDetailCd,Pageable pageable);
	
	
	
}
