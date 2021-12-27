package sophist.log.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sophist.log.model.SearchLogGroupByInterface;
import sophist.log.repository.SearchLogStatisticsRepository;
import sophist.log.service.LogStatisticsService;

@Service
public class LogStatisticsServiceImpl implements LogStatisticsService {

	@Resource
	private SearchLogStatisticsRepository searchLogStatisticsRepository; 
	
	@Override
	public List<SearchLogGroupByInterface> selectSearchLogCount() throws Throwable {
		// TODO Auto-generated method stub
		return searchLogStatisticsRepository.selectSearchLogCount();
	}

}
