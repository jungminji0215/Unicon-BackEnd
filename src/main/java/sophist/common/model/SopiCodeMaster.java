package sophist.common.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sophist.mem.model.SopiMemMessage;

// 코드 마스터
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiCodeMaster implements Serializable {
	
	// 모듈코드
	@Id 
	private String moduleCd;
	
	// 마스터 코드
	@Id
	private String masterCd;
	
	// 모듈이름
	private String moduleName;
	
	// 모듈설명  
	@Column(nullable = false, length = 100) 
	private String messageContents;

	// 사용여부 
	@Column(nullable = false, length = 1) 
	private String messageState;
	
	// 생성일자
	@CreationTimestamp 
	private Timestamp createDate;
	
	// 생성자
	@Column(nullable = false, length = 30) 
	private String createUser;
	
	// 수정일자
	@CreationTimestamp 
	private Timestamp updateDate;
	
	// 수정자
	@Column(nullable = false, length = 30) 
	private String updateUser;
	
	@OneToMany(mappedBy = "sopiCodeMaster")
	private List<SopiCodeMaster> sopiCodeMaster;
	

}
