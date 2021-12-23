package sophist.mem.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sophist.mem.login.repository.LoginRepository;
import sophist.mem.model.SopiMemInfo;

@Service
public class LoginService { 
	@Autowired 
	private LoginRepository loginRepository;
	
	// sha-256 암호화 사용
    @Autowired
    private PasswordEncoder passwordEncoder;
    
	// 회원 가입
	@Transactional // 회원가입 전체 서비스가 하나의 트랜잭션으로 묶임, 전체가 성공이 돼야 commit
	public int Join(SopiMemInfo sopiMemInfo) {
		// 암호화 되지 않은 비번 
		String rawPassword = sopiMemInfo.getMemPw(); 
		// 암호화
		String encPassword = passwordEncoder.encode(rawPassword);
		// 암호화 된 비번 넣기
		sopiMemInfo.setMemPw(encPassword);	
		
		// 필수 값 넣기
		sopiMemInfo.setMemState("Y");	
		sopiMemInfo.setSnsConfirm("Normal");	
		
		// 이미 존재하는 아이디면 -2 리턴
		if(SearchMem(sopiMemInfo.getMemId()) != null) {
			return -2;
		}
		
		try {
			// 회원 가입 성공 : 1 리턴
			loginRepository.save(sopiMemInfo);
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 회원 가입 실패 : -1 리턴
		return -1;
	}
	
	
	
    // 일반 로드인
	@Transactional(readOnly = true) // select 할 때 트랜젝션 시작하고 서비스 종료시에 트랜잭션 종료하는데까지 정합성 유지할 수 있음
	public int login(SopiMemInfo sopiMemInfo) {
			
		SopiMemInfo user = loginRepository.findByMemId(sopiMemInfo.getMemId());
		
		System.out.println(passwordEncoder.matches(sopiMemInfo.getMemPw(), user.getMemPw()));
		

		
		// 비번 틀리거나 아이디가 존재하지 않으면 -1 리턴
		 if(!passwordEncoder.matches(sopiMemInfo.getMemPw(), user.getMemPw())) {
				System.out.println("입력한것");
				System.out.println(sopiMemInfo.getMemId());
				System.out.println(sopiMemInfo.getMemPw());
				System.out.println("가져온 카카오회원 정보");
				System.out.println(user.getMemId());
				System.out.println(user.getMemPw());
			 return -1;
		 }
		 
		 return 1;
}
	
	
	

	
	
	// 중복 회원 가입 방지를 위한 회원 찾기
	@Transactional(readOnly = true)
	public SopiMemInfo SearchMem(String memId) {
		return loginRepository.findByMemId(memId);
	}
	
}
