package sophist.mem.repository;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 사용자 통계
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiMemStatistics implements Serializable{
	
	// 통계코드
	@Id 
	private String statisticsCd;

}