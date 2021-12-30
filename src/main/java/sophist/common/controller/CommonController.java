package sophist.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import sophist.common.ResponseDto;
import sophist.common.service.FileDetailService;

@Slf4j
@CrossOrigin (origins ="http://localhost:3000")
@RestController
public class CommonController {
	
	@Autowired
	private FileDetailService fileDetailService;
	
	// 파일 업로드
	@PostMapping("/sophist/file/upload")
	public  ResponseDto<String> fileUpload(@RequestParam("file") MultipartFile file) throws Throwable{ 
		String result = fileDetailService.fileUpload(file);
		return new ResponseDto<String>(HttpStatus.OK.value(), result) ;
	}

}
