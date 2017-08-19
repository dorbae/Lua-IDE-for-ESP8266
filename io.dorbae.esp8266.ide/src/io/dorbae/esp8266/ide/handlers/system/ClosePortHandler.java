/**
 *
 *********************************************************
 *
 * ClosePortHandler.java
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import io.dorbae.esp8266.ide.CommonConstant;
import io.dorbae.esp8266.ide.utils.DorbaeStringUtil;
import jssc.SerialPortException;

public class ClosePortHandler {

	private final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger( ClosePortHandler.class);
	
	/**
	 * 
	 * @version 1.0.00	2017. 8. 18.	dorbae(dorbae.io@gmail.com)	Initialize
	 * @since 2017. 8. 18.
	 * @author dorbae(dorbae.io@gmail.com)	Initialize
	 */
	@Execute
	public void execute() {
		LOG.info( "Close Port ");
		if ( io.dorbae.esp8266.ide.CommonConstant.serialPort != null) {
			// Get Port Name
			String portName = DorbaeStringUtil.null2Empty( io.dorbae.esp8266.ide.CommonConstant.serialPort.getPortName());
			
			try {
				if ( io.dorbae.esp8266.ide.CommonConstant.serialPort.closePort())
					LOG.info( "Close port {} successfully.", portName);
				else
					LOG.info( "Close port {} not successfully.", portName);
				
				//
				// Update port status
				//
				io.dorbae.esp8266.ide.CommonConstant.serialPort = null;
				io.dorbae.esp8266.ide.CommonConstant.isPortOpened.set( false);
				
			} catch ( SerialPortException ex) {
				MessageDialog.openWarning( Display.getDefault().getActiveShell(), "Failed to close port", "Failed to close port.\n" + ex.getMessage());
				LOG.error( "Failed to close port. {}", ExceptionUtils.getStackTrace( ex));
			
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
		return CommonConstant.isPortOpened.get();
	
	}
	
}
