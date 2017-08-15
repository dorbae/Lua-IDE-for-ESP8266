/**
 *********************************************************
 * SampleHandler.java
 *********************************************************
 * @version 1.0.00	2017. 8. 16.	dorbae(dorbae.io@gmail.com)	Initialize
 * @since 2017. 8. 16.
 * @author dorbae(dorbae.io@gmail.com)	Initialize
 */
package io.dorbae.esp8266.ide.handlers;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.e4.ui.workbench.IWorkbench;

/**
 * @author dorbae(dorbae.io@gamil.com)
 *
 */
/*
 * Eclipse Default IDs
 * 	Command 		ID
    ---------------------------------------------------
	Save			org.eclipse.ui.file.save
	Save All		org.eclipse.ui.file.saveAll
	Undo			org.eclipse.ui.edit.undo
	Redo			org.eclipse.ui.edit.redo
	Cut				org.eclipse.ui.edit.cut
	Copy			org.eclipse.ui.edit.copy
	Paste			org.eclipse.ui.edit.paste
	Delete			org.eclipse.ui.edit.delete
	Import			org.eclipse.ui.file.import
	Export			org.eclipse.ui.file.export
	Select All		org.eclipse.ui.edit.selectAll
	About			org.eclipse.ui.help.aboutAction
	Preferences		org.eclipse.ui.window.preferences
	Exit			org.eclipse.ui.file.exit
 */
public class SampleHandler {
	/*
	 * 핸들러에서 액션을 책임질 메소드에 표시한다. 
	 * 프레임워크는 사용자 인터페이스와 관련되는 매뉴 엔트리 등이 선택될 경우 이 메소드를 한번 실행시킨다.
	 */
	@org.eclipse.e4.core.di.annotations.Execute
    public void execute(IWorkbench workbench) {
        workbench.close();
        
        

    }
	
	/*
	 * Command Parameter 이용 1
	 */
	@org.eclipse.e4.core.di.annotations.Execute
    public void executeUsingParameter1(IWorkbench workbench, @javax.inject.Named("io.dorbae.esp8266.ide.handlers.paramters.user.name") String userName) {
        workbench.close();
        
    }
	
	/*
	 * Command Parameter 이용 2
	 */
	@org.eclipse.e4.core.di.annotations.Execute
    public void executeUsingParameter2(IWorkbench workbench, org.eclipse.core.commands.ParameterizedCommand command) {
		Object userName = command.getParameterMap().get( "io.dorbae.esp8266.ide.handlers.paramters.user.name");

        workbench.close();
        
    }

    // NOT REQUIRED IN THIS EXAMPLE
    // just to demonstrates the usage of
    // the annotation
	/*
	 * 이클립스 프레임워크가 핸들러가 현재 실행 가능한지를 체크하기 위해 사용할 메소드에 표시
	 * 핸들러 클래스의 이 메소드가 false 를 반환하면 이클립스는 해당하는 사용자 인터페이스를 비활성화 시킨다. \
	 * 예를들어 핸들러 클래스의 @CanExecute 가 표시된 메소드에서 true 를 반환하면 저장 버튼은 활성화 된다. 
	 * 이 메소드의 기본 반환값은 true 이기 때문에 핸들러 클래스는 항상 실행될 수 있다. 
	 * @CanExecute 메소드는 반드시 구현할 필요는 없다.
	 */
	/*
	 * 이클립스 컨텍스트 내에서 변경이 일어나면,  @CanExecute가 표시된 메소드가 프레임워크에 의해서 호출된다. 
	 * 예를들어, 우리가 새로운 파트하나를 선택하면, 그리고 @CanExecute가 표시된 메소드가 "false"를 반환한다면,  
	 * 프레임워크는 그 커맨드에 연결되는 어떤 메뉴나 아이템들도 비활성화 시킨다.
	 */
	/*
	 * 우리는 이벤트 브로커를 사용하여 이벤트를 전달함으로서  @CanExecute 메소드들을 다시 실행시키도록 할 수 있다.\
	 * // evaluate all @CanExecute methods
        eventBroker.send(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, UIEvents.ALL_ELEMENT_ID);

        // evaluate a context via a selector
        Selector s = (a selector that an MApplicationElement or an ID);
        eventBroker.send(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC, s);
	 */
    @org.eclipse.e4.core.di.annotations.CanExecute
    public boolean canExecute() {
        return true;
        
    }


}
