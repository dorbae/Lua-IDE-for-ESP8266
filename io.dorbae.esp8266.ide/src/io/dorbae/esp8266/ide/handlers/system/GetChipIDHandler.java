
/**
 *
 *********************************************************
 *
 * GetChipIDHandler.java
 *
 *********************************************************
 *
 * @version 1.0.00	2017. 8. 28.	dorbae(dorbae.io@gmail.com)	Initialize
 *
 * @since 2017. 8. 28.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 *
 */
package io.dorbae.esp8266.ide.handlers.system;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import io.dorbae.esp8266.ide.CommonConstant;
import io.dorbae.esp8266.ide.utils.DorbaeStringUtil;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class GetChipIDHandler {
	
	private final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger( GetChipIDHandler.class);
	
	/**
	 * 
	 *********************************************************
	 *********************************************************
	 * @version 1.0.00	2017. 8. 28.	dorbae(dorbae.io@gmail.com)	Initialize
	 * @since 2017. 8. 28.
	 * @author dorbae(dorbae.io@gmail.com)	Initialize
	 */
	@Execute
	public void execute() {
		if ( CommonConstant.serialPort != null) {
			
			String chipId = null;
			
			try {
//				new Thread( new Runnable() {
//					
//					@Override
//					public void run() {
//						String chipId = null;
//						try {
//							chipId = CommonConstant.serialPort.readString();
//							System.out.println( "chipId=" + chipId);
//						} catch (SerialPortException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						
//					}
//				}).start();
				
//				CommonConstant.serialPort.setParams( SerialPort.BAUDRATE_115200, SerialPort.DATABITS_5, SerialPort.STOPBITS_1, SerialPort.PARITY_EVEN);
				CommonConstant.serialPort.setParams( 9600, 8, 1, 0);
//				CommonConstant.serialPort.setParams(baudRate, dataBits, stopBits, parity)
//				CommonConstant.serialPort.addEventListener( new SerialPortEventListener() {
//					
//					@Override
//					public void serialEvent( SerialPortEvent event) {
////						if ( event.isRXCHAR() && event.getEventValue() > 0) {
//						if ( event.getEventValue() > 0) {
//							String chipId = null;
//							try {
//								System.out.println( "eventValue= " + event.getEventValue());
////								chipId = CommonConstant.serialPort.readHexString();
//								chipId = CommonConstant.serialPort.readString( event.getEventValue());
//								if ( chipId == null)
//									System.out.println( "null");
//								else
//									System.out.printf( "chipId=%s\n", chipId);
//							} catch (SerialPortException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//							
//							try {
//								System.out.println( "eventValue= " + event.getEventValue());
////								chipId = CommonConstant.serialPort.readHexString();
//								chipId = CommonConstant.serialPort.readString( event.getEventValue());
//								if ( chipId == null)
//									System.out.println( "null");
//								else
//									System.out.printf( "chipId=%s\n", chipId);
//							} catch (SerialPortException e) {
//								// TODO Auto-generated catch block
//								e.printStackTrace();
//							}
//						}
//						
//					}
//				});
				
				System.out.println( "bu=" +  CommonConstant.serialPort.writeString( "=node.chipid()"));
				
				Thread.sleep( 2000);
				CommonConstant.serialPort.addEventListener( new SerialPortEventListener() {
					
					@Override
					public void serialEvent( SerialPortEvent event) {
//						if ( event.isRXCHAR() && event.getEventValue() > 0) {
						if ( event.getEventValue() > 0) {
							String chipId = null;
							try {
								System.out.println( "eventValue= " + event.getEventValue());
//								chipId = CommonConstant.serialPort.readHexString();
								chipId = CommonConstant.serialPort.readString( event.getEventValue());
								if ( chipId == null)
									System.out.println( "null");
								else
									System.out.printf( "chipId=%s\n", chipId);
							} catch (SerialPortException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							try {
								System.out.println( "eventValue= " + event.getEventValue());
//								chipId = CommonConstant.serialPort.readHexString();
								chipId = CommonConstant.serialPort.readString( event.getEventValue());
								if ( chipId == null)
									System.out.println( "null");
								else
									System.out.printf( "chipId=%s\n", chipId);
							} catch (SerialPortException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
					}
				});
//				chipId = CommonConstant.serialPort.readString();
//				chipId = DorbaeStringUtil.null2Empty( chipId);
//				MessageDialog.openInformation( Display.getDefault().getActiveShell(), "Chip ID", chipId);
				Thread.sleep( 10000L);
				CommonConstant.serialPort.removeEventListener();
			
			} catch ( Exception e) {
				LOG.error( "Failed to read chip id. {}", ExceptionUtils.getStackTrace( e));
				return;
				
			}
			
		}
	}
	
	
	/**
	 * 
	 * 
	 *********************************************************
	 * @return
	 *********************************************************
	 * @version 1.0.00	2017. 8. 28.	dorbae(dorbae.io@gmail.com)	Initialize
	 * @since 2017. 8. 28.
	 * @author dorbae(dorbae.io@gmail.com)	Initialize
	 */
	@CanExecute
	public boolean canExecute() {
		LOG.trace( "Call {}.canExecute()", this.getClass().getName());
		return CommonConstant.isPortOpened.get();
	}
		
}