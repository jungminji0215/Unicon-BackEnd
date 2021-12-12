package sophist.group.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sophist.common.model.SopiCategory;
import sophist.mem.model.SopiMemInfo;

// 사용자 그룹 맵핑
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class SopiGroupMemMapping implements Serializable{
	
	// 맵핑 코드
	@Id 
	private String mappingCd;
	
	// 사용자 아이디
	private String memId;
	
	// 모임 코드
	private String groupCd;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name="mem_id"),
		@JoinColumn(name="group_cd")
	})
	private SopiMemInfo sopiMemInfo;
	
	@OneToMany(mappedBy="sopiCategory")
	private List<SopiCategory> sopiCategoryList = new ArrayList<SopiCategory>();
	
	@OneToMany(mappedBy="sopiGroupReview")
	private List<SopiGroupReview> sopiGroupReviewList = new ArrayList<SopiGroupReview>();
	
	@OneToMany(mappedBy="sopiGroupPermission")
	private List<SopiGroupPermission> sopiGroupPermission = new ArrayList<SopiGroupPermission>();
	
	@OneToMany(mappedBy="sopiGroupDetail")
	private List<SopiGroupDetail> sopiGroupDetailList = new ArrayList<SopiGroupDetail>();

}
