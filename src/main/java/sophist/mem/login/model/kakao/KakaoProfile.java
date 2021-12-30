package sophist.mem.login.model.kakao;

import lombok.Data;

@Data
public class KakaoProfile {
	public Integer id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;
}
