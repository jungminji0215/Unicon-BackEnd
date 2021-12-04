package sophist.group.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 사용자 그룹 맵핑
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiGroupMemMapping implements Serializable{
	
	// 맵핑 코드
	@Id 
	private String mappingCd;
	
	// 사용자 아이디
	private String memId;
	
	// 모임 코드
	private String groupCd;

}
