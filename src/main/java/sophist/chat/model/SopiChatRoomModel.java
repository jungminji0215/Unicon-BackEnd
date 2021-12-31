package sophist.chat.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="sopi_chat_room")
public class SopiChatRoomModel implements Serializable{
	
	
	@Id
	@Column(name="room_cd",length=10)
	private String roomCd;
	
	@Column(name="room_permission_state",length=1)
	private String roomPermissionState;
	
	@Column(name="room_name",length=20)
	private String roomName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_mapping_cd",insertable = false,updatable = false)
	private SopiChatRoomMappingModel sopiChatRoomMappingModel;
	
	@Column(name="room_mapping_cd",length=10)
	private String roomMappingCd;
	

}
