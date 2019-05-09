package com.yucong.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yucong.service.DemoServiceI;
import com.yucong.service.imp.DemoServiceImpl;

@Configuration
public class CxfConfig {

	// 不配置此servlet，那么wsdl的访问路径是 http://localhost:8080/services/api?wsdl
	@Bean
	public ServletRegistrationBean getServletRegistrationBean() {
		ServletRegistrationBean bean = new ServletRegistrationBean(new CXFServlet(), "/demo/*");
		bean.addUrlMappings("/demo/*"); // cxf的servlet访问路径值
		return bean;
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public DemoServiceI demoService() {
		return new DemoServiceImpl();
	}

	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), demoService());
		endpoint.publish("/api"); // http://localhost:8080/demo/api?wsdl
		endpoint.getInInterceptors().add(new AuthInterceptor());
		endpoint.getInInterceptors().add(new LoggingInInterceptor());
		return endpoint;
	}

}
