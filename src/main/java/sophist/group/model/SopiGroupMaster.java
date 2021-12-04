package sophist.group.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 모임 마스터
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiGroupMaster implements Serializable{
	
	// 모임 코드
	@Id 
	@Column(nullable = false, length = 10)
	private String groupCd;
	
	// 모임 이름
	@Column(nullable = false, length = 30)
	private String groupName;
	
	// 모임 상태
	@Column(nullable = false, length = 1)
	private String groupState;

	// 모임 설명 
	@Column(nullable = false, length = 300)
	private String groupDesc;

}
