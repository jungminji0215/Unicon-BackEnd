package sophist.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import sophist.common.model.SopiFileDetail;

public interface FileDetailRepository extends JpaRepository<SopiFileDetail, String>{
	// file_cd 값 불러오기
	String findByFileCd(String fileCd);

}
