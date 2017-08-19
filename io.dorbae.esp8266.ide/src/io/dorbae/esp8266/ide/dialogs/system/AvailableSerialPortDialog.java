/**
 *
 *********************************************************
 *
 * AvailableSerialPortDialog.java
 *
 *********************************************************
 *
 * @version 1.0.00	2017. 8. 18.	dorbae(dorbae.io@gmail.com)	Initialize
 *
 * @since 2017. 8. 18.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 (
 */
package io.dorbae.esp8266.ide.dialogs.system;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class AvailableSerialPortDialog extends Dialog {
	
	protected String result;
	protected Shell shell;
	
	/**
	 * @param parent
	 * @param style
	 */
	public AvailableSerialPortDialog(Shell parent, int style) {
		super( parent, style);
		
	}


	/**
	 * Open the dialog.
	 * @return the result
	 */
	public String open( String[] portNames) {
		createContents( portNames);
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents( String[] portNames) {
		shell = new Shell( getParent(), SWT.DIALOG_TRIM);
		shell.setSize( 450, 156);
		shell.setText( getText());
		shell.setLayout(new GridLayout(1, false));
		
		Composite compositeTop = new Composite(shell, SWT.NONE);
		compositeTop.setLayout(new FillLayout(SWT.HORIZONTAL));
		compositeTop.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Combo comboProtNames = new Combo(compositeTop, SWT.NONE);
		
		Composite compositBottom = new Composite(shell, SWT.NONE);
		compositBottom.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compositBottom.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button btnOK = new Button(compositBottom, SWT.NONE);
		btnOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = comboProtNames.getText();
				shell.dispose();
				
			}
		});
		btnOK.setText("O K");
		
		Button btnCancel = new Button(compositBottom, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				result = null;
				shell.dispose();
				
				
			}
		});
		btnCancel.setText("Cancel");
		
		try {
			comboProtNames.setRedraw( false);
			comboProtNames.removeAll();
			
			for ( String portName : portNames) {
				comboProtNames.add( portName);
			
			}
			
			if ( portNames.length > 0)
				comboProtNames.select( 0);
			
			
		} finally {
			comboProtNames.setRedraw( true);
		
		}
		

	}	
	
}
