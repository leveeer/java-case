package com.jxau.web.controllers;

import com.jxau.domain.User;
import com.jxau.service.UserService;
import com.jxau.util.ResponseUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("excel")
public class ExcelControllers {

    @Autowired
    private UserService userService;

    /**
     * 导出Excel
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("excel_down")
    public String excel_down(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String path = request.getServletContext().getRealPath("/");
        List<User> list = userService.findAll();
        Workbook wb = fillExcelDataWithTemplate(list, path + "/static/user_info.xls");
        ResponseUtil.export(response,wb,"用户信息.xls");
        return null;
    }

    public static Workbook fillExcelDataWithTemplate(List<User> list, String templateFileUrl) {

        Workbook wb = null;
        try {
            POIFSFileSystem pfs = new POIFSFileSystem(new FileInputStream(templateFileUrl));
            wb = new HSSFWorkbook(pfs);
            //取得模板的第一个sheet页
            Sheet sheet = wb.getSheetAt(0);
            //拿到sheet页有多少列
            short cellNum = sheet.getRow(0).getLastCellNum();

            int rowIndex = 1;
            Row row;
            for (User user : list) {
                row = sheet.createRow(rowIndex);
                rowIndex ++;
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getUsername());
                row.createCell(2).setCellValue(user.getMoney().toString());
                row.createCell(3).setCellValue(user.getIsLockedStr());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wb;
    }
}
