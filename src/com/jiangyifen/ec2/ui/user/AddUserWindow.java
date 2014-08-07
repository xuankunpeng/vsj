package com.jiangyifen.ec2.ui.user;

import java.text.SimpleDateFormat;

import com.jiangyifen.ec2.entity.User;
import com.jiangyifen.ec2.service.eaoservice.UserService;
import com.jiangyifen.ec2.utils.SpringContextHolder;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class AddUserWindow extends Window implements ClickListener {

	private Button save = new Button("保存", this);
	private Button cancel = new Button("取消", this);

	private User user;
	private TextField userNameField;
	private TextField ageField;
	private TextField phoneField;
	private PopupDateField dateField;
	private ComboBox deptField;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private UserPanel userPanel;

	private UserService userService = SpringContextHolder.getBean("userService");

	public AddUserWindow(UserPanel userPanel) {

		this.userPanel = userPanel;

		this.setModal(true);
		this.center();
		this.setWidth("20%");
		this.setHeight("30%");
		this.setCaption("Add User");

		initSpringContext();

		VerticalLayout vl = new VerticalLayout();
		vl.setSpacing(true);

		HorizontalLayout h2 = new HorizontalLayout();//添加用户名filed
		Label userNameLabel = new Label("用户名：");
		userNameField = new TextField();
		h2.addComponent(userNameLabel);
		h2.addComponent(userNameField);
		userNameLabel.setWidth("80.5px");

		vl.addComponent(h2);

		HorizontalLayout h3 = new HorizontalLayout();
		Label ageLabel = new Label("年龄");
		ageField = new TextField();
		h3.addComponent(ageLabel);
		h3.addComponent(ageField);
		ageLabel.setWidth("80px");

		vl.addComponent(h3);

		HorizontalLayout h4 = new HorizontalLayout();
		Label phoneLabel = new Label("电话号码");
		phoneField = new TextField();
		h4.addComponent(phoneLabel);
		h4.addComponent(phoneField);
		phoneLabel.setWidth("81.3px");

		vl.addComponent(h4);

		HorizontalLayout h5 = new HorizontalLayout();
		Label dateLabel = new Label("入职日期");
		dateField = new PopupDateField();
		dateField.setDateFormat("yyyy-MM-dd");
		h5.addComponent(dateLabel);
		h5.addComponent(dateField);
		dateLabel.setWidth("81.3px");
		dateField.setWidth("135px");

		vl.addComponent(h5);

		HorizontalLayout h6 = new HorizontalLayout();
		Label deptLabel = new Label("部门");
		deptField = new ComboBox();
		deptField.addItem("财务");
		deptField.addItem("人事");
		deptField.addItem("研发");
		deptField.setWidth("138px");
		deptField.setNullSelectionAllowed(false);
		h6.addComponent(deptLabel);
		h6.addComponent(deptField);
		deptLabel.setWidth("80px");

		vl.addComponent(h6);

		HorizontalLayout h7 = new HorizontalLayout();
		h7.addComponent(save);
		h7.addComponent(cancel);
		vl.addComponent(h7);
		h7.setSpacing(true);
		addComponent(vl);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		Button bt = (Button) event.getSource();
		if (bt == cancel) {
			getParent().removeWindow(this);

		} else if (bt == save) {

			user = new User();// 创建新对象
			user.setAge(Integer.valueOf(ageField.getValue().toString()));
			user.setDate(sdf.format(dateField.getValue()));
			user.setDept(deptField.getValue().toString());
			user.setPhoneNumber(phoneField.getValue().toString());
			user.setUsername(userNameField.getValue().toString());

			userService.saveUser(user);// 持久化user对象
			getParent().getWindow().showNotification("添加成功！", Notification.TYPE_HUMANIZED_MESSAGE);

			userPanel.findAllUser();// 查询出所有user对象并显示在table
			getParent().removeWindow(this);
		}

	}

	private void initSpringContext() {

		userService = SpringContextHolder.getBean("userService");

	}
}
