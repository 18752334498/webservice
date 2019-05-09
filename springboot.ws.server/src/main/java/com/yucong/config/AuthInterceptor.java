package com.yucong.config;

import java.util.List;

import javax.xml.soap.SOAPException;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 定义服务端用户名密码校验的拦截器 Created by sky on 2018/2/27.
 */
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	private static Logger logger = Logger.getLogger(AuthInterceptor.class);
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";

	public AuthInterceptor() {
		// 定义在哪个阶段进行拦截
		super(Phase.PRE_PROTOCOL);
	}

	// 权限认证拦截器处理SOAPHeader中的认证信息，客户端在发起请求时在SOAPHeader中添加认证信息，
	// 服务端在接收到请求后，校验认证信息，校验通过则继续执行，校验不通过则返回错误
	@Override
	public void handleMessage(SoapMessage soapMessage) throws Fault {
		System.out.println("=================开始验证================");
		List<Header> headers = null;
		String username = null;
		String password = null;
		try {
			headers = soapMessage.getHeaders();
		} catch (Exception e) {
			logger.error("", e);
		}

		if (headers == null || headers.size() == 0) {
			throw new Fault(new IllegalArgumentException("找不到Header，无法验证用户信息"));
		}

		// 获取用户名,密码
		for (Header header : headers) {
			SoapHeader soapHeader = (SoapHeader) header;
			Element e = (Element) soapHeader.getObject();
			NodeList usernameNode = e.getElementsByTagName("username");
			NodeList pwdNode = e.getElementsByTagName("password");
			username = usernameNode.item(0).getTextContent();
			password = pwdNode.item(0).getTextContent();
			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				throw new Fault(new IllegalArgumentException("用户信息为空"));
			}
		}
		// 校验用户名密码
		if (!(username.equals(USERNAME) && password.equals(PASSWORD))) {
			SOAPException soapExc = new SOAPException("认证失败");
			logger.debug("用户认证信息错误");
			throw new Fault(soapExc);
		}
		System.out.println("=================结束验证================");
	}

}
