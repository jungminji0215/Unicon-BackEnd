package sophist.mem.login.model;

import lombok.Data;

// 네이버
@Data
public class NaverProfile {

	public String resultcode;
	public String message;
	public Response response;
	
	@Data
	public class Response {

		public String id;
		public String nickname;
		public String profile_image;
		public String gender;
		public String email;
		public String name;

	}

}




