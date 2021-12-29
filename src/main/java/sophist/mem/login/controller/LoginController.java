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

import sophist.common.ResponseDto;
import sophist.mem.login.model.NaverProfile;
import sophist.mem.login.model.OAuthTokenNaver;
import sophist.mem.login.model.ResponseLogin;
import sophist.mem.login.model.kakao.KakaoProfile;
import sophist.mem.login.model.kakao.OAuthTokenKakao;
import sophist.mem.login.service.impl.LoginService;
import sophist.mem.model.SopiMemInfo;

// 프론트엔드 , 백엔드 서버가 분리 되어있기 때문에 CORS 문제가 발생 
// 정상으로 통신하기 위해서 @CrossOrigin 어노테이션을 통해 해당 도메인에서 접근을 허용해주어야 한다.
@CrossOrigin(origins = "http://localhost:3000 ", allowCredentials = "true") 
@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;

	// 회원가입
	@PostMapping("/join")
	public ResponseDto<String> join(@RequestBody SopiMemInfo sopiMemInfo) {
		String data = loginService.join(sopiMemInfo);
		return new ResponseDto<String>(HttpStatus.OK.value(), data);
	}

	// 로그인
	@PostMapping("/login")
	public ResponseDto<ResponseLogin> login(@RequestBody SopiMemInfo sopiMemInfo, HttpSession session) {
		SopiMemInfo result = loginService.login(sopiMemInfo);

		// 세션이 만들어짐
		if (result != null) {
			session.setAttribute("user", sopiMemInfo.getMemId());
		}

		ResponseLogin user = ResponseLogin.builder().memId(result.getMemId()).memNickname(result.getMemNickname())
				.memContents(result.getMemContents()).memGender(result.getMemGender()).build();

		return new ResponseDto<ResponseLogin>(HttpStatus.OK.value(), user);
	}

	// 카카오 로그인
	@PostMapping("/social/login/kakao")
	public ResponseDto<SopiMemInfo> kakaoCallback(@RequestBody OAuthTokenKakao oAuthTokenKakao) {

		// 프론터에서 넘어 온 토큰 정보로 카카오 서버에 요청 할 차례
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Authorization", "Bearer " + oAuthTokenKakao.getAccess_token());
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers);

		ResponseEntity<String> response = rt.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST,
				kakaoProfileRequest, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		KakaoProfile kakaoProfile = null;

		try {
			kakaoProfile = objectMapper.readValue(response.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String memId = kakaoProfile.getKakao_account().getEmail();
		String memNickname = kakaoProfile.getProperties().getNickname();

		SopiMemInfo kakaoUser = SopiMemInfo.builder().memId(memId).memNickname(memNickname)
				.memPw("13954a298aa3509ed88938884ee7f74227fc23069cfe692b8e20df54b8a7ebe2").snsConfirm("Kakao")
				.memGender("E").memState("Y") // Y : 횔동회원, N : 탈퇴
				.build();

		// 가입자 비가입자 체크해서 처리 originUser : 기존 가입자
		SopiMemInfo originUser = loginService.searchMem(kakaoUser.getMemId());

		// 비가입자면 회원가입하고 로그인 처리
		if (originUser == null) {
			loginService.join(kakaoUser);
		}

		// 가입자면 바로 로그인 처리
		loginService.login(kakaoUser);

		return new ResponseDto<SopiMemInfo>(HttpStatus.OK.value(), kakaoUser);
	}

	// 네이버
	@GetMapping("/social/login/naver")
	public ResponseDto<String> naverCallback(@RequestParam("code") String code) {

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8"); // 내가 전송한 http body 데이터가
																						// key=value 형태라고 알려주는 것

		// body 데이터 담을 객체 (HttpBody 객체 생성)
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "FQfWYUQMCFhjJlwg6pLM");
		params.add("client_secret", "bKGKatJhbw");
		params.add("code", code);

		HttpEntity<MultiValueMap<String, String>> NaverTokenRequest = new HttpEntity<>(params, headers);

		RestTemplate rt = new RestTemplate();

		ResponseEntity<String> accessToken = rt.exchange("https://nid.naver.com/oauth2.0/token", HttpMethod.POST,
				NaverTokenRequest, String.class);

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

		HttpEntity<MultiValueMap<String, String>> NaverProfileRequest = new HttpEntity<>(headers2);

		ResponseEntity<String> requestProfile = rt2.exchange("https://openapi.naver.com/v1/nid/me", HttpMethod.POST,
				NaverProfileRequest, String.class);

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

		SopiMemInfo NaverUser = SopiMemInfo.builder().memId(memId) // 이메일
				.memNickname(memNickname) // 임시 닉네임
				.memPw("1234").snsConfirm("Naver").memGender(memGender).memGender("N").memState("Y") // Y : 횔동회원, N : 탈퇴
				.build();

		// 가입자 비가입자 체크해서 처리 originUser : 기존 가입자
		SopiMemInfo originUser = loginService.searchMem(NaverUser.getMemId());

		// 비가입자면 회원가입하고 로그인 처리
		if (originUser == null) {
			System.out.println("----------비가입자----------");
			String data = loginService.join(NaverUser);
			return new ResponseDto<String>(HttpStatus.OK.value(), data);
		}

		// 가입자면 바로 로그인 처리
		SopiMemInfo data = loginService.login(NaverUser);

		return new ResponseDto<String>(HttpStatus.OK.value(), data.getMemId());
	}
}