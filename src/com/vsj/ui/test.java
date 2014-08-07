package com.vsj.ui;

import com.jiangyifen.ec2.entity.User;
import com.jiangyifen.ec2.utils.ExcelReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class test {
	/** Excel 文件要存放的位置，假定在D盘JTest目录下 */
	public static String outputFile = "D:/JTest/ gongye.xls";

	public static void main(String argv[]) {
		/*
		 * try { // 创建新的Excel 工作簿 HSSFWorkbook workbook = new HSSFWorkbook(); //
		 * 在Excel工作簿中建一工作表，其名为缺省值 // 如要新建一名为"效益指标"的工作表，其语句为： // HSSFSheet sheet
		 * = workbook.createSheet("效益指标"); HSSFSheet sheet =
		 * workbook.createSheet(); // 在索引0的位置创建行（最顶端的行） HSSFRow row =
		 * sheet.createRow((short) 0); // 在索引0的位置创建单元格（左上端） HSSFCell cell =
		 * row.createCell((short) 0); // 定义单元格为字符串类型
		 * cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 在单元格中输入一些内容
		 * cell.setCellValue("增加值"); // 新建一输出文件流 FileOutputStream fOut = new
		 * FileOutputStream(outputFile); // 把相应的Excel 工作簿存盘
		 * workbook.write(fOut); fOut.flush(); // 操作结束，关闭文件 fOut.close();
		 * System.out.println("文件生成...");
		 * 
		 * } catch (Exception e) { System.out.println("已运行 xlCreate() : " + e);
		 * }
		 */
		try {
			// 对读取Excel表格标题测试
			InputStream is = new FileInputStream("d:\\test2.xls");
			ExcelReader excelReader = new ExcelReader();
			String[] title = excelReader.readExcelTitle(is);
			System.out.println("获得Excel表格的标题:");
			for (String s : title) {
				System.out.print(s + " ");
			}

			// 对读取Excel表格内容测试
			InputStream is2 = new FileInputStream("d:\\test2.xls");
			Map<Integer, User> map = excelReader.readExcelContent(is2);
			System.out.println("获得Excel表格的内容:");
			for (int i = 1; i <= map.size(); i++) {
				System.out.println(map.get(i));
			}

		} catch (FileNotFoundException e) {
			System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		}
	}
}
