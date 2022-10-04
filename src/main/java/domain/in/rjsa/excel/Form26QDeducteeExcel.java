package domain.in.rjsa.excel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;


public class Form26QDeducteeExcel {

	String output;
	SXSSFWorkbook wbs;
	CellStyle styleMatch1;
	CellStyle styleMatch2;
	CellStyle styleMatch3;
	CellStyle styleMatch4;
	CellStyle styleMatch5;
	CellStyle styleMatch6;
	public CellStyle stylematchblank;
	BorderStyle borderStyleMatched;

	public void createStylematch1() {
		CellStyle styleMatch = wbs.createCellStyle();
		styleMatch.setFillForegroundColor(IndexedColors.ORANGE.index);
		styleMatch.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		borderStyleMatched = BorderStyle.THIN;
		styleMatch.setBorderBottom(borderStyleMatched);
		styleMatch.setBorderRight(borderStyleMatched);
		styleMatch.setBorderLeft(borderStyleMatched);
		styleMatch.setVerticalAlignment(VerticalAlignment.CENTER);
		styleMatch.setAlignment(HorizontalAlignment.CENTER);
		styleMatch.setWrapText(true);
		Font fontMatch = wbs.createFont();
		fontMatch.setFontName(XSSFFont.DEFAULT_FONT_NAME);
		fontMatch.setFontHeightInPoints((short) 12);
		fontMatch.setColor(IndexedColors.BLACK.getIndex());
		styleMatch.setFont(fontMatch);
		this.styleMatch1 = styleMatch;
	}

	public void createStylematch2()

	{

		CellStyle styleMatch = wbs.createCellStyle();
		styleMatch.setFillForegroundColor(IndexedColors.YELLOW.index);
		styleMatch.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleMatch.setAlignment(HorizontalAlignment.CENTER);
		styleMatch.setWrapText(true);
		styleMatch.setVerticalAlignment(org.apache.poi.ss.usermodel.VerticalAlignment.CENTER);
		styleMatch.setBorderBottom(borderStyleMatched);
		borderStyleMatched = BorderStyle.THIN;

		styleMatch.setBorderLeft(borderStyleMatched);
		styleMatch.setBorderRight(borderStyleMatched);
		styleMatch.setBorderTop(borderStyleMatched);
		styleMatch.setBorderBottom(borderStyleMatched);

		Font fontMatch = wbs.createFont();
		fontMatch.setFontName(XSSFFont.DEFAULT_FONT_NAME);
		fontMatch.setFontHeightInPoints((short) 13);
		fontMatch.setColor(IndexedColors.BLACK.getIndex());
		styleMatch.setFont(fontMatch);

		this.styleMatch2 = styleMatch;
	}

	public void createStylematch3() {

		CellStyle styleMatch = wbs.createCellStyle();
		styleMatch.setFillForegroundColor(IndexedColors.GREEN.index);
		styleMatch.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		styleMatch.setAlignment(HorizontalAlignment.CENTER);
		styleMatch.setBorderBottom(borderStyleMatched);
		styleMatch.setBorderRight(borderStyleMatched);
		styleMatch.setBorderLeft(borderStyleMatched);
		styleMatch.setVerticalAlignment(VerticalAlignment.CENTER);
		styleMatch.setAlignment(HorizontalAlignment.CENTER);
		styleMatch.setWrapText(true);
		Font fontMatch = wbs.createFont();
		fontMatch.setFontName(XSSFFont.DEFAULT_FONT_NAME);
		fontMatch.setFontHeightInPoints((short) 12);
		fontMatch.setColor(IndexedColors.BLACK.getIndex());
		styleMatch.setFont(fontMatch);
		this.styleMatch3 = styleMatch;

	}

