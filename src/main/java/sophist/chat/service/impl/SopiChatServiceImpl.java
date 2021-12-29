package sophist.chat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import sophist.chat.model.SopiChatRoomModel;
import sophist.chat.repository.SopiChatRoomRepository;
import sophist.chat.service.SopiChatService;

@Service
public class SopiChatServiceImpl implements SopiChatService{
	
	@Resource
	private SopiChatRoomRepository sopiChatRoomRepository;

	@Override
	public List<SopiChatRoomModel> findAllWithChatRoomByMemId() {
		return sopiChatRoomRepository.findAllWithChatRoomByMemId();
	}

	

}
