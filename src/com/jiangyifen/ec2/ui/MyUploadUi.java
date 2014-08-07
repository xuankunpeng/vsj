package com.jiangyifen.ec2.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.jiangyifen.ec2.entity.User;
import com.jiangyifen.ec2.service.eaoservice.UserService;
import com.jiangyifen.ec2.ui.user.UserPanel;
import com.jiangyifen.ec2.utils.ExcelReader;
import com.jiangyifen.ec2.utils.SpringContextHolder;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class MyUploadUi extends VerticalLayout implements Upload.SucceededListener,
		Upload.FailedListener, Upload.Receiver {

	private File file;
	private ExcelReader excelReader;
	private User user;
	private UserService userService = SpringContextHolder.getBean("userService");
	private UserPanel userPanel;
	private String filename;
	private Map<Integer, User> map;

	public MyUploadUi(UserPanel userPanel) {

		this.userPanel = userPanel;

		final Upload upload = new Upload(null, this);

		upload.setButtonCaption("导入资源");
		upload.addListener((Upload.SucceededListener) this);
		upload.addListener((Upload.FailedListener) this);
		upload.setImmediate(true);

		this.addComponent(upload);
	}

	/**
	 * 上传资源
	 */
	@Override
	public OutputStream receiveUpload(String filename, String MIMEType) {

		FileOutputStream fos = null;
		file = new File(System.getProperty("user.home").replace('\\', '/') + "/tmp/uploads/"
				+ filename);
		// System.out.println(System.getProperty("user.home"));
		// file = new File("/tmp/uploads/" + filename);
		try {

			fos = new FileOutputStream(file);
			this.filename = filename;
			System.out.println(file);

		} catch (final java.io.FileNotFoundException e) {

			e.printStackTrace();
			return null;
		}

		return fos;
	}

	/**
	 * 导入成功后
	 */
	@Override
	public void uploadSucceeded(Upload.SucceededEvent event) {

		importExcle();

		if (map.size() == 0) {

			getParent().getWindow().showNotification("资源为空啊！");
		} else {

			getParent().getWindow().showNotification("导入成功啦！！！");
		}
	}

	/**
	 * 导入失败后
	 */
	@Override
	public void uploadFailed(Upload.FailedEvent event) {

		getParent().getWindow().showNotification("很遗憾！导入失败！");
	}

	/**
	 * 导入数据的方法
	 */
	public void importExcle() {

		excelReader = new ExcelReader();
		InputStream is2;
		try {
			is2 = new FileInputStream(System.getProperty("user.home").replace('\\', '/')
					+ "/tmp/uploads/" + filename);
			// System.out.println(System.getProperty("user.home"));
			// is2 = new FileInputStream("/tmp/uploads/" + filename);

			map = excelReader.readExcelContent(is2);// 调用解析excel的方法

			// start
			long x = System.currentTimeMillis();

			for (int i = 1; i <= map.size(); i++) {

				user = map.get(i);
				userService.saveUser(user);// 持久化excel每一行数据

			}

			long y = System.currentTimeMillis();
			System.out.println("导入所用时间：" + (y - x));// 计算导入的时间
			// end
			userPanel.findAllUser();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
