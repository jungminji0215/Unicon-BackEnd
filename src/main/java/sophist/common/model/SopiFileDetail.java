package sophist.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 상세 파일 정보
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiFileDetail implements Serializable{
	
	// 파일 상세 코드
	@Id 
	private String fileDetailCd;
	
	// 파일 코드
	@OneToOne
	@JoinColumn(name="file_cd")
	private SopiFileMaster sopiFileMaster;
	
	// 파일 경로
	@Column(nullable = false, length = 400) 
	private String filePath;

	// 원파일명
	@Column(nullable = false, length = 200) 
	private String originFileName;
	
	// 시스템파일명
	@Column(nullable = false, length = 200) 
	private String sysFileName;
	
	// 파일크기
	private int fileSize;
	
	// 생성일자
	@CreationTimestamp 
	private Timestamp createDate;
	
	// 수정일자
	@CreationTimestamp 
	private Timestamp updateDate;
}