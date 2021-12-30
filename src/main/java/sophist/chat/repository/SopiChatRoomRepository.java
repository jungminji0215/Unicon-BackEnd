package sophist.chat.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import sophist.chat.model.SopiChatRoomModel;

@EnableJpaRepositories
public interface SopiChatRoomRepository extends JpaRepository<SopiChatRoomModel,String>{
	
	@Query("SELECT new map(scrm.roomName,sgm.groupName ) FROM SopiChatRoomModel scrm "
			+ " inner join SopiChatRoomMappingModel scrmm ON scrm.roomMappingCd=scrmm.roomMappingCd"
			+ " inner join SopiGroupMaster sgm on sgm.groupCd=scrmm.groupCd"
			+ " inner join SopiMemInfo smi on smi.memId=scrmm.memId"
			+ " where scrmm.memId= :memId"
			+ " and scrm.roomPermissionState= 'Y'"
			)
	public List<SopiChatRoomModel> findAllWithChatRoomByMemId(String memId);
	
}
