package cn.com.main;

import java.util.List;

import cn.com.webxml.ArrayOfString;
import cn.com.webxml.WeatherWebService;
import cn.com.webxml.WeatherWebServiceSoap;

public class RunMain {

	public static void main(String[] args) {
		WeatherWebService ws = new WeatherWebService();

		WeatherWebServiceSoap soap = ws.getWeatherWebServiceSoap();

		ArrayOfString arr = soap.getWeatherbyCityName("南京");

		List<String> list = arr.getString();

		list.forEach(System.out::println);
	}
}
