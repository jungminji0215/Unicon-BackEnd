package sophist.group.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 모임 감상문
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiGroupReview implements Serializable {
	
	// 리뷰 코드
	@Id 
	private String previewCd;
	
	// 맵핑 코드 (FK)
	@ManyToOne 
	@JoinColumn(name="mapping_cd")
	private SopiGroupMemMapping sopiGroupMemMapping;

	// 내용
	@Column(nullable = false, length = 5000)
	private String contents;

	// 생성일자
	@CreationTimestamp 
	private Timestamp createDate;
	

}
