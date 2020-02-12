package cn.segema.report.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import cn.segema.report.domain.Address;
import cn.segema.report.repository.AddressRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AddressHandler {

	@Resource
	private AddressRepository addressRepository;

	public Mono<ServerResponse> findById(ServerRequest request) {
		if (request.pathVariable("addressId") != null) {
			Optional<Address> address = addressRepository.findById(request.pathVariable("addressId"));
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
					.body(BodyInserters.fromObject(address.get()));
		}
		return ServerResponse.badRequest().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(BodyInserters.fromObject(null));
	}

	public Mono<ServerResponse> findList(ServerRequest request) {
		Address address = new Address();
		if (request.queryParam("status").isPresent()) {
			Integer status = Integer.valueOf(String.valueOf(request.queryParam("status").get()));
			address.setStatus(status);
		}
		List<Address> addressList = addressRepository.findListByAddress(address);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(BodyInserters.fromObject(addressList));
	}

	public Mono<ServerResponse> add(ServerRequest request) {
		Address address = new Address();
		request.queryParam("detailContent").ifPresent(address::setDetailContent);
		request.queryParam("shorName").ifPresent(address::setShortName);
		address.setAddressId(UUID.randomUUID().toString());
		address.setStatus(0);
		address.setCreateTime(new Date());
		address = addressRepository.save(address);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(address));
	}

	public Mono<ServerResponse> edit(ServerRequest request) {
		Mono<Address> addressMono = request.bodyToMono(Address.class);
		Mono<Address> mono = addressMono.doOnNext(address -> 
		 addressRepository.save(address));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(mono));
	}

}
