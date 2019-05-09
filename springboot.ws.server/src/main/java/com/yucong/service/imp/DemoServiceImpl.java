package com.yucong.service.imp;

import java.util.Date;

import com.yucong.service.DemoServiceI;

/**
 * 实现类上可以不添加Webservice注解
 * 如果添加最好不要serviceName属性，只要targetNamespace、endpointInterface
 */
public class DemoServiceImpl implements DemoServiceI {

	@Override
	public String sayHello(String user) {
		return user + "，现在时间：" + "(" + new Date() + ")";
	}

}
