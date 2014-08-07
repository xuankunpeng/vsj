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
public class EditDeptWindow extends Window implements ClickListener {

	private Button save = new Button("保存", this);
	private Button cancel = new Button("取消", this);

	private DeptPanel deptPanel;
	private Department dept;

	private TextField deptNoField;
	private TextField deptNameField;

	private DeptService deptService = SpringContextHolder.getBean("deptService");

	public EditDeptWindow(DeptPanel deptPanel, Long deptId) {

		this.deptPanel = deptPanel;

		dept = deptService.getDept(deptId);

		if (dept != null) {

			this.setModal(true);
			this.center();
			this.setWidth("20%");
			this.setHeight("30%");
			this.setCaption("Edit dept");

			VerticalLayout vl = new VerticalLayout();
			vl.setSpacing(true);

			HorizontalLayout h1 = new HorizontalLayout();
			Label deptNoLabel = new Label("部门编号：");
			deptNoField = new TextField();
			deptNoField.setValue(dept.getDeptNO());
			h1.addComponent(deptNoLabel);
			h1.addComponent(deptNoField);
			deptNoLabel.setWidth("81.3px");

			vl.addComponent(h1);

			HorizontalLayout h2 = new HorizontalLayout();
			Label deptNameLabel = new Label("部门名称：");
			deptNameField = new TextField();
			deptNameField.setValue(dept.getDeptName());
			h2.addComponent(deptNameLabel);
			h2.addComponent(deptNameField);
			deptNameLabel.setWidth("81.3px");

			vl.addComponent(h2);

			HorizontalLayout h6 = new HorizontalLayout();
			h6.addComponent(save);
			h6.addComponent(cancel);
			vl.addComponent(h6);
			h6.setSpacing(true);
			addComponent(vl);
		} else {
			getParent().getWindow().showNotification("用户为空！");
		}
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Button bt = (Button) event.getSource();
		if (bt == cancel) {

			getParent().removeWindow(this);

		} else if (bt == save) {

			Department dept1 = new Department();
			dept1.setId(dept.getId());
			dept1.setDeptNO(Long.valueOf(deptNoField.getValue().toString()));
			dept1.setDeptName(deptNameField.getValue().toString());

			deptService.updateDept(dept1);
			deptPanel.findAllDept();
			getParent().removeWindow(this);
		}
	}
}
