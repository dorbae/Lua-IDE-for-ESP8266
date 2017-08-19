/**
 *
 *********************************************************
 *
 * StatsuBarToolControl.java
 *
 *********************************************************
 *
 * @version 1.0.00	2017. 8. 19.	dorbae(dorbae.io@gmail.com)	Initialize
 *
 * @since 2017. 8. 19.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 *
 */
package io.dorbae.esp8266.ide.toolcontrols;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class StatsuBarToolControl {
	
	@Inject
	private EventBroker eventBroker;
	public static final String UI_EVENT_TOPIC_STATUSBAR = "io.dorbae.esp8266.ide.toolcontrols.StatsuBarToolControl";
	private org.eclipse.swt.widgets.Label lblPortName = null;
	
	
	@Inject @Optional
	public void  getEvent( @UIEventTopic( UI_EVENT_TOPIC_STATUSBAR) String message) {
	    updateInterface(message); 
	}
	
	@Inject
	public StatsuBarToolControl() {
		
	}
	
	@PostConstruct
	public void createGui( Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd.heightHint= 300;
		gd.widthHint = 300;
		composite.setLayoutData( gd);
		
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblPortNameHeader = new Label(composite, SWT.NONE);
		lblPortNameHeader.setSize(149, 400);
		lblPortNameHeader.setAlignment(SWT.RIGHT);
		lblPortNameHeader.setText("Serial Port : ");
		
		this.lblPortName = new Label(composite, SWT.NONE);
		lblPortName.setSize(149, 400);
		this.lblPortName.setAlignment(SWT.RIGHT);
		this.lblPortName.setText("COM5");
		
	}
	
	public void updateInterface(String message)
    {
        try{
            Display.getDefault().asyncExec(new Runnable() {
              @Override
              public void run() {
                 try{
                	 lblPortName.setText(message);  
                    }
                    catch(Exception exc){
                        System.out.println(exc);
                    }               
              }
            });
        }
        catch(Exception exception){
            System.out.println(exception);
        }   
    }
}