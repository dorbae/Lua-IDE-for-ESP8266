
/**
 * 
 *********************************************************
 * LuaExplorerViewerLabelProvider.java
 *********************************************************
 *
 * @version 1.0.00	2017. 8. 17.	dorbae(dorbae.io@gmail.com)	Initialize
 * @since 2017. 8. 17.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 * 
 */

package io.dorbae.esp8266.ide.jface.provider.label;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;


public class LuaExplorerViewerLabelProvider extends LabelProvider {
	private ImageDescriptor directoryImage;
	private ImageDescriptor luaFileImage;
    private ResourceManager resourceManager;
    
    public LuaExplorerViewerLabelProvider() {
    	Bundle bundle = FrameworkUtil.getBundle( LuaExplorerViewerLabelProvider.class);
        URL directoryIconUrl = FileLocator.find( bundle, new Path("icons/file/folder.png"), null);
        this.directoryImage = ImageDescriptor.createFromURL( directoryIconUrl);
        URL luaFileIconUrl = FileLocator.find( bundle, new Path("icons/file/lua.png"), null);
        this.luaFileImage = ImageDescriptor.createFromURL( luaFileIconUrl);
    }
    
	public Image getImage(Object element) {
		if ( element == null)
			return null;
		else if ( ( ( java.io.File)element).isDirectory())
			return getResourceManager().createImage( this.directoryImage);
		else 
			return getResourceManager().createImage( this.luaFileImage);
		
	}

	public String getText( Object element) {
		if ( element == null) {
			return "null";
		
		} else if ( element instanceof java.io.File) {
			return ( ( java.io.File) element).getName();
		
		} else {
			return "Unknown";
	
		}
	}
	
	protected ResourceManager getResourceManager() {
        if(resourceManager == null) {
            resourceManager = new LocalResourceManager(JFaceResources.getResources());
        }
        return resourceManager;
    }
}
