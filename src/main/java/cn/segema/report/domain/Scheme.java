package cn.segema.report.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("方案")
@Data
@Table(name = "report_scheme")
@Entity
public class Scheme {
	@ApiModelProperty(value="方案id")
	@Id
	@Column(name = "scheme_id")
	private String schemeId;

	@ApiModelProperty(value="方案明细")
	@Column(name = "scheme_detail")
	private String schemeDetail;

	@ApiModelProperty(value="收货地址id")
	@Column(name = "address_id")
	private String addressId;
	
	@ApiModelProperty(value="收货地址明细")
    @Column(name = "address_detail")
    private String addressDetail;

	@ApiModelProperty(value="状态",example = "0")
	@Column(name = "status")
	private Integer status;

	@ApiModelProperty(value="创建时间")
	@Column(name = "create_time")
	private Date createTime;

}
