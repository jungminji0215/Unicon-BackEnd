package sophist.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import sophist.common.service.FileDetailService;
import sophist.mem.login.ResponseDto;

@Slf4j
@CrossOrigin (origins ="http://localhost:3001")
@RestController
public class CommonController {
	
	@Autowired
	private FileDetailService fileDetailService;
	
	// 파일 업로드
	@PostMapping("/sophist/file/upload")
	public  ResponseDto<Integer> fileUpload(@RequestParam("file") MultipartFile file) throws Throwable{ 
		log.info("원 파일명={}", file.getOriginalFilename()); // 원파일명 확인해보기
		log.info("사이즈={}", file.getSize());
		log.info("타입={}", file.getContentType());
		
		int result = fileDetailService.fileUpload(file);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), result) ;
	}

}
