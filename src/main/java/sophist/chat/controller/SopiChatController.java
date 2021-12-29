package sophist.chat.controller;


import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sophist.chat.model.SopiChatRoomModel;
import sophist.chat.service.SopiChatService;

@RestController
public class SopiChatController {
	
	@Resource
	private SopiChatService sopiChatService;
	
	@GetMapping(value = "/chatRoomList")
	public Page<SopiChatRoomModel> findAllWithChatRoomByMemId(@PageableDefault(size = 10,sort = "sgd.groupStarPoint",direction = Direction.DESC)Pageable pageable  )  throws Throwable{
		return sopiChatService.findAllWithChatRoomByMemId(pageable);
		
	}
}
