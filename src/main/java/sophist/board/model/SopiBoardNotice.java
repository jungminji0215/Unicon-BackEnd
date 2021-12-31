package sophist.board.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "sopi_board_notice")
public class SopiBoardNotice implements Serializable {
	
	// 공지사항 상세코드
	@Id
	@Column(name="board_notice_cd")
	private String boardNoticeCd;
	
	// 공지사항 내용
	@Column(name="board_notice_contents")
	private String boardNoticeContents;
	
	// 공지사항 제목
	@Column(name="board_notice_title")
	private String boardNoticeTitle;
	
	// 생성일자
	@CreationTimestamp
	@Column(name="create_date")
	private Timestamp createDate;
	
	// 수정일자
	@UpdateTimestamp
	@Column(name="update_date")
	private Timestamp updateDate;
	
	// 작성자
	@Column(name="writer")
	private String writer;
	
	// 고정여부
	@Column(name="fixed_yn")
	private String fixedYn;
	
	// 게시판 매핑 모델
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_cd",insertable = false,updatable = false)
	private SopiBoardMaster sopiBoardMaster;
	
	// 게시판 코드
	@Column(name="board_cd")
	private String boardCd;
}
