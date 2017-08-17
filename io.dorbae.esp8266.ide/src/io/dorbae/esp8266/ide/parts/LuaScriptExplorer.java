
package io.dorbae.esp8266.ide.parts;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class LuaScriptExplorer {
	
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger( LuaScriptExplorer.class);
	
	private static class LuaExplorerTreeContentProvider implements ITreeContentProvider {
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

	private static class LuaExplorerViewerLabelProvider extends LabelProvider {
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

	@Inject
	public LuaScriptExplorer() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite compositeMain = new Composite(parent, SWT.NONE);
		compositeMain.setLayout(new FillLayout(SWT.HORIZONTAL));

		TreeViewer treeExplorerViewer = new TreeViewer(compositeMain, SWT.BORDER);
		treeExplorerViewer.setExpandPreCheckFilters(false);
		Tree treeExplorer = treeExplorerViewer.getTree();
		treeExplorer.setHeaderVisible(true);
		treeExplorerViewer.setContentProvider(new LuaExplorerTreeContentProvider());
		treeExplorerViewer.setLabelProvider(new LuaExplorerViewerLabelProvider());
		treeExplorerViewer.setInput( new File( "C:\\"));

	}

	@PreDestroy
	public void preDestroy() {

	}

	@Focus
	public void onFocus() {

	}

	@Persist
	public void save() {

	}

}
