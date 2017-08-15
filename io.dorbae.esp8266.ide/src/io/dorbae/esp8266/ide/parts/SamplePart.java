package io.dorbae.esp8266.ide.parts;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class SamplePart {

	private Text txtInput;
	private TableViewer tableViewer;

	/*
	 * Injection
	 */
	@Inject
	private MDirtyable dirty;

	/*
	 * Called after class object is a instance and member variables and methods injections are called
	 * 클래스가 구성되고, 맴버변수와 메소드 인젝션이 수행된 후에 호출된다.
	 * 
	 * 파트의 사용자 인터페이스는 @PostConstruct 애노테이션을 붙인 메소드에서 구성하는 것을 추천
	 * @PostConstruct 메소드에서 사용자 인터페이스를 생성하기 위해서는, @Inject 메소드들이 사용자 인터페이스가 아직 생성되지 않았을 수도 있다
	 */
	/*
	 * Why is the @PostConstruct method not called?
	 * 다음에 나오는 설명은 Eclipse 4.6 (Eclipse Neon) 릴리즈 이전의 이클립스 버전에서만 유효하다.
	 * 이클립스 4.6 프레임워크는 자바버전의 @PostContruct 를 사용하기 때문에 여기서 설명하고 있는 문제는 더이상 발생할 수 없다. 
	 * Eclipse 4.6 이전에 Java 7 과 이클립스 플랫폼 둘다 @PostConstruct 애노테이션을 노출 시켰다.
	 * 우리의 이클립스 애플리케이션에서 우리는 프레임워크에게 이클립스 플랫폼에서 노출 시킨 애노테이션이 사용되어야 한다는 것을 
	 * 알려야 한다. org.eclipse.core.runtime 은 수정된 버전에서는 javax.annotation 엑스포트 한다. 
	 * 만약 어떤 이유들로 인해서 우리가 org.eclipse.core.runtime 에 대한 종속성을 피하기 원한다면, 
	 * 우리는 javax.annotation 패키지에 패키지 종속성을 정의 할 수 있으며 버전을 1.0.0 으로 설정하자. 
	 * 이 문제에 대한 상세 정보는 " Eclipse 4 RCP FAQ(http://wiki.eclipse.org/Eclipse4/RCP/FAQ)" 에서 확인하자.
	 */
	@javax.annotation.PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Enter text to mark part as dirty");
		txtInput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dirty.setDirty(true);
			}
		});
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		tableViewer = new TableViewer(parent);

		tableViewer.setContentProvider(ArrayContentProvider.getInstance());;
		tableViewer.setInput(createInitialDataModel());
		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	/*
	 * 파트가 포커싱 될 때마다 호출된다.
	 */
	@org.eclipse.e4.ui.di.Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	/*
	 * 이클립스 프레임워크가 파트에 저장요청을 하게 되면 호출된다. 
	 */
	@org.eclipse.e4.ui.di.Persist
	public void save() {
		dirty.setDirty(false);
	}
	
	/*
	 * 클래스가 소멸되기 전에 호출된다. 리소스들을 해제하는데 사용될 수 있다.
	 */
	@javax.annotation.PreDestroy
	public void destoy() {
	}
	
	private List<String> createInitialDataModel() {
		return Arrays.asList("Sample item 1", "Sample item 2", "Sample item 3", "Sample item 4", "Sample item 5");
	}
}