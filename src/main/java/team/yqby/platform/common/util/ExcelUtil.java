package team.yqby.platform.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * excel操作工具
 * </p>
 * User：jumping Date： 2016/10/20 0020 Version：1.0
 */
@Slf4j
public class ExcelUtil {

    /**
     * 读取excel 文件
     *
     * @param strPath    文件路径
     * @param sheetAtNum sheet索引
     * @return
     */
    public static String[][] readExcel(String strPath, int sheetAtNum) {
        XSSFWorkbook xwb = null;
        try {
            xwb = new XSSFWorkbook(new FileInputStream(strPath));
            XSSFSheet sheet = xwb.getSheetAt(sheetAtNum);
            XSSFRow row;
            Cell cell;
            String[][] stringList = new String[sheet.getPhysicalNumberOfRows()][15];
            // 循环输出表格中的内容
            for (int i = sheet.getFirstRowNum(); i < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                if (null == sheet.getRow(i)) {
                    continue;
                }
                for (int j = row.getFirstCellNum(); j < row.getPhysicalNumberOfCells(); j++) {
                    String value = "";
                    cell = row.getCell(j);
                    switch (cell.getCellType()) {
                        case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                            value =  new DecimalFormat("#").format(cell.getNumericCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_STRING: // 字符串
                            value = cell.getRichStringCellValue().getString();
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                            value = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_FORMULA: // 公式
                            break;
                        case HSSFCell.CELL_TYPE_BLANK: // 空值
                            break;
                        case HSSFCell.CELL_TYPE_ERROR: // 故障
                            break;
                        default:
                            break;
                    }
                    stringList[i][j] = StringUtils.trim(value);
                }
            }
            return stringList;
        } catch (IOException e) {
           log.error("文件操作异常,",e);

        }
        return null;
    }
}
