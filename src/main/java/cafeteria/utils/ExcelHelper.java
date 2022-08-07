package cafeteria.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cafeteria.dao.EmployeeDAO;
import cafeteria.dao.StatisticDAO;
import cafeteria.entity.Bill;
import cafeteria.entity.Employee;
import cafeteria.exception.FormatVietNamException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

    public static List<Object[]> readExcelFile(String path, int sheet) throws FileNotFoundException, IOException {
        List<Object[]> obList = new ArrayList<>();
        File file = new File(path);
        FileInputStream input = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(input);
        Sheet sh = wb.getSheetAt(sheet);
        for (Row row : sh) {
            Object[] obArray = new Object[6];
            for (int i = 0; i < 6; i++) {
                obArray[i] = row.getCell(i).getStringCellValue();
            }
            obList.add(obArray);
        }

        input.close();
        wb.close();

        return obList;
    }

    public static void writeExcelFile(String path, Bill hoaDon, int tienKhachTra) throws IOException, SQLException, FormatVietNamException {

        List<Object[]> obList = new StatisticDAO().getHoaDonChiTiet(hoaDon.getMaHoaDon());
        File file = new File(path);
        File fileParent = file.getParentFile();
        if(!fileParent.exists()) fileParent.mkdir();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("receipt");

        int[] lineMerge = {0, obList.size() + 10, obList.size() + 11, obList.size() + 12};
        mergeRow(sheet, 3, lineMerge);
        mergeRow(sheet, 2, obList.size() + 8, obList.size() + 9);

        setReceipt(sheet, hoaDon, obList, tienKhachTra);
        
        FileOutputStream out = new FileOutputStream(file);
        printOut(wb, out);
        finish(wb, out);
    }

    private static void setReceipt(Sheet sheet, Bill hoaDon, List<Object[]> obList, int tienKhachTra) throws FormatVietNamException {
        
        CellStyle csTitle = createCs(sheet, 20, false);
        CellStyle cs = createCs(sheet, 13, true);

        List<Cell> cellListTitle = createRow(sheet, 0, "HÓA ĐƠN THANH TOÁN");
        setCellStyleTo(cellListTitle, csTitle);

        List<Cell> cellListHeader = createRow(sheet, 6, "Tên SP", "Số Lượng", "Đơn Giá", "Tổng Tiền");
        setCellStyleTo(cellListHeader, cs);

        List<Cell> cellListBye = createRow(sheet, obList.size() + 10, "Cảm ơn quý khách. Hẹn gặp lại!");
        setCellStyleTo(cellListBye, cs);

        List<Cell> cellListAddress = createRow(sheet, obList.size() + 11, "Đ/C: Trịnh Văn Bô-Xuân Phương-Nam Từ Liêm-Hà Nội");
        setCellStyleTo(cellListAddress, cs);

        List<Cell> cellListContact = createRow(sheet, obList.size() + 12, "Liên hệ: 0387958475-0395411500");
        setCellStyleTo(cellListContact, cs);

        String[][] dataPattern = getPatternData(hoaDon);
        for (int i = 0; i < dataPattern.length; i++) {
            createRow(sheet, i + 1, dataPattern[i]);
        }
        int tongTien = setTable(obList, sheet);
        List<Cell> cellListTotal = createRow(sheet, obList.size() + 8, "Tổng","","",LocalVietNam.getCurrency(tongTien));
        setCellStyleTo(cellListTotal, cs);
        
        List<Cell> cellListCacsh = createRow(sheet, obList.size() + 9, "Khách Trả","","",LocalVietNam.getCurrency(tienKhachTra));
        setCellStyleTo(cellListCacsh, cs);
    }

    private static List<Cell> createRow(Sheet sh, int row, String... content) {
        List<Cell> cellList = new ArrayList<>();
        Row r = sh.createRow(row);
        for (int i = 0; i < content.length; i++) {
            Cell cel = createCell(r, i, content[i]);
            sh.autoSizeColumn(i);
            cellList.add(cel);
        }
        return cellList;
    }

    private static CellStyle createDefaultFontSize(Sheet sheet) {
        Workbook wb = sheet.getWorkbook();
        CellStyle cell = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 13);
        cell.setFont(font);
        return cell;
    }

    private static Cell createCell(Row r, int cell, String content) {
        CellStyle cs = createDefaultFontSize(r.getSheet());
        Cell c = r.createCell(cell, CellType.STRING);
        c.setCellValue(content);
        c.setCellStyle(cs);
        return c;
    }

    private static void setCellStyleTo(List<Cell> cellList, CellStyle cs) {
        for (Cell cell : cellList) {
            cell.setCellStyle(cs);
        }
    }

    private static String[][] getPatternData(Bill hd) {
        Employee nv = new EmployeeDAO().selectByID(hd.getMaNhanVien());
        String[][] dataPattern = {
            {"Mã Hóa Đơn", hd.getMaHoaDon() + ""},
            {"Loại Khách Hàng: ", hd.isLoaiKhachHang() ? "Nước ngoài" : "Trong nước"},
            {"Ngày: ", LocalVietNam.getDate(hd.getNgayXuatHoaDon())},
            {"NV Bán Hàng: ", nv.getTenNhanVien()}};

        return dataPattern;
    }

    private static CellStyle createCs(Sheet sheet, int fontSize, boolean isBold) {
        CellStyle cs = sheet.getWorkbook().createCellStyle();
        cs.setAlignment(HorizontalAlignment.CENTER);
        Font fontTitle = sheet.getWorkbook().createFont();
        fontTitle.setFontHeightInPoints((short) fontSize);
        fontTitle.setBold(isBold);
        cs.setFont(fontTitle);

        return cs;
    }

    private static int setTable(List<Object[]> obList, Sheet sh) throws FormatVietNamException {
        int tongTien = 0;
        for (int i = 0; i < obList.size(); i++) {
            Object[] ob = obList.get(i);
            int tong = Integer.parseInt(ob[1].toString()) * Integer.parseInt(ob[2].toString());
            String[] data = {
                ob[0].toString(),
                ob[1].toString(),
                ob[2].toString(),
                LocalVietNam.getCurrency(String.valueOf(tong))
            };
            tongTien += tong;
            createRow(sh, i + 7, data);
        }
        return tongTien;
    }

    private static void mergeRow(Sheet sheet, int lastCell, int... rows) {
        for (int r : rows) {
            CellRangeAddress merge = new CellRangeAddress(r, r, 0, lastCell);
            sheet.addMergedRegion(merge);
        }
    }

    private static void printOut(Workbook wb, FileOutputStream out) throws IOException {
        wb.write(out);
    }

    private static void finish(Workbook wb, FileOutputStream out) throws IOException {
        out.close();
        wb.close();
    }
}
