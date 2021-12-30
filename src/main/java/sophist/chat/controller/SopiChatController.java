package sophist.chat.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sophist.chat.model.SopiChatRoomModel;
import sophist.chat.service.SopiChatService;

@RestController
public class SopiChatController {
	
	@Resource
	private SopiChatService sopiChatService;
	
	@GetMapping(value = "/sophist/chatRoomList")
	public List<SopiChatRoomModel> findAllWithChatRoomByMemId(String memId)  throws Throwable{
		return sopiChatService.findAllWithChatRoomByMemId(memId);
		
	}
}
