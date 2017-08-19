/**
 *
 *********************************************************
 *
 * DorbaeStringUtil.java
 *
 *********************************************************
 *
 * @version 1.0.00	2017. 8. 18.	dorbae(dorbae.io@gmail.com)	Initialize
 *
 * @since 2017. 8. 18.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 (
 */
package io.dorbae.esp8266.ide.utils;

public class DorbaeStringUtil {
	
	/**
	 * 
	 * @param str : String data
	 * @return If str is null, return ""(empty space). Otherwise, return str as it is
	 * @version 1.0.00	2017. 8. 18.	dorbae(dorbae.io@gmail.com)	Initialize
	 * @since 2017. 8. 18.
	 * @author dorbae(dorbae.io@gmail.com)	Initialize
	 */
	public static String null2Empty( String str) {
		return (str == null ? "" : str);
		
	}
}
