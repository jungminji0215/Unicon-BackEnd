package sophist.mem.login.model;

import lombok.Data;

@Data
public class OAuthTokenNaver {
	private String access_token;
	private String token_type;
	private String refresh_token;
	private int expires_in;
}
