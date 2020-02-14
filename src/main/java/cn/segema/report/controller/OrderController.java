package cn.segema.report.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.report.domain.Order;
import cn.segema.report.repository.OrderRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "报单")
@RestController
@RequestMapping(value = "/v2/Order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@ApiOperation(value = "根据id获取报单", notes = "根据id获取报单")
	@ApiImplicitParams({ @ApiImplicitParam(name = "orderId", value = "收货地址id", required = true, paramType = "path") })
	@GetMapping("/{orderId}")
	public ResponseEntity findById(@PathVariable String orderId) {
		Optional<Order> Order = this.orderRepository.findById(orderId);
		return new ResponseEntity(Order, HttpStatus.OK);
	}

	@ApiOperation(value = "获取报单列表", notes = "获取报单列表")
	@GetMapping("/list")
	public ResponseEntity findList(Order order) {
		List<Order> orderList = orderRepository.findListByOrder(order);
		return new ResponseEntity(orderList, HttpStatus.OK);
	}

	@ApiOperation(value = "新增报单", notes = "新增报单")
	@PostMapping
	public ResponseEntity add(@RequestBody Order order) {
		order.setOrderId(UUID.randomUUID().toString());
		orderRepository.save(order);
		return new ResponseEntity(order, HttpStatus.OK);
	}

	@ApiOperation(value = "编辑报单", notes = "编辑报单")
	@PutMapping
	public ResponseEntity edit(@RequestBody Order order) {
		Optional<Order> oldProduct = orderRepository.findById(order.getOrderId());
		if (oldProduct.isPresent()) {
			BeanUtils.copyProperties(order, oldProduct.get(), "createTime");
			orderRepository.save(oldProduct.get());
			return new ResponseEntity(oldProduct.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity("can't find chanel", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "删除报单", notes = "删除报单")
	@DeleteMapping("/{orderId}")
	public ResponseEntity delete(@PathVariable String orderId) {
		Optional<Order> Order = orderRepository.findById(orderId);
		if (Order.isPresent()) {
			orderRepository.save(Order.get());
		}
		return new ResponseEntity(Order, HttpStatus.OK);
	}

}
