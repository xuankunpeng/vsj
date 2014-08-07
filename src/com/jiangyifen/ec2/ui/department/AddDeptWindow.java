package com.jiangyifen.ec2.ui.department;

import com.jiangyifen.ec2.entity.Department;
import com.jiangyifen.ec2.service.eaoservice.DeptService;
import com.jiangyifen.ec2.utils.SpringContextHolder;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class AddDeptWindow extends Window implements ClickListener {

	private Button save = new Button("保存", this);
	private Button cancel = new Button("取消", this);

	private Department department;
	private TextField deptNoField;
	private TextField deptNameField;
	private DeptPanel deptPanel;

	private DeptService deptService = SpringContextHolder.getBean("deptService");

	public AddDeptWindow(DeptPanel deptPanel) {

		this.deptPanel = deptPanel;
		this.setModal(true);
		this.center();
		this.setWidth("20%");
		this.setHeight("30%");
		this.setCaption("Add Dept");

		 initSpringContext();

		VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);

		HorizontalLayout h2 = new HorizontalLayout();
		Label deptNoLabel = new Label("部门编号：");
		deptNoField = new TextField();
		h2.addComponent(deptNoLabel);
		h2.addComponent(deptNoField);
		deptNoLabel.setWidth("81.3px");

		vl.addComponent(h2);

		HorizontalLayout h3 = new HorizontalLayout();
		Label deptNameLabel = new Label("部门名称：");
		deptNameField = new TextField();
		h3.addComponent(deptNameLabel);
		h3.addComponent(deptNameField);
		deptNameLabel.setWidth("81.3px");

		vl.addComponent(h3);

		HorizontalLayout h4 = new HorizontalLayout();
		h4.addComponent(save);
		h4.addComponent(cancel);
		vl.addComponent(h4);
		h4.setSpacing(true);

		addComponent(vl);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Button bt = (Button) event.getSource();
		if (bt == cancel) {

			getParent().removeWindow(this);

		} else if (bt == save) {

			department = new Department();
			department.setDeptNO(Long.valueOf(deptNoField.getValue().toString()));
			department.setDeptName(deptNameField.getValue().toString());
			deptService.saveDept(department);

			getParent().getWindow().showNotification("添加成功！", Notification.TYPE_HUMANIZED_MESSAGE);
			deptPanel.findAllDept();
			getParent().removeWindow(this);
		}

	}

	private void initSpringContext() {

		deptService = SpringContextHolder.getBean("deptService");

	}
}
