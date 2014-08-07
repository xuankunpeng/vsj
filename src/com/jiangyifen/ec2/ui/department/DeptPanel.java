package com.jiangyifen.ec2.ui.department;

import java.util.List;

import com.jiangyifen.ec2.entity.Department;
import com.jiangyifen.ec2.service.eaoservice.DeptService;
import com.jiangyifen.ec2.utils.SpringContextHolder;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;

public class DeptPanel extends VerticalSplitPanel implements ClickListener, ValueChangeListener {

	private static final long serialVersionUID = 1L;

	private HorizontalLayout hlayout;
	private Button btnQueryDept = new Button("获取", this);
	private Button addButton = new Button("添加", this);
	private Button editButton = new Button("编辑", this);
	private Button delButton = new Button("删除", this);
	private Table deptTable;
	private Department dept;

	private BeanItemContainer<Department> deptContainer;

	private AddDeptWindow addDeptWindow;
	private EditDeptWindow editDeptWindow;
	private DeptService deptService = SpringContextHolder.getBean("deptService");

	private Object[] NATURAL_COL_ORDER = new Object[] { "id", "deptNO", "deptName" };
	private String[] COL_HEADERS = new String[] { "ID", "部门编号", "部门名称" };

	public DeptPanel() {
		
		this.setSplitPosition(60);
		this.setCaption("部门信息表");
		deptTable = new Table("部门信息表");
		deptTable.setSizeFull();
		deptTable.setSelectable(true);
		deptTable.setImmediate(true);
		deptTable.setPageLength(30);
		deptTable.setNullSelectionAllowed(false);
		deptTable.addListener(this);

		deptContainer = new BeanItemContainer<Department>(Department.class);
		deptTable.setContainerDataSource(deptContainer);
		deptTable.setVisibleColumns(NATURAL_COL_ORDER);
		deptTable.setColumnHeaders(COL_HEADERS);
		this.addComponent(deptTable);
		findAllDept();

		this.addComponent(createButten());// 添加增删改按钮

		// userContainer.removeAllItems();
		// User user = new User();
		// user.setId(1l);
		// user.setDept("888888888888888");
		// userContainer.addItem(user);
	}

	/**
	 * 创建增删改按钮
	 * 
	 * @return
	 */
	public HorizontalLayout createButten() {
		hlayout = new HorizontalLayout();

		hlayout.addComponent(btnQueryDept);
		hlayout.addComponent(addButton);
		hlayout.addComponent(editButton);
		hlayout.addComponent(delButton);

		hlayout.setSpacing(true);
		return hlayout;
	}

	/**
	 * 查询部门表中所有数据
	 */
	public void findAllDept() {

		List<Department> list = deptService.getAllDepts();
		deptContainer.removeAllItems();
		deptContainer.addAll(list);

	}

	@Override
	public void buttonClick(ClickEvent event) {
		Button btn = event.getButton();
		if (btn == addButton) {

			addDeptWindow = new AddDeptWindow(this);
			getWindow().addWindow(addDeptWindow);

		} else if (btn == editButton) {

			editDeptWindow = new EditDeptWindow(this, dept.getId());
			getWindow().addWindow(editDeptWindow);

		} else if (btn == delButton) {

			deptService.deleteDept(dept);
			findAllDept();

		} else if (btn == btnQueryDept) {
			System.out.println("0000");

		}
	}

	@Override
	public void valueChange(ValueChangeEvent event) {

		Property property = event.getProperty();
		if (property == deptTable) {

			dept = (Department) deptTable.getValue();

		}

	}

}
