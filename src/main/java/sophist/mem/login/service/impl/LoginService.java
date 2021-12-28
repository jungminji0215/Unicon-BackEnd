package sophist.mem.login.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import sophist.mem.login.repository.LoginRepository;
import sophist.mem.model.SopiMemInfo;

@Slf4j
@Service
public class LoginService {
	@Autowired
	private LoginRepository loginRepository;

	// sha-256 암호화
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 회원 가입
	@Transactional // 회원가입 전체 서비스가 하나의 트랜잭션으로 묶임, 전체가 성공이 돼야 commit
	public String join(SopiMemInfo sopiMemInfo) {

		String rawPassword = sopiMemInfo.getMemPw(); // 암호화 되지 않은 비번
		String encPassword = passwordEncoder.encode(rawPassword); // 암호화

		sopiMemInfo.setMemPw(encPassword);
		sopiMemInfo.setMemState("Y");

		if (sopiMemInfo.getSnsConfirm() == "Kakao") {
			sopiMemInfo.setSnsConfirm("Kakao");
		}

		if (sopiMemInfo.getSnsConfirm() == "Normal") {
			sopiMemInfo.setSnsConfirm("Normal");
		}
		loginRepository.save(sopiMemInfo); // 회원 가입

		return "success"; // 회원 가입
	}

	// 일반 로드인
	@Transactional(readOnly = true) // select 할 때 트랜젝션 시작하고 서비스 종료시에 트랜잭션 종료하는데까지 정합성 유지할 수 있음
	public SopiMemInfo login(SopiMemInfo sopiMemInfo) {

		SopiMemInfo user = null;

		try {
			user = loginRepository.findByMemId(sopiMemInfo.getMemId());
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return user;
	}

	// 회원 조회
	public SopiMemInfo searchMem(String memId) {
		return loginRepository.findByMemId(memId);
	}

	// 회원
}
