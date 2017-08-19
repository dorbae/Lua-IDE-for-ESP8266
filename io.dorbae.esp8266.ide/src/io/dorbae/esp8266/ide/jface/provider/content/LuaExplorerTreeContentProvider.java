/**
 * 
 *********************************************************
 * LuaExplorerTreeContentProvider.java
 *********************************************************
 *
 * @version 1.0.00	2017. 8. 17.	dorbae(dorbae.io@gmail.com)	Initialize
 * @since 2017. 8. 17.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 * 
 */
package io.dorbae.esp8266.ide.jface.provider.content;

import java.io.File;
import java.io.FileFilter;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class LuaExplorerTreeContentProvider implements ITreeContentProvider {

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public Object[] getChildren(Object parentElement) {
		if ( parentElement == null) {
			return null;
		
		} else if ( parentElement instanceof java.io.File) {
			java.io.File file = ( java.io.File) parentElement;
			if ( file.isDirectory()) {
				return file.listFiles( new FileFilter() {
					
					@Override
					public boolean accept( File pathname) {
						if ( pathname.isDirectory())
							return true;
						
						String lowerFileName = pathname.getName().toLowerCase();
						if ( lowerFileName.endsWith( ".lua") || lowerFileName.endsWith( ".lc"))
							return true;
						
						return false;
						
					}
				});
					
			} else {
				return null;
			
			}
		
		} else {
			return null;
	
		}
		
	}

	public Object getParent(Object element) {
		if ( element == null) {
			return null;
		
		} else if ( element instanceof java.io.File) {
			return ((java.io.File) element).getParentFile();
		
		} else {
			return null;
			
		}
	}

	public boolean hasChildren(Object element) {
		if ( element != null && element instanceof java.io.File)
			return ((java.io.File) element).isDirectory();
			
		return false;
		
	}

}
