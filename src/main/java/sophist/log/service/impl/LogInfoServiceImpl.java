package sophist.log.service.impl;

import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sophist.log.model.SopiIpLog;
import sophist.log.model.SopiSearchLog;
import sophist.log.repository.IpLogInfoRepository;
import sophist.log.repository.SearchLogInfoRepository;
import sophist.log.service.LogInfoService;

@Service
public class LogInfoServiceImpl implements LogInfoService {
	
	@Resource
	private IpLogInfoRepository logInfoRepository;
	
	@Resource
	private SearchLogInfoRepository searchLogInfoRepository;

	@Override
	public Page<SopiIpLog> selectIpLogList(Pageable pageable) throws Throwable {
		return logInfoRepository.findAll(pageable);
	}

	@Override
	public Page<SopiSearchLog> selectSearchLogList(Pageable pageable) throws Throwable {
		return searchLogInfoRepository.findAll(pageable);
	}

	@Override
	public void insertSearchLog(SopiSearchLog sopiSearchLog) throws Throwable {
		 searchLogInfoRepository.save(sopiSearchLog);
	}
	
	@Override
	public SopiSearchLog selectSearchLogDetail(String searchCd) throws Throwable {
		return searchLogInfoRepository.findById(searchCd).orElseThrow(()->{
			return new IllegalArgumentException("ip 상세보기 실패 : 아이피를 찾을 수 없습니다.");
		});
	}
}
