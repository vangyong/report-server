package cn.segema.report.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("报单明细")
@Data
@Table(name = "report_order")
@Entity
public class Order {
	@ApiModelProperty(value="报单id")
	@Id
	@Column(name = "order_id")
	private String orderId;

	@ApiModelProperty(value="微信昵称")
	@Column(name = "nick_name")
	private String nickName;
	
	@ApiModelProperty(value="收货地址id")
	@Column(name = "address_id")
	private String addressId;
	
	@ApiModelProperty(value="收货地址明细")
    @Column(name = "address_detail")
    private String addressDetail;
	
	@ApiModelProperty(value="方案id")
	@Column(name = "scheme_id")
	private String schemeId;

	@ApiModelProperty(value="方案明细")
	@Column(name = "scheme_detail")
	private String schemeDetail;
	
	@ApiModelProperty(value="支付方式",example = "0")
	@Column(name = "pay_type")
	private Integer payType;

	@ApiModelProperty(value="状态",example = "0")
	@Column(name = "status")
	private Integer status;

	@ApiModelProperty(value="快递单号")
	@Column(name = "express_order")
	private String expressOrder;
	
	@ApiModelProperty(value="支付金额")
	@Column(name = "pay_money")
	private BigDecimal payMoney;
	
	@ApiModelProperty(value="运单总数")
	@Column(name = "express_total")
	private int expressTotal;
	
	
	@ApiModelProperty(value="支付宝帐号")
	@Column(name = "alipay_account")
	private String alipayAccount;
	
	@ApiModelProperty(value="真实姓名")
	@Column(name = "real_name")
	private String realName;
	
	@ApiModelProperty(value="备注")
	@Column(name = "remarks")
	private String remarks;

	@ApiModelProperty(value="创建时间")
	@Column(name = "create_time")
	private Date createTime;

}
