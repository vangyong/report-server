package cn.segema.report.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import cn.segema.report.handler.AddressHandler;
import cn.segema.report.handler.OrderHandler;
import cn.segema.report.handler.SchemeHandler;
import cn.segema.report.handler.UserHandler;

@Configuration
public class RouterConfig {
	@Resource
	private UserHandler userHandler;
	
	@Resource
	private AddressHandler addressHandler;
	
	@Resource
	private SchemeHandler schemeHandler;
	
	@Resource
	private OrderHandler orderHandler;
	
	
	@Bean
	public RouterFunction<?> routerFunction() {
		return RouterFunctions.route(RequestPredicates.GET("/user/id"), userHandler::getUserById)
				.andRoute(RequestPredicates.GET("/user/page"), userHandler::findByPage)
				.andRoute(RequestPredicates.GET("/address/status"), addressHandler::findByAddress)
				.andRoute(RequestPredicates.GET("/address/add"), addressHandler::add)
				.andRoute(RequestPredicates.GET("/address/edit"), addressHandler::edit)
				.andRoute(RequestPredicates.GET("/address/page"), addressHandler::findByPage)
				.andRoute(RequestPredicates.GET("/scheme/add"), schemeHandler::add)
				.andRoute(RequestPredicates.GET("/scheme/edit"), schemeHandler::edit)
				.andRoute(RequestPredicates.GET("/scheme/page"), userHandler::findByPage)
				.andRoute(RequestPredicates.GET("/order/add"), orderHandler::add)
				.andRoute(RequestPredicates.GET("/order/edit"), orderHandler::edit)
				.andRoute(RequestPredicates.GET("/order/page"), orderHandler::findByPage);
	}
}
