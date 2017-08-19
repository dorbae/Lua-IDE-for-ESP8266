
/**
 * 
 *********************************************************
 * LuaScriptExplorer.java
 *********************************************************
 *
 * @version 1.0.01	2017. 8. 17.	dorbae(dorbae.io@gmail.com)	Separate LabelProvider, ContentProvider Class
 * @version 1.0.00	2017. 8. 15.	dorbae(dorbae.io@gmail.com)	Initialize
 * @since 2017. 8. 15.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 * 
 */

package io.dorbae.esp8266.ide.parts;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import io.dorbae.esp8266.ide.jface.provider.content.LuaExplorerTreeContentProvider;
import io.dorbae.esp8266.ide.jface.provider.label.LuaExplorerViewerLabelProvider;

public class LuaScriptExplorer {
	
	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger( LuaScriptExplorer.class);
	
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
