package sophist.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sophist.board.model.SopiBoardNotice;

@Repository
public interface BoardRepository extends JpaRepository<SopiBoardNotice, String> {
	
	@Query("SELECT sbn.boardNoticeContents, sbn.updateDate, sbn.writer, sbn.fixedYn FROM SopiBoardNotice sbn"
			+ " inner join SopiBoardMaster sbm on sbn.boardCd=sbm.boardCd")
	public Page<SopiBoardNotice> selectBoardNotice(Pageable pageable);
}
