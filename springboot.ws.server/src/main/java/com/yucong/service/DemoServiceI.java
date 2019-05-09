package com.yucong.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * serviceName属性不要添加，采用默认值
 *
 */
@WebService(
		targetNamespace = "http://service.yucong.com/", // 接口的包名倒写，最后的斜杠不能少
		endpointInterface = "com.yucong.service.DemoServiceI"// 服务接口全路径
)
public interface DemoServiceI {

	// 如果不指定@WebMethod注解默认是吧实现类中所有的public方法都发布成服务方法
	// 可以使用@WebMethod(exclude=true)把此方法排除
	@WebMethod
	public String sayHello(String user);

}
