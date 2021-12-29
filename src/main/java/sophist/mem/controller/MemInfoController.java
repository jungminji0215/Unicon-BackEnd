package sophist.mem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import sophist.common.ResponseDto;
import sophist.common.service.FileDetailService;
import sophist.mem.login.service.impl.LoginService;
import sophist.mem.model.SopiMemInfo;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class MemInfoController {

	@Autowired
	private FileDetailService fileDetailService;

	@Autowired
	private LoginService loginService;
	

	// sopi_mem_info update
	// 파일, 회원정보, 카테고리 업데이트
	@PutMapping("/sophist/mypage/{memId}")
	public ResponseDto<String> updateMypage(HttpServletRequest requset, HttpSession session, SopiMemInfo sopiMemInfo) throws Throwable {
		String memId = "";
		memId = (String) session.getAttribute("user");

		MultipartHttpServletRequest multiFile = (MultipartHttpServletRequest) requset;
		MultipartFile multipartFile = multiFile.getFile("file");

		// 파일 업로드
		// fileDetailService.fileUpload(multipartFile);
		
		// 회원 업데이트
		
		sopiMemInfo.setMemContents(requset.getParameter("memContents"));
		sopiMemInfo.setMemNickname(requset.getParameter("memNickname"));
		sopiMemInfo.setMemGender(requset.getParameter("memGender"));
		

		log.info("request={}", requset);
		log.info("변경할 닉네임={}", requset.getParameter("memNickname"));
		log.info("변경할 자기소개={}", requset.getParameter("memContents"));
		
		
		loginService.updateMem(sopiMemInfo);

		return null;

	}

}
