package sophist.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sophist.group.model.SopiGroupMemMapping;

// 카테고리
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiCategory implements Serializable{
	
	// 카테고리 코드
	@Id 
	private String categoryCd;
	
	// 맵핑 코드
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mapping_cd")
	private SopiGroupMemMapping sopiGroupMemMapping;
	
	// 카테고리 이름
	@Column(nullable = false, length = 20)
	private String categoryName;
	
	// 카테고리 상태
	@Column(nullable = false, length = 2)
	private String categoryState;

}
