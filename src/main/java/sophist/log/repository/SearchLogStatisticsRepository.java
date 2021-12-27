package sophist.log.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sophist.log.model.SearchLogGroupByInterface;
import sophist.log.model.SopiSearchLog;

@Repository
public interface SearchLogStatisticsRepository extends JpaRepository<SopiSearchLog, String> {
 
	@Query(value = "select count(search_text) as searchTextCount , search_text as searchText "
			+ " from sopi_search_log "
			+ " GROUP BY search_text ORDER BY count(search_text) desc", nativeQuery = true)
	public List<SearchLogGroupByInterface> selectSearchLogCount();
	
	
}
