package sophist.chat.service.impl;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sophist.chat.model.SopiChatRoomModel;
import sophist.chat.repository.SopiChatRoomRepository;
import sophist.chat.service.SopiChatService;

@Service
public class SopiChatServiceImpl implements SopiChatService{
	
	@Resource
	private SopiChatRoomRepository sopiChatRoomRepository;

	@Override
	public Page<SopiChatRoomModel> findAllWithChatRoomByMemId(Pageable pageable) {
		Page<SopiChatRoomModel> findAllWithChatRoomByMemId = sopiChatRoomRepository.findAllWithChatRoomByMemId(pageable);
		return findAllWithChatRoomByMemId;
	}
}

