package sophist.common.service;

import org.springframework.web.multipart.MultipartFile;


public interface FileDetailService {
	
	public int fileUpload(MultipartFile file) throws Throwable;

}
