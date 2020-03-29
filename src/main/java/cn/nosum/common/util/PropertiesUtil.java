package cn.nosum.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class PropertiesUtil {
	private final static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	private static Map<String,String> propertiesMap= new ConcurrentHashMap<String,String>();
	private static String fileName="/application.properties";

	private PropertiesUtil(){}
	public PropertiesUtil(String fileName){
		setFileName(fileName);
	}

	static {
		loadFile();
	}

	/**
	 * 加载配置文件，并且将其转换为Map
	 */
	private static void loadFile(){
		InputStream inputStream = PropertiesUtil.class.getResourceAsStream(fileName);
		try {
			Properties properties = new Properties();
			properties.load(inputStream);
			Set<Map.Entry<Object, Object>> entrySet = properties.entrySet();
			for (Map.Entry<Object, Object> entry : entrySet) {
				String entryKey=entry.getKey().toString().trim();
				String value=entry.getValue().toString().trim();
				if (!entry.getKey().toString().startsWith("#")) {
					propertiesMap.put(entryKey, value);
				}
			}
		} catch (IOException e) {
			logger.debug("application.properties file read exception");
		}
	}

	public static void setFileName(String fileName) {
		PropertiesUtil.fileName = fileName;
		loadFile();
	}

	public static String getProperty(String name,String defaultValue){
		String value=getProperty(name);
		return StringUtils.isEmpty(value)?defaultValue:value;
	}

	public static String getProperty(String name){
		return propertiesMap.get(name);
	}
	public static List<String> getArraysProperty(String name){
		List<String> values=new ArrayList<>();
		for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
			if (entry.getKey().startsWith(name)) {
				values.add(entry.getValue());
			}
		}
		return values;
	}
}