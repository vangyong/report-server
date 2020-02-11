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
import cn.segema.report.domain.Scheme;
import cn.segema.report.repository.SchemeRepository;
import cn.segema.report.vo.User;
import reactor.core.publisher.Mono;

@Service
public class SchemeHandler {

	@Resource
	private SchemeRepository schemeRepository;

	public Mono<ServerResponse> findListByScheme(ServerRequest request) {
		Scheme scheme = new Scheme();
		if(request.queryParam("addressId")!=null) {
			String addressId = String.valueOf(request.queryParam("addressId"));
			scheme.setAddressDetail(addressId);
		}
		List<Scheme> schemeList =  schemeRepository.findListByScheme(scheme);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(schemeList));
	}
	
	public Mono<ServerResponse> add(ServerRequest request) {
		Scheme scheme = new Scheme();
		scheme.setSchemeId(UUID.randomUUID().toString());
		if(request.queryParam("addressId")!=null) {
			String addressId = String.valueOf(request.queryParam("addressId"));
			scheme.setAddressId(addressId);
		}
		if(request.queryParam("addressDetail")!=null) {
			String addressDetail = String.valueOf(request.queryParam("addressDetail"));
			scheme.setAddressDetail(addressDetail);
		}
		scheme.setStatus(0);
		scheme.setCreateTime(new Date());
		scheme = schemeRepository.save(scheme);
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(scheme));
	}
	
	public Mono<ServerResponse> edit(ServerRequest request) {
		if(request.queryParam("schemeId")!=null) {
			Optional<Scheme> scheme = schemeRepository.findById(String.valueOf(request.queryParam("schemeId")));
			
			if(request.queryParam("addressId")!=null) {
				String addressId = String.valueOf(request.queryParam("addressId"));
				scheme.get().setAddressId(addressId);
			}
			if(request.queryParam("addressDetail")!=null) {
				String addressDetail = String.valueOf(request.queryParam("addressDetail"));
				scheme.get().setAddressDetail(addressDetail);
			}
			if(request.queryParam("status")!=null) {
				Integer status = Integer.valueOf(String.valueOf(request.queryParam("status")));
				scheme.get().setStatus(status);
			}
			schemeRepository.save(scheme.get());
			return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(scheme.get()));
		}
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(null));
	}

	public Mono<ServerResponse> findByPage(ServerRequest request) {
		User user = new User();
		user.setId(new BigInteger("2"));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(user));
	}

}
