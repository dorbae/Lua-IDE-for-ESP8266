/**
 *
 *********************************************************
 *
 * CommonConstant.java
 *
 *********************************************************
 *
 * @version 1.0.00	2017. 8. 18.	dorbae(dorbae.io@gmail.com)	Initialize
 *
 * @since 2017. 8. 18.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 (
 */
package io.dorbae.esp8266.ide;

public class CommonConstant {
	public static java.util.concurrent.atomic.AtomicBoolean isPortOpened = new java.util.concurrent.atomic.AtomicBoolean( false);
	public static jssc.SerialPort serialPort = null;
	public static int READ_SERIAL_TIMEOUT_SECONDS = 10;
	
	//
	// Event Topips
	//
	public static final String EVENT_TOPIC_SERIAL_PORT_CHANGED = "evtTopicPortChanged";

}
