 
package io.dorbae.esp8266.ide.parts;

import javax.inject.Inject;
import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;
import javax.annotation.PreDestroy;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import io.dorbae.esp8266.ide.utils.DorbaeStringUtil;
import jssc.SerialPortException;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.ViewerSorter;

public class PropertiesPart {
	private static class Sorter extends ViewerSorter {
		public int compare(Viewer viewer, Object e1, Object e2) {
			Object item1 = e1;
			Object item2 = e2;
			return 0;
		}
	}
	
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			return element.toString();
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
					list.add( new String[] { "Status", "Closed"});
				
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
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger( PropertiesPart.class);
	private Table tableProperties;
	
	@Inject
	public PropertiesPart() {
		
	}
	
	@PostConstruct
	public void postConstruct( Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TableViewer tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		tableViewer.setSorter(new Sorter());
		tableProperties = tableViewer.getTable();
		tableProperties.setLinesVisible(true);
		tableProperties.setHeaderVisible(true);
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
	
}