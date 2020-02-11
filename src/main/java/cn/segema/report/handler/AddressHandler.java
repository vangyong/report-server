package cn.segema.report.handler;

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

import cn.segema.report.domain.Address;
import cn.segema.report.repository.AddressRepository;
import cn.segema.report.vo.User;
import reactor.core.publisher.Mono;

@Service
public class AddressHandler {

	@Resource
	private AddressRepository addressRepository;

	public Mono<ServerResponse> findByAddress(ServerRequest request) {
		Address address = new Address();
		if(request.queryParam("status")!=null) {
			Integer status = Integer.valueOf(String.valueOf(request.queryParam("status")));
			address.setStatus(status);
		}
		List<Address> addressList = addressRepository.findListByAddress(address);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(addressList));
	}
	
	public Mono<ServerResponse> add(ServerRequest request) {
		Address address = new Address();
		
		request.queryParam("addressDetail").ifPresent(address::setAddressDetail);
		address.setAddressId(UUID.randomUUID().toString());
		address.setStatus(0);
		address.setCreateTime(new Date());
		address = addressRepository.save(address);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(address));
	}
	
	
	public Mono<ServerResponse> edit(ServerRequest request) {
		if(request.queryParam("addressId")!=null) {
			Optional<Address> address = addressRepository.findById(String.valueOf(request.queryParam("addressId")));
			request.queryParam("addressDetail").ifPresent(address.get()::setAddressDetail);
			Optional<String> status = request.queryParam("status");
			//status.ifPresent(address.get().setStatus(Integer.valueOf(status.get())));
			//status.ifPresent(address::setStatus);
			addressRepository.save(address.get());
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(address.get()));
		}
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(null));
	}

	public Mono<ServerResponse> findByPage(ServerRequest request) {
		User user = new User();
		user.setId(new BigInteger("2"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(user));
	}
	

}
