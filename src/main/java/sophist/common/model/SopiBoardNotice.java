package sophist.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 공지사항 게시판
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiBoardNotice implements Serializable {
	
	// 공지사항 상세 코드
	@Id 
	private String boardNoticeCd;
	
	// 게시판 코드 
	@ManyToOne
	@JoinColumn(name="board_cd")
	private SopiBoardMaster sopiBoardMaster;
	
	// 공지사항 제목
	@Column(nullable = false, length = 100) 
	private String boardNoticeTitle;
	
	// 공지사항 내용
	@Column(nullable = false, length = 5000) 
	private String boardNoticeContents;
	
	// 생성일자
	@CreationTimestamp 
	private Timestamp createDate;
	
	// 수정일자
	@CreationTimestamp 
	private Timestamp updateDate;
	
	// 작성자
	@Column(nullable = false, length = 30) 
	private String writer;

	// 고정 여부
	@Column(nullable = false, length = 1) 
	private String fixedYn;
}