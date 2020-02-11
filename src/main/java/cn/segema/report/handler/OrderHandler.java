package cn.segema.report.handler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import cn.segema.report.domain.Order;
import cn.segema.report.repository.OrderRepository;
import cn.segema.report.vo.User;
import reactor.core.publisher.Mono;

@Service
public class OrderHandler {

	@Resource
	private OrderRepository orderRepository;

	public Mono<ServerResponse> findListByOrder(ServerRequest request) {
		Order order = new Order();
		if(request.queryParam("addressId")!=null) {
			String addressId = String.valueOf(request.queryParam("addressId"));
			order.setAddressDetail(addressId);
		}
		if(request.queryParam("nickName")!=null) {
			String nickName = String.valueOf(request.queryParam("nickName"));
			order.setNickName(nickName);
		}
		if(request.queryParam("alipayAccount")!=null) {
			String alipayAccount = String.valueOf(request.queryParam("alipayAccount"));
			order.setAlipayAccount(alipayAccount);
		}
		
		List<Order> orderList =  orderRepository.findListByOrder(order);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(orderList));
	}
	
	public Mono<ServerResponse> add(ServerRequest request) {
		Order order = new Order();
		order.setOrderId(UUID.randomUUID().toString());
		if(request.queryParam("nickName")!=null) {
			String nickName = String.valueOf(request.queryParam("nickName"));
			order.setNickName(nickName);
		}
		if(request.queryParam("addressId")!=null) {
			String addressId = String.valueOf(request.queryParam("addressId"));
			order.setAddressId(addressId);
		}
		
		if(request.queryParam("addressDetail")!=null) {
			String addressDetail = String.valueOf(request.queryParam("addressDetail"));
			order.setAddressDetail(addressDetail);
		}
		if(request.queryParam("schemeId")!=null) {
			String schemeId = String.valueOf(request.queryParam("schemeId"));
			order.setSchemeId(schemeId);
		}
		if(request.queryParam("schemeDetail")!=null) {
			String schemeDetail = String.valueOf(request.queryParam("schemeDetail"));
			order.setSchemeDetail(schemeDetail);
		}
		
		if(request.queryParam("payType")!=null) {
			Integer payType = Integer.valueOf(String.valueOf(request.queryParam("payType")));
			order.setPayType(payType);
		}
		
		if(request.queryParam("status")!=null) {
			Integer status = Integer.valueOf(String.valueOf(request.queryParam("status")));
			order.setStatus(status);
		}
		
		if(request.queryParam("expressOrder")!=null) {
			String expressOrder = String.valueOf(request.queryParam("expressOrder"));
			order.setExpressOrder(expressOrder);
		}
		
		if(request.queryParam("payMoney")!=null) {
			BigDecimal payMoney = BigDecimal.valueOf(Long.valueOf(String.valueOf(request.queryParam("payMoney"))));
			order.setPayMoney(payMoney);
		}
		if(request.queryParam("expressTotal")!=null) {
			Integer expressTotal = Integer.valueOf(String.valueOf(request.queryParam("expressTotal")));
			order.setStatus(expressTotal);
		}
		if(request.queryParam("alipayAccount")!=null) {
			String alipayAccount = String.valueOf(request.queryParam("alipayAccount"));
			order.setAlipayAccount(alipayAccount);
		}
		
		if(request.queryParam("realName")!=null) {
			String realName = String.valueOf(request.queryParam("realName"));
			order.setRealName(realName);
		}
		if(request.queryParam("remarks")!=null) {
			String remarks = String.valueOf(request.queryParam("remarks"));
			order.setRemarks(remarks);
		}
		
		order.setStatus(0);
		order.setCreateTime(new Date());
		order = orderRepository.save(order);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(order));
	}
	
	public Mono<ServerResponse> edit(ServerRequest request) {
		if(request.queryParam("orderId")!=null) {
			Optional<Order> orderOptional = orderRepository.findById(String.valueOf(request.queryParam("orderId")));
			Order order = orderOptional.get();
			
			if(request.queryParam("nickName")!=null) {
				String nickName = String.valueOf(request.queryParam("nickName"));
				order.setNickName(nickName);
			}
			if(request.queryParam("addressId")!=null) {
				String addressId = String.valueOf(request.queryParam("addressId"));
				order.setAddressId(addressId);
			}
			
			if(request.queryParam("addressDetail")!=null) {
				String addressDetail = String.valueOf(request.queryParam("addressDetail"));
				order.setAddressDetail(addressDetail);
			}
			if(request.queryParam("schemeId")!=null) {
				String schemeId = String.valueOf(request.queryParam("schemeId"));
				order.setSchemeId(schemeId);
			}
			if(request.queryParam("schemeDetail")!=null) {
				String schemeDetail = String.valueOf(request.queryParam("schemeDetail"));
				order.setSchemeDetail(schemeDetail);
			}
			
			if(request.queryParam("payType")!=null) {
				Integer payType = Integer.valueOf(String.valueOf(request.queryParam("payType")));
				order.setPayType(payType);
			}
			
			if(request.queryParam("status")!=null) {
				Integer status = Integer.valueOf(String.valueOf(request.queryParam("status")));
				order.setStatus(status);
			}
			
			if(request.queryParam("expressOrder")!=null) {
				String expressOrder = String.valueOf(request.queryParam("expressOrder"));
				order.setExpressOrder(expressOrder);
			}
			
			if(request.queryParam("payMoney")!=null) {
				BigDecimal payMoney = BigDecimal.valueOf(Long.valueOf(String.valueOf(request.queryParam("payMoney"))));
				order.setPayMoney(payMoney);
			}
			if(request.queryParam("expressTotal")!=null) {
				Integer expressTotal = Integer.valueOf(String.valueOf(request.queryParam("expressTotal")));
				order.setStatus(expressTotal);
			}
			if(request.queryParam("alipayAccount")!=null) {
				String alipayAccount = String.valueOf(request.queryParam("alipayAccount"));
				order.setAlipayAccount(alipayAccount);
			}
			
			if(request.queryParam("realName")!=null) {
				String realName = String.valueOf(request.queryParam("realName"));
				order.setRealName(realName);
			}
			if(request.queryParam("remarks")!=null) {
				String remarks = String.valueOf(request.queryParam("remarks"));
				order.setRemarks(remarks);
			}
			
			orderRepository.save(order);
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(order));
		}
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(null));
	}
	
	

	public Mono<ServerResponse> findByPage(ServerRequest request) {
		User user = new User();
		user.setId(new BigInteger("2"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(user));
	}

}
