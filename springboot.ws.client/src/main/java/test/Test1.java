package test;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;

import com.yucong.service.DemoServiceI;
import com.yucong.service.imp.DemoServiceImplService;

public class Test1 {

	/**
	 * 通过Apache的CXF工具下载服务端的代码
	 * 
	 * wsdl2java -encoding utf-8 http://localhost:8080/demo/api?wsdl
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		DemoServiceImplService demoServiceImpl = new DemoServiceImplService();
		DemoServiceI demoServiceI = demoServiceImpl.getDemoServiceImplPort();

		Client client = ClientProxy.getClient(demoServiceI);
		client.getInInterceptors().add(new LoggingInInterceptor());

		String string = demoServiceI.sayHello("Jack");
		System.out.println(string);
	}

}
