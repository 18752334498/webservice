package test;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Administrator on 2018/2/27.
 */
public class LoginInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    private String username="root";
    private String password="admin";
    public LoginInterceptor(String username, String password) {
        //设置在发送请求前阶段进行拦截
        super(Phase.PREPARE_SEND);
        this.username=username;
        this.password=password;
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
		System.out.println("=================客户端拦截器================");
        List<Header> headers = soapMessage.getHeaders();
        Document doc = DOMUtils.createDocument();
		Element auth = doc.createElementNS("http://service.yucong.com/", "SecurityHeader");
        Element UserName = doc.createElement("username");
        Element UserPass = doc.createElement("password");

		UserName.setTextContent(this.username);
		UserPass.setTextContent(this.password);

        auth.appendChild(UserName);
        auth.appendChild(UserPass);

        headers.add(0, new Header(new QName("SecurityHeader"),auth));
		System.out.println("=================客户端拦截器================");
    }
}
