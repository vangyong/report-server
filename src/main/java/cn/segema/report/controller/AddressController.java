package cn.segema.report.controller;

import java.util.Date;
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

import cn.segema.report.domain.Address;
import cn.segema.report.repository.AddressRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "收货地址")
@RestController
@RequestMapping(value = "/v2/address")
public class AddressController {

	@Autowired
	private AddressRepository addressRepository;

	@ApiOperation(value = "根据id获取收货地址", notes = "根据id获取收货地址")
	@ApiImplicitParams({ @ApiImplicitParam(name = "addressId", value = "收货地址id", required = true, paramType = "path") })
	@GetMapping("/{addressId}")
	public ResponseEntity findById(@PathVariable String addressId) {
		Optional<Address> Address = this.addressRepository.findById(addressId);
		return new ResponseEntity(Address, HttpStatus.OK);
	}

	@ApiOperation(value = "获取收货地址列表", notes = "获取收货地址列表")
	@GetMapping("/list")
	public ResponseEntity findList(Address Address) {
		List<Address> AddressList = addressRepository.findListByAddress(Address);
		return new ResponseEntity(AddressList, HttpStatus.OK);
	}

	@ApiOperation(value = "新增收货地址", notes = "新增收货地址")
	@PostMapping
	public ResponseEntity add(@RequestBody Address address) {
		address.setAddressId(UUID.randomUUID().toString());
		address.setCreateTime(new Date());
		addressRepository.save(address);
		return new ResponseEntity(address, HttpStatus.OK);
	}

	@ApiOperation(value = "编辑收货地址", notes = "编辑收货地址")
	@PutMapping
	public ResponseEntity edit(@RequestBody Address Address) {
		Optional<Address> oldProduct = addressRepository.findById(Address.getAddressId());
		if (oldProduct.isPresent()) {
			BeanUtils.copyProperties(Address, oldProduct.get(), "createTime");
			addressRepository.save(oldProduct.get());
			return new ResponseEntity(oldProduct.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity("can't find chanel", HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "删除收货地址", notes = "删除收货地址")
	@DeleteMapping("/{addressId}")
	public ResponseEntity delete(@PathVariable String addressId) {
		Optional<Address> address = addressRepository.findById(addressId);
		if (address.isPresent()) {
			addressRepository.save(address.get());
		}
		return new ResponseEntity(address, HttpStatus.OK);
	}

}
