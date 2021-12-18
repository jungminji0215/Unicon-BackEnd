package sophist.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	@Column(name = "board_cd",length = 10,insertable = false,updatable = false)
	private String boardCd;
	
	// 모듈 코드 
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="board_cd",insertable = false,updatable = false),
		@JoinColumn(name="module_cd",insertable = false,updatable = false)
	})
	private SopiCodeMaster sopiCodeMaster;
	
	@Column(name="module_cd",length = 10)
	private String moduleCd;
	
	@OneToMany(mappedBy = "sopiBoardMaster")
	private List<SopiBoardNotice> SopiBoardNoticeList;
}