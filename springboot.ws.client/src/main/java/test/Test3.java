package test;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class Test3 {

	/**
	 * 动态调用方式
	 */
	public static void main(String[] args) {
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		Client client = factory.createClient("http://localhost:8080/demo/api?wsdl");
		client.getOutInterceptors().add(new LoginInterceptor("root", "admin"));

		QName opName = new QName("http://service.yucong.com/", "sayHello");
		try {
			Object[] objects = new Object[0];
			objects = client.invoke(opName, "Tom");
			System.out.println(objects[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
