package cn.segema.report.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import cn.segema.report.handler.AddressHandler;
import cn.segema.report.handler.OrderHandler;
import cn.segema.report.handler.SchemeHandler;

@Configuration
public class RouterConfig {
	
	@Resource
	private AddressHandler addressHandler;
	
	@Resource
	private SchemeHandler schemeHandler;
	
	@Resource
	private OrderHandler orderHandler;
	
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route(GET("/v2/user/list"), addressHandler::findList);
				//.andRoute(POST("/v2/address").and(accept(MediaType.APPLICATION_JSON)), addressHandler::add)
				//.andRoute(PUT("/v2/address").and(accept(MediaType.APPLICATION_JSON)), addressHandler::edit)
				//.andRoute(GET("/v2/address/{addressId}").and(accept(MediaType.APPLICATION_JSON)), addressHandler::findById)
				//.andRoute(GET("/v2/scheme/list").and(accept(MediaType.APPLICATION_JSON)), schemeHandler::findList)
				//.andRoute(POST("/v2/scheme").and(accept(MediaType.APPLICATION_JSON)), schemeHandler::add)
				//.andRoute(PUT("/v2/scheme").and(accept(MediaType.APPLICATION_JSON)), schemeHandler::edit)
				//.andRoute(GET("/v2/scheme/{schemeId}").and(accept(MediaType.APPLICATION_JSON)), schemeHandler::findById)
				//.andRoute(GET("/v2/order/list").and(accept(MediaType.APPLICATION_JSON)), orderHandler::findList)
				//.andRoute(POST("/v2/order").and(accept(MediaType.APPLICATION_JSON)), orderHandler::add)
				//.andRoute(GET("/v2/order").and(accept(MediaType.APPLICATION_JSON)), orderHandler::edit)
				//.andRoute(GET("/v2/order/{orderId}").and(accept(MediaType.APPLICATION_JSON)), orderHandler::findById);
	}
}
