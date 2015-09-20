package com.wl.im.manager;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;

/**
 * XMPPConnection杩炴帴绠＄悊鍣�
 * @author Jason
 * QQ: 2904048107	
 * @date 2015-8-3
 * @version 1.0
 */
public class ConnectionManager {

	private static AbstractXMPPConnection connection;
	
	//鏈嶅姟鍣ㄧ殑IP鍦板潃
	private static String host = "192.168.74.105";
	//鏈嶅姟Openfire鐨勭鍙ｅ彿
	private static int port = 5222;
	//鏈嶅姟鍣ㄥ悕绉帮紙鏈湴璁＄畻鏈虹殑鍚嶇О銆佺綉绔欏煙鍚嶏級
	private static String serviceName = "xb-20150808clck";
	
	/**
	 * 鑾峰彇杩炴帴瀵硅薄
	 * @return
	 */
	public static AbstractXMPPConnection getConnection(){
		if (connection == null) {
			openConnection();
		}
		return connection;
	}

	/**
	 * 鎵撳紑杩炴帴
	 */
	private static void openConnection() {
		//璁剧疆杩炴帴鐨勯厤缃�
		Builder builder = XMPPTCPConnectionConfiguration.builder();
		builder.setHost(host);
		builder.setPort(port);
		builder.setServiceName(serviceName);
		builder.setSecurityMode(SecurityMode.disabled);
		
		connection = new XMPPTCPConnection(builder.build()); 
		
		try {
			connection.connect();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 鍏抽棴杩炴帴
	 */
	public static void release(){
		if (connection != null) {
			connection.disconnect();
		}
	}
	
}
