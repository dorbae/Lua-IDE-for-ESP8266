/**
 *
 *********************************************************
 *
 * ClosePortHandler.java
 *
 *********************************************************
 *
 * @version 1.1.00	2017. 8. 21.	dorbae(dorbae.io@gmail.com)	Send event to broker for executing \@CanExecute and changing properties part
 * @version 1.0.00	2017. 8. 18.	dorbae(dorbae.io@gmail.com)	Initialize
 *
 * @since 2017. 8. 18.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 *
 */
package io.dorbae.esp8266.ide.handlers.system;

import javax.inject.Inject;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import io.dorbae.esp8266.ide.CommonConstant;
import io.dorbae.esp8266.ide.utils.DorbaeStringUtil;
import jssc.SerialPortException;

public class ClosePortHandler {

	private final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger( ClosePortHandler.class);
	
	@Inject IEventBroker broker;
	
	/**
	 * 
	 * @version 1.0.01	2017. 8. 20.	dorbae(dorbae.io@gmail.com)	Add IBroker send service
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
//				io.dorbae.esp8266.ide.CommonConstant.serialPort = null;
				io.dorbae.esp8266.ide.CommonConstant.isPortOpened.set( false);
				
				// Send @CanExecute Event
				broker.send( UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIEvents.ALL_ELEMENT_ID);
				
				broker.send( CommonConstant.EVENT_TOPIC_SERIAL_PORT_CHANGED, io.dorbae.esp8266.ide.CommonConstant.serialPort);
				
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
