/**
 * 
 *********************************************************
 *
 * RestartHandler.java
 * 
 *********************************************************
 *
 * @version 1.0.00	2017. 8. 17.	dorbae(dorbae.io@gmail.com)	Initialize
 * @since 2017. 8. 17.
 * 
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 * 
 */ 
package io.dorbae.esp8266.ide.handlers.system;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import io.dorbae.esp8266.ide.CommonConstant;

public class RestartHandler {
	
	private final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger( RestartHandler.class);
	
	@Execute
	public void execute() {
		LOG.info( "Restart ESP8266");
		
	}
	
	@CanExecute
	public boolean canExecute() {
		LOG.trace( "Call {}.canExecute()", this.getClass().getName());
		return CommonConstant.isPortOpened.get();
	
	}
		
}