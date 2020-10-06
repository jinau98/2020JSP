package kr.ac.hit.web.servlet.mvc.handler;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import kr.ac.hit.web.servlet.mvc.Controller;

public class UrlHandlerMapping {
	//URI와 Controller를 매핑한 정보 저장
	public static Map<String, Controller> handlerMap = new HashMap<String, Controller>();
	
	private UrlHandlerMapping() {}; 		//다른데서 url~ping 객체 생성하지 못하도록 private으로 접근
	
	public static void init(String configFilePath) throws Exception{
		Properties prop = new Properties();
		prop.load(new FileReader(configFilePath));
		
		Iterator keyItr = prop.keySet().iterator();
		
		while(keyItr.hasNext()) {
			String uri = (String) keyItr.next();
			String handlerClassName = prop.getProperty(uri);
			
			Class handlerClass = Class.forName(handlerClassName);
			Controller handler = (Controller) handlerClass.newInstance();
			handlerMap.put(uri,  handler);
		}
	}
	
	public static Controller getHandler(String uri) {
		return handlerMap.get(uri);
	}
}
