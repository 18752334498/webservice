package test;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.yucong.service.DemoServiceI;

public class Test2 {

	/**
	 * 使用代理类工厂,需要拿到对方的接口
	 */
	public static void main(String[] args) {
		// 创建工厂对象
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		// 设置访问路径
		factory.setAddress("http://localhost:8080/demo/api?wsdl");

		// 添加用户名密码拦截器
		factory.getOutInterceptors().add(new LoginInterceptor("root", "admin"));

		// 设置接口
		factory.setServiceClass(DemoServiceI.class);

		// 创建接口的代理类对象
		DemoServiceI demoServiceI = (DemoServiceI) factory.create();

		String string = demoServiceI.sayHello("Tom");
		System.out.println(string);
	}
}
