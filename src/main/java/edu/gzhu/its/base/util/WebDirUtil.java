package edu.gzhu.its.base.util;

import java.net.URL;

/**
 * 读取配置文件
 * 
 * @author : 丁国柱
 * @date : 2014-6-16 上午9:52:59
 */
public class WebDirUtil {
	

	public String getWebDir() {
		URL currentUrl = this.getClass().getResource("WebDirUtil.class");
		String name = currentUrl.getFile();
		return name.substring(0, name.indexOf("/WEB-INF"));
	}
	
	public String getWebRootDir() {
		URL currentUrl = this.getClass().getResource("WebDirUtil.class");
		String name = currentUrl.getFile();
		return name.substring(0, name.indexOf("/WebRoot"));
	}
	
	/**
	 * 静态文件地址
	 * @author : 丁国柱
	 * @date : 2014-12-24 下午1:49:37
	 */
	public String getStaticWebRootDir() {
		return "/lcell/lcVirtualStorage/WebRoot/dingPan"; //linux环境
		//return "F:/lcVirtualStorage/WebRoot/lcontology"; //windows环境
	}
	
}
