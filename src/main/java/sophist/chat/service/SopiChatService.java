package sophist.chat.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import sophist.chat.model.SopiChatRoomModel;

public interface SopiChatService {
	
	public List<SopiChatRoomModel> findAllWithChatRoomByMemId(String memId);
}
