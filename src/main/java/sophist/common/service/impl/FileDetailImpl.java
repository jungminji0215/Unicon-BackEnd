package sophist.common.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import sophist.common.model.SopiFileDetail;
import sophist.common.repository.FileDetailRepository;
import sophist.common.service.FileDetailService;

@Slf4j
@Service
public class FileDetailImpl implements FileDetailService{
	
	// sha-256 암호화 사용
    @Autowired
    private PasswordEncoder fileNameEncoder;
	
	@Autowired
	private FileDetailRepository fileDetailRepository;
	
	private String fileDir = "C:\\sopi_uploaded_files\\";
	
	@Override
	public String fileUpload(MultipartFile file) throws Throwable {
		
		
		if(!file.isEmpty()) {
			
			String originFileName = file.getOriginalFilename();
			String filePath = fileDir + originFileName;
			String sysFileName = fileNameEncoder.encode(originFileName);
			long fileSize = file.getSize();
			
			log.info("파일저장 filePath={}", filePath);
			
			SopiFileDetail sopiFileDetail = SopiFileDetail.builder()
							.filePath(filePath) 
							.originFileName(originFileName)
							.sysFileName(sysFileName)
							.fileSize(fileSize)
							.build();
			
			fileDetailRepository.save(sopiFileDetail);
			
			file.transferTo(new File(filePath)); //파일을 이 경로에다가 저장을 해줌
			
			return "success";
		
	    }

		
		return "aa";
	}

}
