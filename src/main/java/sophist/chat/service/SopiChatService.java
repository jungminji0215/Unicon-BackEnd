package sophist.chat.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import sophist.chat.model.SopiChatRoomModel;

public interface SopiChatService {
	
	public Page<SopiChatRoomModel> findAllWithChatRoomByMemId(Pageable pageable);
}
