package Base;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;


public class TestcaseDown {
    public static void tcDown(){

        XSSFWorkbook xssfWb = null;
        XSSFSheet xssfSheet = null;
        XSSFRow xssfRow = null;
        XSSFCell xssfCell = null;

        try {
            int rowNo = 0; // 행의 갯수 밑에 갯수 늘어날때마다 ++할것임

            xssfWb = new XSSFWorkbook();
            xssfSheet = xssfWb.createSheet("Neo Studio(Web) Test Case");

            XSSFFont font = xssfWb.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL); // 폰트 스타일
            font.setFontHeightInPoints((short)14); // 폰트 크기
            font.setBold(true); // 볼드체

            //테이블 셀 스타일
            CellStyle cellStyle = xssfWb.createCellStyle();
            xssfSheet.setColumnWidth(0, (xssfSheet.getColumnWidth(0))+(short)2048); // 0번째 컬럼 넓이
            xssfSheet.setColumnWidth(0,5600);
            xssfSheet.setColumnWidth(1,5600);
            xssfSheet.setColumnWidth(2,5600);

            cellStyle.setFont(font); // cellStyle에 font를 적용
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            xssfSheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 2)); //첫행, 마지막행, 첫열, 마지막열 병합

            // 타이틀 생성
            xssfRow = xssfSheet.createRow(rowNo++); // 행 객체 추가
            xssfCell = xssfRow.createCell((short) 0); // 추가한 행에 셀 객체 추가
            xssfCell.setCellStyle(cellStyle); // 셀에 스타일 지정
            xssfCell.setCellValue("Neo Studio(Web) Test Case"); // 데이터 입력

            xssfSheet.createRow(rowNo++);

            CellStyle tableCellStyle = xssfWb.createCellStyle();

            xssfRow = xssfSheet.createRow(rowNo++);
            xssfCell = xssfRow.createCell((short) 0);
            xssfCell.setCellStyle(tableCellStyle);
            xssfCell.setCellValue("TC No.");
            xssfCell = xssfRow.createCell((short) 1);
            xssfCell.setCellStyle(tableCellStyle);
            xssfCell.setCellValue("테스트 항목");
            xssfCell = xssfRow.createCell((short) 2);
            xssfCell.setCellStyle(tableCellStyle);
            xssfCell.setCellValue("테스트 결과");

            for(int i = 0; i < 20; i++){
                xssfRow = xssfSheet.createRow(rowNo++); // 행 객체 추가
                xssfCell = xssfRow.createCell((short) 0); // 추가한 행에 셀 객체 추가
                xssfCell.setCellStyle(tableCellStyle);
                xssfCell.setCellValue(i+1);
            }

            String localFile = "/Users/sykim/" + "NeoStudio_TestCase" + ".xlsx";

            File file = new File(localFile);
            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            xssfWb.write(fos);

            if (fos != null) fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
