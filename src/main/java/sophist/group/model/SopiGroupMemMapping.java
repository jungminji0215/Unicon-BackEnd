package sophist.group.model;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sophist.chat.model.SopiChatRoomModel;
import sophist.common.model.SopiCategory;
import sophist.mem.model.SopiMemInfo;

// 사용자 그룹 맵핑
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SopiGroupMemMapping implements Serializable {

	// 맵핑 코드
	@Id
	@Column(name="mapping_cd")
	private String mappingCd;

	// 사용자 아이디
	@Column(name="mem_id")
	private String memId;

	// 모임 코드
	@Column(name="group_cd")
	private String groupCd;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mem_id",insertable = false,updatable = false)
	private SopiMemInfo sopiMemInfo;

	@OneToMany(mappedBy = "sopiGroupMemMapping")
	private List<SopiCategory> sopiCategoryList = new ArrayList<SopiCategory>();

	@OneToMany(mappedBy = "sopiGroupMemMapping")
	private List<SopiGroupReview> sopiGroupReviewList = new ArrayList<SopiGroupReview>();

	@OneToMany(mappedBy = "sopiGroupMemMapping")
	private List<SopiGroupPermission> sopiGroupPermission = new ArrayList<SopiGroupPermission>();

	@OneToMany(mappedBy = "sopiGroupMemMapping")
	private List<SopiGroupDetail> sopiGroupDetailList = new ArrayList<SopiGroupDetail>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_cd",insertable = false,updatable = false)
	private SopiGroupMaster sopiGroupMaster;

}
