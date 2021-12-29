package sophist.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

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
@Table(name="sopi_file_detail")
public class SopiFileDetail {
	
	// 파일 상세 코드
	@Id 
	@GeneratedValue(generator = "file_detail_id")
	@GenericGenerator(name="file_detail_id", strategy = "sophist.common.channel.FileDetailIdGenerator")
	private String fileDetailCd;
	
	// 파일 코드
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="file_cd",insertable = false,updatable = false)
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
	private long fileSize;
	
	// 생성일자
	@CreationTimestamp 
	private Timestamp createDate;
	
	// 수정일자
	@CreationTimestamp 
	private Timestamp updateDate;
	
	@Column(name="file_cd")
	private String fileCd;
}