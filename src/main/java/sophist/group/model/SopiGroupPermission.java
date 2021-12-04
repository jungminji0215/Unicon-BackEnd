package sophist.group.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 모임 허가
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiGroupPermission implements Serializable{
	
	// 허가코드
	@Id 
	private String permissionCd;
	
	// 맵핑코드
	@ManyToOne 
	@JoinColumn(name="mapping_cd")
	private SopiGroupMemMapping sopiGroupMemMapping;
	
	// 허가상태
	@Column(nullable = false, length = 1)
	private String permissionState;

}
