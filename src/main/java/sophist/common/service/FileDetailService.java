package sophist.common.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileDetailService {
	
	public String fileUpload(MultipartFile file) throws Throwable;

}
