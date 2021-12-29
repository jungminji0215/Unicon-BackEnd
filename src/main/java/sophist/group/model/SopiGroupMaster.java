package sophist.group.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import sophist.chat.model.SopiChatRoomMappingModel;
import sophist.common.model.SopiCodeMaster;
import sophist.common.model.SopiFileMaster;

// 모임 마스터
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
@Table(name="sopi_group_master")
public class SopiGroupMaster implements Serializable{
	
	// 모임 코드
	@Id 
	@Column(nullable = false, length = 10,name="group_cd")
	private String groupCd;
	
	// 모임 이름
	@Column(nullable = false, length = 30)
	private String groupName;
	
	// 모임 상태
	@Column(nullable = false, length = 1)
	private String groupState;

	// 모임 설명 
	@Column(nullable = false, length = 300)
	private String groupDesc;
	
	@OneToOne
	@JoinColumn(name="file_cd",insertable = false,updatable = false)
	private SopiFileMaster sopiFileMaster;
	
	@Column(name="file_cd")
	private String fileCd;
	
	@OneToOne
	@JoinColumn(name="group_cd",insertable = false,updatable = false)
	private SopiChatRoomMappingModel SopiChatRoomMappingModel;
	
	@OneToMany(mappedBy = "sopiGroupMaster")
	private List<SopiGroupMemMapping> sopiGroupMemMappingList = new ArrayList<SopiGroupMemMapping>();
	
}

