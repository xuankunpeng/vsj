package com.jiangyifen.ec2.ui.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.jiangyifen.ec2.entity.User;
import com.jiangyifen.ec2.service.eaoservice.UserService;
import com.jiangyifen.ec2.utils.SpringContextHolder;
import com.vaadin.data.Property.ConversionException;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class EditUserWindow extends Window implements ClickListener {

	private Button save = new Button("保存", this);
	private Button cancel = new Button("取消", this);

	private UserPanel userPanel;
	private User user;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private TextField userNameField;
	private TextField ageField;
	private TextField phoneField;
	private TextField deptField;
	private PopupDateField dateField;

	private UserService userService = SpringContextHolder.getBean("userService");

	public EditUserWindow(UserPanel userPanel, Long userId) {

		this.userPanel = userPanel;

		user = userService.getUser(userId);//查询table中选中的user对象
		
		if (user != null) {

			this.setModal(true);
			this.center();
			this.setWidth("20%");
			this.setHeight("30%");
			this.setCaption("Edit User");

			VerticalLayout vl = new VerticalLayout();
			vl.setSpacing(true);

			HorizontalLayout h1 = new HorizontalLayout();
			Label userNameLabel = new Label("用户名：");
			userNameField = new TextField();
			userNameField.setValue(user.getUsername());
			h1.addComponent(userNameLabel);
			h1.addComponent(userNameField);
			userNameLabel.setWidth("80.5px");

			vl.addComponent(h1);

			HorizontalLayout h2 = new HorizontalLayout();
			Label ageLabel = new Label("年龄");
			ageField = new TextField();
			ageField.setValue(user.getAge());
			h2.addComponent(ageLabel);
			h2.addComponent(ageField);
			ageLabel.setWidth("80px");

			vl.addComponent(h2);

			HorizontalLayout h3 = new HorizontalLayout();
			Label phoneLabel = new Label("电话号码");
			phoneField = new TextField();
			phoneField.setValue(user.getPhoneNumber());
			h3.addComponent(phoneLabel);
			h3.addComponent(phoneField);
			phoneLabel.setWidth("81.3px");

			vl.addComponent(h3);

			HorizontalLayout h4 = new HorizontalLayout();
			Label deptLabel = new Label("部门");
			deptField = new TextField();
			deptField.setValue(user.getDept());
			h4.addComponent(deptLabel);
			h4.addComponent(deptField);
			deptLabel.setWidth("80px");

			vl.addComponent(h4);

			HorizontalLayout h5 = new HorizontalLayout();
			Label dateLabel = new Label("入职日期");
			dateField = new PopupDateField();
			dateField.setDateFormat("yyyy-MM-dd");
			try {
				dateField.setValue(sdf.parse(user.getDate()));
			} catch (ReadOnlyException e) {
				e.printStackTrace();
			} catch (ConversionException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			h5.addComponent(dateLabel);
			h5.addComponent(dateField);
			dateLabel.setWidth("81.3px");
			dateField.setWidth("135px");

			vl.addComponent(h5);

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

			User user1 = new User();
			user1.setId(user.getId());
			user1.setAge(Integer.valueOf(ageField.getValue().toString().trim()));
			user1.setDept((String) deptField.getValue());
			user1.setPhoneNumber((String) phoneField.getValue());
			user1.setUsername((String) userNameField.getValue());
			user1.setDate(sdf.format(dateField.getValue()));

			userService.updateUser(user1);// 修改user对象
			userPanel.findAllUser();
			getParent().removeWindow(this);
		}
	}
}
