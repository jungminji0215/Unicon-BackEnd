package sophist.mem.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestComtroller {
	// 테스트 
	
	@GetMapping("/social")
	public  String kakao() { 
		
		return "login";
	}
	

}