	public void createStylematch4() {

		CellStyle styleMatch = wbs.createCellStyle();
		styleMatch.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.index);
		styleMatch.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleMatch.setAlignment(HorizontalAlignment.JUSTIFY);
		styleMatch.setAlignment(HorizontalAlignment.CENTER);
		styleMatch.setBorderBottom(borderStyleMatched);
		styleMatch.setBorderLeft(borderStyleMatched);
		styleMatch.setBorderRight(borderStyleMatched);
		styleMatch.setVerticalAlignment(VerticalAlignment.CENTER);
		styleMatch.setWrapText(true);
		Font fontMatch = wbs.createFont();
		fontMatch.setFontName(XSSFFont.DEFAULT_FONT_NAME);
		fontMatch.setFontHeightInPoints((short) 12);
		fontMatch.setColor(IndexedColors.BLACK.getIndex());
		styleMatch.setFont(fontMatch);
		styleMatch.setFont(fontMatch);
		this.styleMatch4 = styleMatch;
	}

	public void createStylematch5() {
		CellStyle styleMatch = wbs.createCellStyle();
		styleMatch.setFillForegroundColor(IndexedColors.YELLOW.index);
		styleMatch.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleMatch.setAlignment(HorizontalAlignment.JUSTIFY);
		styleMatch.setVerticalAlignment(VerticalAlignment.CENTER);
		styleMatch.setAlignment(HorizontalAlignment.CENTER);
		styleMatch.setWrapText(true);
		styleMatch.setBorderRight(borderStyleMatched);
		styleMatch.setBorderBottom(borderStyleMatched);
		styleMatch.setBorderLeft(borderStyleMatched);
		styleMatch.setBorderTop(borderStyleMatched);
		this.styleMatch5 = styleMatch;
		Font fontMatch = wbs.createFont();
		fontMatch.setFontName(XSSFFont.DEFAULT_FONT_NAME);
		fontMatch.setFontHeightInPoints((short) 12);
		fontMatch.setColor(IndexedColors.BLACK.getIndex());
		styleMatch.setFont(fontMatch);
		styleMatch.setFont(fontMatch);

	}

	public void createstylematch6() {
		CellStyle styleMatch = wbs.createCellStyle();
		styleMatch.setFillForegroundColor(IndexedColors.AQUA.index);
		styleMatch.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleMatch.setAlignment(HorizontalAlignment.JUSTIFY);
		styleMatch.setBorderBottom(borderStyleMatched);
		styleMatch.setBorderLeft(borderStyleMatched);
		styleMatch.setBorderRight(borderStyleMatched);
		styleMatch.setBorderTop(borderStyleMatched);
		styleMatch.setVerticalAlignment(VerticalAlignment.CENTER);
		styleMatch.setAlignment(HorizontalAlignment.CENTER);
		styleMatch.setWrapText(true);

		Font fontMatch = wbs.createFont();
		fontMatch.setFontName(XSSFFont.DEFAULT_FONT_NAME);
		fontMatch.setFontHeightInPoints((short) 12);
		fontMatch.setColor(IndexedColors.BLACK.getIndex());
		styleMatch.setFont(fontMatch);

		this.styleMatch6 = styleMatch;

	}

	public void stylematchblank() {
		CellStyle styleMatch = wbs.createCellStyle();
		styleMatch.setFillForegroundColor(IndexedColors.SEA_GREEN.index);
		styleMatch.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		styleMatch.setAlignment(HorizontalAlignment.JUSTIFY);

		this.stylematchblank = styleMatch;

	}

	public SXSSFWorkbook initialise(String outputFile) {
		output = outputFile;
		wbs = new SXSSFWorkbook();

		createStylematch1();
		createStylematch2();
		createStylematch3();
		createStylematch4();
		createStylematch5();
		createstylematch6();
		stylematchblank();
		createWelcomesheet(wbs);
		studentDetailExcel(wbs);
		return wbs;
	}

	private SXSSFWorkbook createWelcomesheet(SXSSFWorkbook wbs) {
		try {

			Sheet sheet = wbs.createSheet("Welcome");
			byte[] bytes;
			BufferedImage image = (BufferedImage) new ReadImage().initializeResource("images/logoimg.png");
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			bytes = baos.toByteArray();
			int pictureIdx = wbs.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			CreationHelper helper = wbs.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(1);
			anchor.setRow1(0);
			anchor.setCol2(6);
			anchor.setRow2(2);
			drawing.createPicture(anchor, pictureIdx);
			sheet.createRow(1).setHeight((short) 1000);

			byte[] bytes1;
			BufferedImage image2 = (BufferedImage) new ReadImage().initializeResource("images/contactimg1.png");
			ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
			ImageIO.write(image2, "png", baos1);
			bytes1 = baos1.toByteArray();
			int pictureIdx1 = wbs.addPicture(bytes1, Workbook.PICTURE_TYPE_PNG);
			CreationHelper helper1 = wbs.getCreationHelper();
			sheet.createDrawingPatriarch();
			ClientAnchor anchor1 = helper1.createClientAnchor();
			anchor1.setCol1(1);
			anchor1.setRow1(8);
			anchor1.setCol2(6);
			anchor1.setRow2(21);
			drawing.createPicture(anchor1, pictureIdx1);
			sheet.setDisplayGridlines(false);

			CellStyle style = wbs.createCellStyle();
			style.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			BorderStyle border = BorderStyle.MEDIUM;
			style.setBorderBottom(border);
			style.setBorderLeft(border);
			style.setBorderRight(border);
			style.setBorderTop(border);
			Font fontMatch = wbs.createFont();
			fontMatch.setFontName(XSSFFont.DEFAULT_FONT_NAME);
			fontMatch.setFontHeightInPoints((short) 14);
			fontMatch.setColor(IndexedColors.WHITE.getIndex());
			fontMatch.setBold(true);
			style.setFont(fontMatch);
			style.setAlignment(HorizontalAlignment.CENTER);

			CellStyle style1 = wbs.createCellStyle();
			style1.setBorderBottom(border);
			style1.setBorderLeft(border);
			style1.setBorderRight(border);

			CellStyle style2 = wbs.createCellStyle();
			style2.setFillForegroundColor(IndexedColors.GREEN.index);
			style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style2.setBorderBottom(border);
			style2.setBorderLeft(border);
			style2.setBorderRight(border);
			style2.setBorderTop(border);
			style2.setFont(fontMatch);
			style2.setAlignment(HorizontalAlignment.CENTER);

			CellStyle style3 = wbs.createCellStyle();
			style3.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.index);
			style3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style3.setBorderBottom(border);
			style3.setBorderLeft(border);
			style3.setBorderRight(border);
			style3.setBorderTop(border);
			Font fontMatch1 = wbs.createFont();
			fontMatch1.setFontName(XSSFFont.DEFAULT_FONT_NAME);
			fontMatch1.setFontHeightInPoints((short) 11);
			fontMatch1.setColor(IndexedColors.BLACK.getIndex());
			fontMatch1.setBold(true);
			style3.setFont(fontMatch1);
			style3.setAlignment(HorizontalAlignment.JUSTIFY);

			Row row1 = sheet.createRow(3);
//			Row row2 = sheet.createRow(4);
//			Row row3 = sheet.createRow(5);
//			Row row4 = sheet.createRow(6);
//			Row row5 = sheet.createRow(7);
			for (int i = 1; i < 2; i++) {
				row1.createCell(i);
				row1.getCell(i).setCellStyle(style);
//				row2.createCell(i);
//				row2.getCell(i).setCellStyle(style1);
//				row3.createCell(i);
//				row3.getCell(i).setCellStyle(style2);

			}
			row1.getCell(1).setCellValue("FORM 26Q DEDUCTEE");
//			row2.setHeight((short) 120);
//			row3.getCell(1).setCellValue("");
//			row4.createCell(1).setCellStyle(style3);
//			row4.getCell(1).setCellValue("Financial Year");
//			row4.createCell(2).setCellStyle(style1);
//			row4.getCell(2).setCellValue("");
////		row4.createCell(3).setCellStyle(style1);
//			row4.createCell(4).setCellStyle(style3);
//			row4.getCell(4).setCellValue("Assessment Year");
//			row4.createCell(5).setCellStyle(style1);
//			row4.getCell(5).setCellValue("");

			sheet.setColumnWidth(1, 5000);
			sheet.setColumnWidth(2, 5000);
			sheet.setColumnWidth(3, 500);
			sheet.setColumnWidth(4, 6000);
			sheet.setColumnWidth(5, 6000);
			sheet.addMergedRegion(CellRangeAddress.valueOf("B" + 4 + ":F" + 4));
			sheet.addMergedRegion(CellRangeAddress.valueOf("B" + 5 + ":F" + 5));
			sheet.addMergedRegion(CellRangeAddress.valueOf("B" + 6 + ":F" + 6));
		} catch (Exception e) {

		}
		return wbs;

	}
	
	public Sheet initializeSheet(String name) {
//		createStylematch1();
//		createStylematch2();
//		createStylematch3();
//		createStylematch4();
//		createStylematch5();
//		createstylematch6();
//		stylematchblank();
		try {
			Sheet sheet = wbs.createSheet(name);
			Row row0 = sheet.createRow(0);

			for (int i = 0; i < 37; i++) {
				row0.createCell(i);
				row0.getCell(i).setCellStyle(styleMatch1);
			}
			sheet.setColumnWidth(0, 2000);
			row0.getCell(0).setCellValue("Sr no.");
			sheet.setColumnWidth(1, 4000);
			row0.getCell(1).setCellValue("Quarter");
			sheet.setColumnWidth(2, 5000);
			row0.getCell(2).setCellValue("Month");
			sheet.setColumnWidth(3, 4000);
			row0.getCell(3).setCellValue("Ro Code");
			sheet.setColumnWidth(4, 4000);
			row0.getCell(4).setCellValue("Branch Code");
			sheet.setColumnWidth(5, 4000);
			row0.getCell(5).setCellValue("Cust/Vend Id");
			sheet.setColumnWidth(6, 4000);
			row0.getCell(6).setCellValue("Unique Ref. No.");
			sheet.setColumnWidth(7, 4000);
			row0.getCell(7).setCellValue("Account Number");
			sheet.setColumnWidth(8, 4000);
			row0.getCell(8).setCellValue("Challan Heading");
			sheet.setColumnWidth(9, 4000);
			row0.getCell(9).setCellValue("Deductee Reference No.");
			sheet.setColumnWidth(10, 4000);
			row0.getCell(10).setCellValue("PAN");
			sheet.setColumnWidth(11, 4000);
			row0.getCell(11).setCellValue("Name");
			sheet.setColumnWidth(12, 4000);
			row0.getCell(12).setCellValue("Section Code");
			sheet.setColumnWidth(13, 4000);
			row0.getCell(13).setCellValue("Date Of Payment");
			sheet.setColumnWidth(14, 4000);
			row0.getCell(14).setCellValue("Date Of Deduction");
			sheet.setColumnWidth(15, 4000);
			row0.getCell(15).setCellValue("AmountPaid");
			sheet.setColumnWidth(16, 4000);
			row0.getCell(16).setCellValue("TDS");
			sheet.setColumnWidth(17, 4000);
			row0.getCell(17).setCellValue("Surcharge");
			sheet.setColumnWidth(18, 4000);
			row0.getCell(18).setCellValue("Education Cess");
			sheet.setColumnWidth(19, 4000);
			row0.getCell(19).setCellValue("Total Tax Deducted");
			sheet.setColumnWidth(20, 4000);
			row0.getCell(20).setCellValue("Total Tax Deposited");
			sheet.setColumnWidth(21, 4000);
			row0.getCell(21).setCellValue("Certificate Number");
			sheet.setColumnWidth(22, 4000);
			row0.getCell(22).setCellValue("remarksReason");
			sheet.setColumnWidth(23, 4000);
			row0.getCell(23).setCellValue("Deductee Code");
			sheet.setColumnWidth(24, 4000);
			row0.getCell(24).setCellValue("Rate of Which Tax Deducted");
			sheet.setColumnWidth(25, 4000);
			row0.getCell(25).setCellValue("Cash Withdrawl (194N)");
			sheet.setColumnWidth(26, 4000);
			row0.getCell(26).setCellValue("Cash Withdrawl 194N(20L to 1Cr)");
			sheet.setColumnWidth(27, 4000);
			row0.getCell(27).setCellValue("Cash Withdrawl 194N(>1Cr)");
			sheet.setColumnWidth(28, 4000);
			row0.getCell(28).setCellValue("Error Description");
			sheet.setColumnWidth(29, 4000);
			row0.getCell(29).setCellValue("Warning Description");
			sheet.setColumnWidth(30, 4000);
			row0.getCell(30).setCellValue("Short Deduction");
			sheet.setColumnWidth(31, 4000);
			row0.getCell(31).setCellValue("Inserted On Short Deduction");
			sheet.setColumnWidth(32, 4000);
			row0.getCell(32).setCellValue("Interest On Late Payment");
			sheet.setColumnWidth(33, 4000);
			row0.getCell(33).setCellValue("Interest On Late Deduction");
			sheet.setColumnWidth(34, 4000);
			row0.getCell(34).setCellValue("TAN");
			sheet.setColumnWidth(35, 4000);
			row0.getCell(35).setCellValue("Comments");
			sheet.setColumnWidth(36, 4000);
			row0.getCell(36).setCellValue("Status");

			return sheet;
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		return null;
	}

	private void studentDetailExcel(SXSSFWorkbook wbs2) {
		try {
			int part = 1;
			initializeSheet("form26QDeductee-1");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Form26QDeducteeExcel cde = new Form26QDeducteeExcel();
		cde.initialise("C:\\Users\\RJSA-15022021-03\\Desktop\\Form26QDeductee.xlsx");
		cde.close();
	}

	public void close() {
		try {
			FileOutputStream out1 = new FileOutputStream(output);
			wbs.setActiveSheet(0);
			wbs.write(out1);
			out1.close();
			wbs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SXSSFWorkbook getWorkbook() {

		return wbs;
	}

}
