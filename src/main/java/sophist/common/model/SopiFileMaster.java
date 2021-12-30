package sophist.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sophist.group.model.SopiGroupMaster;
import sophist.mem.model.SopiMemInfo;

// 파일 마스터
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
@Table(name="sopi_file_master")
public class SopiFileMaster implements Serializable  {
	
	// 파일코드
	@Id 
	@Column(name="file_cd")
	@GeneratedValue(generator = "file_id")
	@GenericGenerator(name="file_id", strategy = "sophist.common.channel.FileIdGenerator")
	private String fileCd;

	@OneToMany(mappedBy = "sopiFileMaster")
	private List<SopiFileDetail> fileDetailList = new ArrayList<SopiFileDetail>();
	
	@OneToOne
	@JoinColumn(name="file_cd")
	private SopiMemInfo sopiMemInfo;
	
	@OneToOne
	@JoinColumn(name="file_cd")
	private SopiGroupMaster sopiGroupMaster;
	
	// 모듈코드
	/*
	private SopiCodeMaster sopiCodeMaster;
	*/
}