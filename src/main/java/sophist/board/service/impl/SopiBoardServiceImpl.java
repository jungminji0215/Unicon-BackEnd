package sophist.board.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sophist.board.model.SopiBoardNotice;
import sophist.board.repository.BoardRepository;
import sophist.board.service.BoardService;

@Service
public class SopiBoardServiceImpl implements BoardService {

	@Resource
	private BoardRepository boardRepository;
	
	@Override
	public Page<SopiBoardNotice> selectBoardNotice(Pageable pageable) {
		return boardRepository.selectBoardNotice(pageable);
	}
}
