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

	// sha-256 암호화
	@Autowired
	private PasswordEncoder passwordEncoder;

	// 회원 가입
	@Transactional // 회원가입 전체 서비스가 하나의 트랜잭션으로 묶임, 전체가 성공이 돼야 commit
	public String join(SopiMemInfo sopiMemInfo) {

		try {
			String rawPassword = sopiMemInfo.getMemPw(); // 암호화 되지 않은 비번
			String encPassword = passwordEncoder.encode(rawPassword); // 암호화

			sopiMemInfo.setMemPw(encPassword);
			sopiMemInfo.setMemState("Y");
			sopiMemInfo.setSnsConfirm("Normal");
			loginRepository.save(sopiMemInfo); // 회원 가입

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return "success"; // 회원 가입
	}

	// 일반 로드인
	@Transactional(readOnly = true) // select 할 때 트랜젝션 시작하고 서비스 종료시에 트랜잭션 종료하는데까지 정합성 유지할 수 있음
	public SopiMemInfo login(SopiMemInfo sopiMemInfo) {

		try {
			if (searchMem(sopiMemInfo.getMemId()) == null) {
				return null;
			}

			SopiMemInfo user = loginRepository.findByMemId(sopiMemInfo.getMemId());
			// 비번 틀리면 fail
			if (!passwordEncoder.matches(sopiMemInfo.getMemPw(), user.getMemPw())) {
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}

		return sopiMemInfo;
	}

	// 중복 회원 가입 방지를 위한 회원 조회
	public SopiMemInfo searchMem(String memId) {
		return loginRepository.findByMemId(memId);
	}

}
