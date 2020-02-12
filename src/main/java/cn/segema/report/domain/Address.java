package cn.segema.report.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("收货地址")
@Data
@Table(name = "report_address")
@Entity
public class Address {
	@ApiModelProperty(value = "收货地址id")
	@Id
	@Column(name = "address_id")
	private String addressId;
	
	@ApiModelProperty(value = "简称")
	@Column(name = "short_name")
	private String shortName;

	@ApiModelProperty(value = "收货地址明细")
	@Column(name = "detail_content")
	private String detailContent;

	@ApiModelProperty(value = "状态")
	@Column(name = "status")
	private Integer status;

	@ApiModelProperty(value = "创建时间")
	@Column(name = "create_time")
	private Date createTime;

}
