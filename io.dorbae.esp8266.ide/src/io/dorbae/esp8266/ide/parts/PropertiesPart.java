 
/**
 * 
 *********************************************************
 * PropertiesPart.java
 *********************************************************
 *
 * @version 1.1.00	2017. 8. 21.	dorbae(dorbae.io@gmail.com)	Serial Port Open/Close Event
 * @version 1.0.00	2017. 8. 19.	dorbae(dorbae.io@gmail.com)	Initialize
 * @since 2017. 8. 19.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 * 
 */
package io.dorbae.esp8266.ide.parts;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import io.dorbae.esp8266.ide.CommonConstant;
import io.dorbae.esp8266.ide.utils.DorbaeStringUtil;
import jssc.SerialPortException;

public class PropertiesPart {
	
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
			
		}
		
		public String getColumnText(Object element, int columnIndex) {
			switch ( columnIndex) {
			case 0:
				return ( ( String[])element)[ 0];
			case 1:
				return ( ( String[])element)[ 1];
			default:
				return "";
			}
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if ( inputElement == null) {
				return new Object[0];
				
			} else if ( inputElement instanceof jssc.SerialPort) {
				jssc.SerialPort serialPort = ( jssc.SerialPort)inputElement;
				java.util.List< String[]> list = new java.util.LinkedList< String[]>();
				if ( serialPort.isOpened()) {
					list.add( new String[] { "Status", "Opened"});
				
				} else {
//					list.add( new String[] { "Status", "Closed"});
					return new String[][] {{ "Status", "Closed"}};
				
				}
				
				list.add( new String[]{ "Port Name", DorbaeStringUtil.null2Empty( serialPort.getPortName())});
				try {
					list.add( new String[]{ "isCTS", Boolean.toString( serialPort.isCTS())});
				
				} catch (SerialPortException e) {
					list.add( new String[]{ "isCTS", "Unknown"});
				}
				
				try {
					list.add( new String[]{ "isDSR", Boolean.toString( serialPort.isDSR())});
				
				} catch (SerialPortException e) {
					list.add( new String[]{ "isDSR", "Unknown"});
				}
				
				try {
					list.add( new String[]{ "isRING", Boolean.toString( serialPort.isRING())});
				
				} catch (SerialPortException e) {
					list.add( new String[]{ "isRING", "Unknown"});
				}
				
				try {
					list.add( new String[]{ "isRLSD", Boolean.toString( serialPort.isRLSD())});
					
				} catch (SerialPortException e) {
					list.add( new String[]{ "isRLSD", "Unknown"});
				}
				
				
				return list.toArray( new String[ list.size()][]);
				
			} else {
				return new Object[0];
				
			}
			
		}
		
		@Override
		public void dispose() {
		}
		
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			LOG.debug( "Call {}.inputChanged", this.getClass().getName());
//				(( Table)viewer.getControl()).clearAll();
//				viewer.setInput( newInput);
		}
	}
	
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger( PropertiesPart.class);
	
	public static final String ID = "io.dorbae.esp8266.ide.part.propertiespart";
	
	private TableViewer tableViewer;
	private Table tableProperties;
	
	@Inject
	public PropertiesPart() {
		
	}
	
	/**
	 * 
	 * Create UI widgets
	 * 
	 *********************************************************
	 * @param parent
	 *********************************************************
	 * @version 1.0.00	2017. 8. 21.	dorbae(dorbae.io@gmail.com)	Delete testing code
	 * @version 1.0.00	2017. 8. 19.	dorbae(dorbae.io@gmail.com)	Initialize
	 * @since 2017. 8. 21.
	 * @author dorbae(dorbae.io@gmail.com)	Initialize
	 */
	@PostConstruct
	public void postConstruct( Composite parent) {
		LOG.debug( "Call {}.postConstruct()", this.getClass().getName());
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		
		tableProperties = tableViewer.getTable();
		TableLayout tableLayout = new TableLayout();
		tableLayout.addColumnData( new ColumnWeightData( 0, 100, true));
		tableLayout.addColumnData( new ColumnWeightData( 0, 100, true));
//		tableLayout.addColumnData( new ColumnPixelData( 100, true, true));
//		tableLayout.addColumnData( new ColumnPixelData( 100, true, true));
		tableProperties.setLayoutData( tableLayout);
		// Set layout manually
		tableProperties.layout();
		
		TableViewerColumn tvc1 = new TableViewerColumn( tableViewer, SWT.NONE);
		TableColumn tableColumn1 = tvc1.getColumn();
		tableColumn1.setText( "Key");
		tableColumn1.setWidth( 300);
	
		TableViewerColumn tvc2 = new TableViewerColumn( tableViewer, SWT.NONE);
		TableColumn tableColumn2 = tvc2.getColumn();
		tableColumn2.setText( "Value");
		tableColumn2.setWidth( 300);
		
		tableProperties.setLinesVisible(true);
		tableProperties.setHeaderVisible(true);
		
		// Enhance Rendering Performance
		tableViewer.setUseHashlookup( true);
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new ContentProvider());

	}
	
	
	@PreDestroy
	public void preDestroy() {
		LOG.debug( "Call {}.preDestroy()", this.getClass().getName());

	}

	@Focus
	public void onFocus() {
		LOG.debug( "Call {}.onFocus()", this.getClass().getName());
		
	}

	@Persist
	public void save() {
		LOG.debug( "Call {}.save()", this.getClass().getName());
		
	}
	
	/**
	 * 
	 * Input Change Recognized Event
	 * 
	 *********************************************************
	 * @param input
	 *********************************************************
	 * @version 1.0.00	2017. 8. 21.	dorbae(dorbae.io@gmail.com)	Initialize
	 * @since 2017. 8. 21.
	 * @author dorbae(dorbae.io@gmail.com)	Initialize
	 */
	@Inject
	@Optional
	private void subscribeTableViewerUpdated( @UIEventTopic( CommonConstant.EVENT_TOPIC_SERIAL_PORT_CHANGED) Object input) {
		LOG.debug( "{}.subscribeTableViewerUpdated", this.getClass().getName());
//		LOG.debug( input.getClass().getName());
	    if ( this.tableViewer != null) {
	        // Update Input Serial Port Object
	    	this.tableViewer.setInput( input);
	    	// If port has never been opened, port is null
	    	if ( input != null)
	    		this.tableViewer.update( input, null);
	    	
	    }
	}
	
	
}