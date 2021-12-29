package sophist.common.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import sophist.common.model.SopiFileDetail;
import sophist.common.model.SopiFileMaster;
import sophist.common.repository.FileDetailRepository;
import sophist.common.repository.FileMasterRepository;
import sophist.common.service.FileDetailService;

@Slf4j
@Service
public class FileDetailImpl implements FileDetailService {

	// sha-256 암호화 사용
	@Autowired
	private PasswordEncoder fileNameEncoder;

	@Autowired
	private FileDetailRepository fileDetailRepository;

	@Autowired
	private FileMasterRepository fileMasterRepository;

	private String fileDir = "C:\\sopi_uploaded_files\\";

	@Override
	public String fileUpload(MultipartFile file) throws Throwable {

		log.info("원 파일명={}", file.getOriginalFilename());
		log.info("사이즈={}", file.getSize());
		log.info("타입={}", file.getContentType());
		log.info("파일={}", file.getName());

		try {
			System.out.println("----------------------------------");
			// sopi_file_master의 file_cd에 빈 객체를 저장 -> PK 자동 증가
			SopiFileMaster sopiFileMaster = new SopiFileMaster();
			fileMasterRepository.save(sopiFileMaster);
			System.out.println("----------------------------------");
			String maxFileCd = fileMasterRepository.findMasterFileCd();
			log.info("maxFileCd={}", maxFileCd);

			String originFileName = file.getOriginalFilename();
			String filePath = fileDir + originFileName;
			String sysFileName = fileNameEncoder.encode(originFileName);
			long fileSize = file.getSize();

			log.info("파일저장 filePath={}", filePath);

			SopiFileDetail sopiFileDetail = SopiFileDetail.builder().filePath(filePath).originFileName(originFileName)
					.sysFileName(sysFileName).fileSize(fileSize).fileCd(maxFileCd).build();
			
			log.info("파일디테일={}", sopiFileDetail);

			fileDetailRepository.save(sopiFileDetail);

			// 파일을 이 경로에다가 저장
			file.transferTo(new File(filePath));
			
			// sopi_mem_info 업데이트
			

		} catch (Exception e) {
			// TODO: handle exception
			return "fail";
		}
		return "success";
	}

}
