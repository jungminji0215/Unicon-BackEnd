package sophist.mem.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiMemMessage implements Serializable{
	
	// 메시지 코드
	@Id 
	private String messageCd;
	
	// 아이디
	private String memId;
	
	// 메시지 내용
	private String messageContents;
	
	// 메시지 상태
	private String messageState;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="mem_id")
	private SopiMemMessage sopiMemMessage;

}
