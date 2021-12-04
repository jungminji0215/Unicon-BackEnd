package sophist.common.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 게시판 마스터
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiBoardMaster implements Serializable{
	
	// 게시판 코드
	@Id 
	private String boardCd;
	
	// 모듈 코드 
//	@ManyToOne
//	@JoinColumn(name="module_cd")
	private SopiCodeMaster sopiCodeMaster;
}