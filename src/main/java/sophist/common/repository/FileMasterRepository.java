package sophist.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sophist.common.model.SopiFileMaster;

public interface FileMasterRepository extends JpaRepository<SopiFileMaster, String>{
	// 마지막 file_cd 값 불러오기
	@Query("select MAX(m.fileCd) from SopiFileMaster m")
	public String findMasterFileCd();

}
