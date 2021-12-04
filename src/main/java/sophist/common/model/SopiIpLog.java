package sophist.common.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// IP 로그
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiIpLog implements Serializable{
	
	// IP 코드
	@Id 
	private String ipCd;
	
	// IP 주소
	@Column(nullable = false, length = 20) 
	private String ipAddr;
	
	// 생성일자
	@CreationTimestamp 
	private Timestamp createDate;

	// URL
	@Column(nullable = false, length = 100) 
	private String Url;

}
