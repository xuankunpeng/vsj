package com.vsj.ui;

import com.jiangyifen.ec2.ui.EntranceUi;
import com.vaadin.Application;
import com.vaadin.ui.Window;

public class VsjApplication  extends Application{

	private static final long serialVersionUID = -2177777201820050957L;

//	private Button btAddLog;
//	
//	private TextField tfLogId;
//	private Button btFindLog;
//	
//	
//	
//	private LogService logService;
	
	@Override
	public void init() {
		
//		initSpringContext();
//		
//		Window mainWindow = new Window("Dd Application");
//		
//		Label label = new Label("Hello Vaadin");
//		mainWindow.addComponent(label);
//		
//		
//		btAddLog = new Button("Add");
//		btAddLog.addListener(this);
//		mainWindow.addComponent(btAddLog);
//		
//		tfLogId = new TextField();
//		tfLogId.setImmediate(true);
//		mainWindow.addComponent(tfLogId);
//		
//		btFindLog  = new Button("Find");
//		btFindLog.addListener(this);
//		mainWindow.addComponent(btFindLog);
//		
//		setMainWindow(mainWindow);
		Window mainWindow = new Window("ht Application");
		mainWindow.setContent(new EntranceUi());
		setMainWindow(mainWindow);
	}

//	private void initSpringContext(){
//		
//		logService = SpringContextHolder.getBean("logService");
//		
//	}
	
//	@Override
//	public void buttonClick(ClickEvent event) {
//		Button source = event.getButton();
//		if(source == btAddLog) {
//			Log log = new Log();
//			log.setAction("add  "+(new Date()));
//			logService.saveLog(log);
//			tfLogId.setValue(log.getId());
//			this.getMainWindow().showNotification("保存成功！"+log.toString(), Notification.TYPE_WARNING_MESSAGE);
//		}else if(source == btFindLog) {
//			Long logId = Long.valueOf(tfLogId.getValue().toString());
//			Log log = logService.findLog(logId);
//			if(null != log){
//				this.getMainWindow().showNotification("获取的log信息！"+log.toString(), Notification.TYPE_WARNING_MESSAGE);
//			}
//		}
//		
//	}
}
