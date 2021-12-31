package sophist.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sophist.board.model.SopiBoardNotice;

public interface BoardService {

	public Page<SopiBoardNotice> selectBoardNotice(Pageable pageable) throws Throwable;
}
