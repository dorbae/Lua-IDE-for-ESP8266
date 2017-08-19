/**
 *
 *********************************************************
 *
 * OpenPortHandler.java
 *
 *********************************************************
 *
 * @version 1.0.00	2017. 8. 18.	dorbae(dorbae.io@gmail.com)	Initialize
 *
 * @since 2017. 8. 18.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 (
 */
package io.dorbae.esp8266.ide.handlers.system;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.internal.workbench.WorkbenchLogger;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import io.dorbae.esp8266.ide.CommonConstant;
import io.dorbae.esp8266.ide.dialogs.system.AvailableSerialPortDialog;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class OpenPortHandler {
	
	private final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger( OpenPortHandler.class);
	
	/**
	 * 
	 * @version 1.0.00	2017. 8. 18.	dorbae(dorbae.io@gmail.com)	Initialize
	 * @since 2017. 8. 18.
	 * @author dorbae(dorbae.io@gmail.com)	Initialize
	 */
	@Execute
	public void execute() {
		String selectedPortName = null;
		String[] availablePortNames = SerialPortList.getPortNames();
		
		if ( availablePortNames == null || availablePortNames.length < 1) {
			MessageDialog.openWarning( Display.getDefault().getActiveShell(), "Warning", "Does not exists any serial port.");
			return;
			
		} else {
			AvailableSerialPortDialog dialog = new AvailableSerialPortDialog( Display.getDefault().getActiveShell(), SWT.NONE);
			selectedPortName = dialog.open( availablePortNames);
			if ( selectedPortName == null)
				return;
			
			
			
		}
		
		try {
			io.dorbae.esp8266.ide.CommonConstant.serialPort = new SerialPort( selectedPortName);
			if ( io.dorbae.esp8266.ide.CommonConstant.serialPort.openPort()) {
				LOG.info( "Open Port[{}].", selectedPortName);
				io.dorbae.esp8266.ide.CommonConstant.isPortOpened.set( true);
				
				try {
					Thread.currentThread().sleep( 10000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				io.dorbae.esp8266.ide.CommonConstant
				.serialPort.writeString( "=node.restart()");
				
			} else {
				LOG.warn( "Failed to open serial port.");
				
			}
		
		} catch ( SerialPortException ex) {
			if ( SerialPortException.TYPE_PORT_BUSY.equals( ex.getExceptionType())) {
				LOG.warn( "Serial port[{}] is not opened. The port is busy. {}", selectedPortName, ex.getMessage());
				
			} else if ( SerialPortException.TYPE_PORT_NOT_FOUND .equals( ex.getExceptionType())) {
				LOG.warn( "Serial port[{}] is not found. {}", selectedPortName, ex.getMessage());
				
			} else {
				LOG.warn( "Failed to open port[{}]. {}", selectedPortName, ExceptionUtils.getStackTrace( ex));
				
			}
		}
		
	}
	
	/**
	 * 
	 * @return true : If serial port is not opened<br/>false : If serial port is opened
	 * @version 1.0.00	2017. 8. 18.	dorbae(dorbae.io@gmail.com)	Initialize
	 * @since 2017. 8. 18.
	 * @author dorbae(dorbae.io@gmail.com)	Initialize
	 */
	@CanExecute
	public boolean canExecute() {
		LOG.trace( "Call {}.canExecute()", this.getClass().getName());
		return !CommonConstant.isPortOpened.get();
	
	}
		
}
