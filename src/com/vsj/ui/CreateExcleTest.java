package com.vsj.ui;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

public class CreateExcleTest {
	/**
	 * @功能：手工构建一个简单格式的Excel
	 */
	final static String[] userNames = { "Smith", "Gordon", "Simpson", "Brown", "Clavel", "Simons",
			"Verne", "Scott", "Allison", "Gates", "Rowling", "Barks", "Ross", "Schneider", "Tate" };
	
	final static String[] dept = { "人事部", "研发部", "销售部", "策划部" };

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("学生表一");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("用户名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("年龄");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("部门");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("电话");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("日期");
		cell.setCellStyle(style);

		Random r = new Random(0);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(1997, 0, 1);
		long start = cal.getTimeInMillis();
		cal.set(2014, 0, 1);
		long end = cal.getTimeInMillis();

		for (int i = 0; i < 10000; i++) {
			row = sheet.createRow(i + 1);
			// 第四步，创建单元格，并设置值

			row.createCell((short) 0).setCellValue(userNames[r.nextInt(userNames.length)]);
			row.createCell((short) 1).setCellValue(r.nextInt(100));
			row.createCell((short) 2).setCellValue(dept[r.nextInt(dept.length)]);
			row.createCell((short) 3).setCellValue(
					"+358 02 555 " + r.nextInt(10) + r.nextInt(10) + r.nextInt(10) + r.nextInt(10));

			Date d = new Date(start + (long) (r.nextDouble() * (end - start)));
			String str = format.format(d);
			row.createCell((short) 4).setCellValue(str);
		}
		// 第六步，将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("E:/Users.xls");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
