package sophist.chat.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sophist.group.model.SopiGroupMaster;
import sophist.mem.model.SopiMemInfo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="sopi_chat_room_mapping")
public class SopiChatRoomMappingModel implements Serializable{
	
	@Id
	@Column(name="room_mapping_cd",length=10)
	private String roomMappingCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mem_id",insertable = false,updatable = false)
	private SopiMemInfo sopiMemInfo;
	
	@OneToOne
	@JoinColumn(name="group_cd",insertable = false,updatable = false)
	private SopiGroupMaster sopiGroupMaster;

	@OneToMany(mappedBy = "sopiChatRoomMappingModel",fetch = FetchType.LAZY)
	private List<SopiChatRoomModel> sopiChatRoomList = new ArrayList<SopiChatRoomModel>();
	
	@Column(name="group_cd")
	private String groupCd;
	
	@Column(name="mem_id")
	private String memId;


}
