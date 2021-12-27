package sophist.log.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sophist.log.model.SearchLogGroupByInterface;
import sophist.log.service.LogStatisticsService;

@RestController
public class SearchLogStatisticsController {
	
	@Resource
	private LogStatisticsService logStatisticsService;
	
	@GetMapping(value = "/sophist/searchLogStatisitcs")
	public List<SearchLogGroupByInterface> selectSearchLogCount() throws Throwable {
		return logStatisticsService.selectSearchLogCount();
	}
}
