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

// 모임 상세 정보
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiGroupDetail implements Serializable{
	
	// 모임 상세정보 코드
	@Id 
	private String groupDetailCd;
	
	// 맵핑코드 (FK)
	@ManyToOne 
	@JoinColumn(name="mapping_cd",insertable = false,updatable = false) 
	private SopiGroupMemMapping sopiGroupMemMapping;
	
	// 모임 인원  
	private int groupHeadCount;
	
	// 모임 시작일자
	@CreationTimestamp 
	private Timestamp groupStartDate;
	
	// 모임 종료일자
	@CreationTimestamp 
	private Timestamp groupEndDate;
	
	// 모임 생성일자
	@CreationTimestamp 
	private Timestamp groupCreateDate;
	
	// 모임 수정일자
	@CreationTimestamp 
	private Timestamp groupUpdateDate;

	// 모임 리더
	@Column(nullable = false, length = 30)
	private String groupLeader;
	
	// 모임 시작일
	@Column(nullable = false, length = 1)
	private String groupStartDay;
	
	// 모임 별점
	@Column(name="group_star_poinrt")
	private int groupStarPoint;
	
	@Column(name="mapping_cd")
	private String mappingCd;
}
