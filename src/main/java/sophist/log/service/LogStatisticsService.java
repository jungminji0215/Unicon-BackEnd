package sophist.log.service;

import java.util.List;

import sophist.log.model.SearchLogGroupByInterface;

public interface LogStatisticsService {
	
	public List<SearchLogGroupByInterface> selectSearchLogCount() throws Throwable;
}
