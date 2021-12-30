package sophist.mem.login.model;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseLogin {
	
	
	private String memId;
	
	// 사용자 닉네임
	@Column(nullable = false, length = 20)
	private String memNickname;
	
	// 성별
	private String memGender;
	
	// 사용자 내용
	private String memContents;
	
}
