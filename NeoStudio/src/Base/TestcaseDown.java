package Base.NeoStudio.src.Base;

import java.io.File;
import java.io.FileOutputStream;
import static Base.NeoStudio.src.Base.CaseList.*;
import static Base.NeoStudio.src.Base.RunTime.*;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

public class TestcaseDown {
    public static void tcDown() {

        XSSFWorkbook xssfWb = null;
        XSSFSheet xssfSheet = null;
        XSSFRow xssfRow = null;
        XSSFCell xssfCell = null;

        try {
            int rowNo = 0; // 행의 갯수 밑에 갯수 늘어날때마다 ++할것임

            xssfWb = new XSSFWorkbook();
            xssfSheet = xssfWb.createSheet("Neo Studio(Web) Test Case");
            //엑셀 시트 이름

            XSSFFont font = xssfWb.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short) 18);
            font.setBold(true); // 볼드체
            //제목 폰트 스타일

            //테이블 셀 스타일
            CellStyle cellStyle = xssfWb.createCellStyle();
            xssfSheet.setColumnWidth(0, (xssfSheet.getColumnWidth(0)) + (short) 2048); // 0번째 컬럼 넓이
            xssfSheet.setColumnWidth(0, 2800);
            xssfSheet.setColumnWidth(1, 8000);
            xssfSheet.setColumnWidth(2, 2800);
            xssfSheet.setColumnWidth(3, 5000);
            xssfSheet.setColumnWidth(4, 8000);


            //제목 셀, 각 행의 넓이 지정해줌

            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            //제목이 들어갈 셀의 스타일을 지정

            xssfSheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 2)); //첫행, 마지막행, 첫열, 마지막열 병합

            // 타이틀 생성
            xssfRow = xssfSheet.createRow(rowNo++); // 행 추가
            xssfSheet.createRow(rowNo++);

            CellStyle tableCellStyle = xssfWb.createCellStyle();
            tableCellStyle.setAlignment(HorizontalAlignment.CENTER);
            tableCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            xssfRow = xssfSheet.createRow(rowNo++);
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

            CellStyle testCellStyle = xssfWb.createCellStyle();
            testCellStyle.setAlignment(HorizontalAlignment.LEFT);
            testCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            //테스트 케이스 항목 스타일 지정

            CellStyle passCellStyle = xssfWb.createCellStyle();
            passCellStyle.setAlignment(HorizontalAlignment.CENTER);
            passCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            //통과 케이스 폰트 스타일

            XSSFFont failCellFont = xssfWb.createFont();
            failCellFont.setColor(IndexedColors.WHITE.getIndex());
            //실패 케이스 폰트 스타일

            CellStyle failCellStyle = xssfWb.createCellStyle();
            failCellStyle.setAlignment(HorizontalAlignment.CENTER);
            failCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            failCellStyle.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
            failCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            failCellStyle.setFont(failCellFont);
            //실패 케이스 스타일

            for (int i = 0; i < item.size(); i++) {


                xssfRow = xssfSheet.createRow(rowNo++); // 행 추가
                xssfCell = xssfRow.createCell((short) 0); // 추가한 행에 셀 추가
                xssfCell.setCellStyle(tableCellStyle);
                xssfCell.setCellValue(i + 1);
                //케이스 갯수만큼 티씨 넘버링

                xssfCell = xssfRow.createCell((short) 1);
                xssfCell.setCellStyle(testCellStyle);
                xssfCell.setCellValue(item.get(i));
                //케이스 셀 만들고

                xssfCell = xssfRow.createCell((short) 2);
                xssfCell.setCellValue(result.get(i));
                //결과 셀 만들기

                if (result.get(i).equals("FAIL")) {
                    xssfCell.setCellStyle(failCellStyle); //케이스 실패일 때 스타일 적용(빨간 바탕, 흰색 글씨)
                } else {
                    xssfCell.setCellStyle(passCellStyle);
                } //패스일 때는 기본 스타일대로
            }
            xssfRow = xssfSheet.createRow(0);
            xssfCell = xssfRow.createCell((short) 0); // 추가한 행에 셀 추가
            xssfCell.setCellStyle(cellStyle); // 셀에 스타일 지정
            xssfCell.setCellValue("Neo Studio(Web) Test Case");

            xssfCell = xssfRow.createCell((short) 3);
            xssfCell.setCellStyle(tableCellStyle);
            xssfCell.setCellValue("테스트 시작 시간 : ");

            xssfCell = xssfRow.createCell((short) 4);
            xssfCell.setCellStyle(tableCellStyle);
            xssfCell.setCellValue(startTime);

            xssfRow = xssfSheet.createRow(1);
            xssfCell = xssfRow.createCell((short) 3);
            xssfCell.setCellStyle(tableCellStyle);
            xssfCell.setCellValue("테스트 종료 시간 : ");

            xssfCell = xssfRow.createCell((short) 4);
            xssfCell.setCellStyle(tableCellStyle);
            xssfCell.setCellValue(endTime);

            xssfRow = xssfSheet.createRow(2);
            xssfCell = xssfRow.createCell((short) 3);
            xssfCell.setCellStyle(tableCellStyle);
            xssfCell.setCellValue("총 테스트 시간 : ");

            xssfCell = xssfRow.createCell((short) 4);
            xssfCell.setCellStyle(tableCellStyle);
            xssfCell.setCellValue(time_elapsed + "초");


            String localFile = "/Users/sykim/AutomationProject/TestCase/" + "NeoStudio_TestCase" + ".xlsx";
            //엑셀 파일 경로, 이름, 형식 지정

            File file = new File(localFile);
            FileOutputStream fos = null;
            fos = new FileOutputStream(file);
            xssfWb.write(fos);

            if (fos != null) fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
