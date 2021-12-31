package sophist.board.controller;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sophist.board.model.SopiBoardNotice;
import sophist.board.service.BoardService;

@RestController
public class SopiBoardController {
	
	@Resource
	private BoardService boardService;
	
	@GetMapping("/boardnotice")
	public Page<SopiBoardNotice> selectBoardNotice(@PageableDefault(size = 10, sort = "updateDate", direction = Sort.Direction.DESC) Pageable pageable) throws Throwable {
		return boardService.selectBoardNotice(pageable);
	}
	
}
