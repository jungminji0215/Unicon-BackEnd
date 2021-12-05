package sophist.log.model;

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

// 검색어 로그
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiSearchLog implements Serializable {
	
	// 검색어 코드
	@Id 
	private String searchCd;
	
	// 생성일자
	@CreationTimestamp
	private Timestamp createDate;
	
	// 검색어
	@Column(nullable = false, length = 100) 
	private String searchText;
}
