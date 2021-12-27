package sophist.mem.login.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sophist.mem.login.ResponseDto;
import sophist.mem.login.model.KakaoProfile;
import sophist.mem.login.model.NaverProfile;
import sophist.mem.login.model.OAuthTokenKakao;
import sophist.mem.login.model.OAuthTokenNaver;
import sophist.mem.login.service.impl.LoginService;
import sophist.mem.model.SopiMemInfo;


// 프론트엔드 , 백엔드 서버가 분리 되어있기 때문에 CORS 문제가 발생 
// 정상으로 통신하기 위해서 @CrossOrigin 어노테이션을 통해 해당 도메인에서 접근을 허용해주어야 한다.
@CrossOrigin (origins ="http://localhost:3000")
@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	
	// 회원가입 
	@PostMapping("/join")
	public  ResponseDto<String> Join(@RequestBody SopiMemInfo sopiMemInfo) { 
		String result = loginService.Join(sopiMemInfo);
		return new ResponseDto<String>(HttpStatus.OK.value(), result) ;
	}
	
	// 로그인 
    @PostMapping("/login")
	public ResponseDto<String> login(@RequestBody SopiMemInfo sopiMemInfo, HttpSession session) {
    	
		if(loginService.SearchMem(sopiMemInfo.getMemId()) == null) {
			return new ResponseDto<String>(HttpStatus.OK.value(), "fail") ;
		}
		
		String result = loginService.login(sopiMemInfo);
		
		
		
		// 유저 잘 불러왔으면 세션이 만들어짐
		if(result != "fail" ) {
			session.setAttribute("user", sopiMemInfo.getMemId()); 
		} 
		return new ResponseDto<String>(HttpStatus.OK.value(), result) ;
	}
	
	// 카카오 로그인
	@GetMapping("/social/login/kakao")
	public ResponseDto<String> kakaoCallback(@RequestParam("code") String code) { 
		
		// POST 방식으로 key=value 데이터를 요청(카카오쪽으로)
		RestTemplate rt = new RestTemplate(); // http 요청 굉장히 편하게 할 수 있는 라이브러리
		HttpHeaders headers = new HttpHeaders(); // HttpHeader 객체 생성
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 내가 전송한 http body 데이터가 key=value 형태라고 알려주는 것
		
		// body 데이터 담을 객체 (HttpBody 객체 생성)
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "13413852fa4149e062c0e50ba100b290");
		params.add("redirect_uri", "http://localhost:8000/social/login/kakao");
		params.add("code", code);
		
		// 바디와 헤더값을 갖는 엔티티가 된다.
		// HttpHeader와 HttpBody를 하나의 오브젝트에 담기 
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params, headers);
		
		// 실제 Http 요청해보기 - Post 방식으로 - 그리고 response 변수의 응답 받음
		ResponseEntity<String> response = rt.exchange( // exchange함수는 httpentity 객체 넣게 되어있다.
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST, // 요청 메서드가 무엇인지 -POST
				kakaoTokenRequest, // 바디, 헤더값 한번에 넣기
				String.class // 응답은 스트링으로 받겠다.
		);
		
		// request.getBody() 정보를 객체에 담기?
		// Gson, Json Simple, ObjectMapper
		ObjectMapper objectMapper = new ObjectMapper();
	
		OAuthTokenKakao oauthToken = null;
		
		try {
			 oauthToken = objectMapper.readValue(response.getBody(), OAuthTokenKakao.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		// 사용자 정보 요청?
		RestTemplate rt2 = new RestTemplate(); 
		HttpHeaders headers2 = new HttpHeaders(); 
		
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); 
		
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
				new HttpEntity<>(headers2);
		
		ResponseEntity<String> response2 = rt2.exchange( 
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST, 
				kakaoProfileRequest2, 
				String.class 
		);
		
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
//		// 인증된 코드값을 통해서 엑세스 토큰을 발급
//		System.out.println(response.getBody());
//		
//		// 코드받고 엑세스토큰 요청받고, 엑세스토큰으로 회원정보 조회한 것을 출력
//		System.out.println(response2.getBody());
//		
//		// 사용자 정보 확인
//		System.out.println(kakaoProfile.getId());
//		System.out.println( kakaoProfile.getKakao_account().getEmail());
			
		/////////////////////////////////////////////////////////////
		// 카카오로부터 받은정보를 통해서 소피스트에 맞게 넣어야함(?)
		// 즉, 다음 정보로 회원가입 강제로 
		// 소피스트 유저 정보 : memId, memPw, memNickname, memGender, memState, memContents, snsConfirm
		
		//memId
		System.out.println("소피스트 서버 이메일 " + kakaoProfile.getKakao_account().getEmail());
		String memId = kakaoProfile.getKakao_account().getEmail();
		// memNickname
		System.out.println("소피스트 서버 유저 닉네임: " + kakaoProfile.getProperties().getNickname());
		String memNickname = kakaoProfile.getProperties().getNickname();
		
		// memPw
		// 랜덤 임시패스워드?
		//UUID garbagePassword = UUID.randomUUID();
		//System.out.println("소피스트 서버 패스워드 " + garbagePassword);
		//String memPw = garbagePassword.toString();
		/////////////////////////////////////////////////////////////
		
		SopiMemInfo kakaoUser = SopiMemInfo.builder()
				.memId(memId) // 이메일
				.memNickname(memNickname) // 임시 닉네임 
//				.memPw(memPw)
				.memPw("13954a298aa3509ed88938884ee7f74227fc23069cfe692b8e20df54b8a7ebe2")
				.snsConfirm("Kakao")
				.memGender("E")
				.memState("Y") // Y : 횔동회원, N : 탈퇴
				.build();
				
		//  가입자 비가입자 체크해서 처리 originUser : 기존 가입자
		SopiMemInfo originUser =  loginService.SearchMem(kakaoUser.getMemId());
		
		// 비가입자면 회원가입하고 로그인 처리
		if(originUser == null) {
			System.out.println("----------비가입자----------");
			String result = loginService.Join(kakaoUser);
			return new ResponseDto<String>(HttpStatus.OK.value(), result) ;
		}
			
		// 가입자면 바로 로그인 처리
		String result = loginService.login(kakaoUser);
			
		return new ResponseDto<String>(HttpStatus.OK.value(), result) ;
	}
	
	
	
	// 네이버
	@GetMapping("/social/login/naver")
	public ResponseDto<String> naverCallback(@RequestParam("code") String code)  { 
		
		
		HttpHeaders headers = new HttpHeaders(); 
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 내가 전송한 http body 데이터가 key=value 형태라고 알려주는 것
		
		// body 데이터 담을 객체 (HttpBody 객체 생성)
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "FQfWYUQMCFhjJlwg6pLM");
		params.add("client_secret", "bKGKatJhbw");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> NaverTokenRequest =
				new HttpEntity<>(params, headers);
		
		RestTemplate rt = new RestTemplate(); 
		
		ResponseEntity<String> accessToken = rt.exchange( 
				"https://nid.naver.com/oauth2.0/token",
				HttpMethod.POST, 
				NaverTokenRequest, 
				String.class 
		);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		OAuthTokenNaver oauthToken = null;
		
		try {
			 oauthToken = objectMapper.readValue(accessToken.getBody(), OAuthTokenNaver.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		// 사용자 정보요청
		RestTemplate rt2 = new RestTemplate(); 
		HttpHeaders headers2 = new HttpHeaders(); 
		
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); 

		
		HttpEntity<MultiValueMap<String, String>> NaverProfileRequest =
				new HttpEntity<>(headers2);
	
		ResponseEntity<String> requestProfile = rt2.exchange( 
				"https://openapi.naver.com/v1/nid/me",
				HttpMethod.POST, 
				NaverProfileRequest, 
				String.class 
		);
		

		
		// 사용자 정보 가져오기
		ObjectMapper objectMapper2 = new ObjectMapper();
		NaverProfile naverProfile = null;
		
		try {
			naverProfile = objectMapper2.readValue(requestProfile.getBody(), NaverProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		// 이메일
		// 유저네임
		// 성별
		// 비번
		
		System.out.println("네이버 서버 이메일 " + naverProfile.getResponse().getEmail());
		String memId = naverProfile.getResponse().getEmail();
		System.out.println("네이버 서버 닉네임 " + naverProfile.getResponse().getNickname());
		String memNickname = naverProfile.getResponse().getNickname();
		System.out.println("네이버 서버 성별 " + naverProfile.getResponse().getGender());
		String memGender = naverProfile.getResponse().getGender();
		
		SopiMemInfo NaverUser = SopiMemInfo.builder()
				.memId(memId) // 이메일
				.memNickname(memNickname) // 임시 닉네임 
				.memPw("1234")
				.snsConfirm("Naver")
				.memGender(memGender)
				.memGender("N")
				.memState("Y") // Y : 횔동회원, N : 탈퇴
				.build();
				
		//  가입자 비가입자 체크해서 처리 originUser : 기존 가입자
		SopiMemInfo originUser =  loginService.SearchMem(NaverUser.getMemId());
		
		// 비가입자면 회원가입하고 로그인 처리
		if(originUser == null) {
			System.out.println("----------비가입자----------");
			String result = loginService.Join(NaverUser);
			return new ResponseDto<String>(HttpStatus.OK.value(), result) ;
		}
			
		// 가입자면 바로 로그인 처리
		String result = loginService.login(NaverUser);
			
		return new ResponseDto<String>(HttpStatus.OK.value(), result) ;
	}
}