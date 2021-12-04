package sophist.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 파일 마스터
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiFileMaster implements Serializable{
	
	// 파일코드
	@Id 
	private String fileCd;
	
	// 모듈코드
//	@ManyToOne
//	@JoinColumn(name="module_cd")
	private SopiCodeMaster sopiCodeMaster;

}