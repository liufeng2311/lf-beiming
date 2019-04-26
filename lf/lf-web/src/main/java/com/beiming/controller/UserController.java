package com.beiming.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.catalina.util.URLEncoder;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.beiming.dto.request.UserListRequestDTO;
import com.beiming.user.UserService;
import com.beiming.util.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="用户信息接口",tags="用户信息接口")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;
/*  @ApiOperation(value="获取用户列表",notes="获取用户列表")
  @PreAuthorize("hasRole('ADMIN')")
  @RequestMapping(value="/getUserList",method=RequestMethod.GET)
  public ResultModel getUserList(@RequestBody @Valid UserListRequestDTO userListRequestDTO) {
    return ResultModel.success(userService.getUserList(userListRequestDTO));
    
  }*/
  @ApiOperation(value="获取用户列表",notes="获取用户列表")
  @RequestMapping(value="/admin",method=RequestMethod.GET)
 // @PreAuthorize("hasRole('ROLE_ADMIN')")//该注解必须在配置文件设置为.anyRequest().authenticated()才生效
 // @PreAuthorize("hasAuthority('ADMIN')")  //配置文件选择过滤时，不应该写注解
  public ResultModel getUserList() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResultModel.success(authentication);
    
  }
  
  
  @ApiOperation(value="获取用户列表",notes="获取用户列表")
  @RequestMapping(value="/user",method=RequestMethod.GET)
  //@PreAuthorize("hasRole('USER')")
  @PreAuthorize("hasAuthority('USER')")                                 //两者不可通用，默认使用的是hasAuthority，要使用hasRole赋权限时要加ROLE_
  public ResultModel getUserList1() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResultModel.success(authentication);
    
  }
  
  
  
  @RequestMapping(value="/out",method=RequestMethod.GET)
  //@PreAuthorize("hasRole('USER')")
  
  @SuppressWarnings("resource")  
  public void out(HttpServletResponse response) throws Exception {
    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = wb.createSheet("信访件详情");     //1.创建一个sheet页
    sheet.setDefaultColumnWidth(40);                 //创建单元格的默认宽度40
    sheet.setDefaultRowHeightInPoints(25);           //设置单元格的默认高度
    sheet.setColumnWidth(0, 25 * 256);               //设置表格某一列的宽度，（优先级大于默认宽度）
    sheet.setColumnWidth(2, 25 * 256); 
    //设置用户信息的样式
    HSSFFont fontInfo=wb.createFont(); //
    //fontInfo.setColor(IndexedColors.BLACK.index); //字体设置颜色
    fontInfo.setFontHeightInPoints((short)15);  //设置字体大小
    HSSFCellStyle userInfoStyle = wb.createCellStyle(); //设置标题单元格的样式
    userInfoStyle.setAlignment(HorizontalAlignment.CENTER);//左右居中
    userInfoStyle.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
    userInfoStyle.setBorderBottom(BorderStyle.THIN);//下边框        
    userInfoStyle.setBorderLeft(BorderStyle.THIN);//左边框        
    userInfoStyle.setBorderRight(BorderStyle.THIN);//右边框        
    userInfoStyle.setBorderTop(BorderStyle.THIN);//上边框 
    userInfoStyle.setFont(fontInfo);    
    //设置表单标题的样式
    HSSFFont fontTitle=wb.createFont(); //设置单元格字体样式
    fontTitle.setColor(IndexedColors.BLACK.index); //设置颜色
    fontTitle.setFontHeightInPoints((short)20);  //设置字体大小
    //fontTitle.setBold(true);
    HSSFCellStyle titleStyle = wb.createCellStyle(); //设置标题单元格的样式
    titleStyle.setAlignment(HorizontalAlignment.CENTER);//左右居中
    titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
    titleStyle.setBorderBottom(BorderStyle.THIN);//下边框        
    titleStyle.setBorderLeft(BorderStyle.THIN);//左边框        
    titleStyle.setBorderRight(BorderStyle.THIN);//右边框        
    titleStyle.setBorderTop(BorderStyle.THIN);//上边框 
    titleStyle.setFont(fontTitle);
    sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 3)); //信访件详情
    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 3)); //具体住址
    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 3)); //求决类
    sheet.addMergedRegion(new CellRangeAddress(7, 8, 0, 3)); //工作开展情况
    sheet.addMergedRegion(new CellRangeAddress(9, 9, 1, 3)); //具体诉求
    sheet.addMergedRegion(new CellRangeAddress(10, 10, 1, 3)); //工作情况
    sheet.addMergedRegion(new CellRangeAddress(11, 12, 0, 3)); //多元化解工作流程
    sheet.addMergedRegion(new CellRangeAddress(13, 13, 1, 3)); //责任单位
    sheet.addMergedRegion(new CellRangeAddress(14, 14, 1, 3)); //属地
    sheet.addMergedRegion(new CellRangeAddress(15, 15, 1, 3)); //社会力量
    sheet.addMergedRegion(new CellRangeAddress(16, 16, 1, 3)); //化解方案
    sheet.addMergedRegion(new CellRangeAddress(17, 17, 1, 3)); //负责人
    sheet.addMergedRegion(new CellRangeAddress(18, 18, 1, 3)); //事件标签
    sheet.addMergedRegion(new CellRangeAddress(19, 19, 1, 3)); //处理建议

    //填入数据
    HSSFRow row = sheet.createRow(0); //获取第一行
    row.createCell(0).setCellValue("信访件详情预览"); //在第一行中创建一个单元格并赋值
    row.getCell(0).setCellStyle(titleStyle);
    row.createCell(1).setCellValue("");
    row.createCell(2).setCellValue("");
    row.createCell(3).setCellValue("");
    row.getCell(1).setCellStyle(titleStyle);
    row.getCell(2).setCellStyle(titleStyle);
    row.getCell(3).setCellStyle(titleStyle);
    HSSFRow rows1 = sheet.createRow(1); //获取第一行
    rows1.createCell(0).setCellValue(""); //在第一行中创建一个单元格并赋值
    rows1.getCell(0).setCellStyle(titleStyle);
    rows1.createCell(1).setCellValue("");
    rows1.createCell(2).setCellValue("");
    rows1.createCell(3).setCellValue("");
    rows1.getCell(1).setCellStyle(titleStyle);
    rows1.getCell(2).setCellStyle(titleStyle);
    rows1.getCell(3).setCellStyle(titleStyle);
    HSSFRow rows2 = sheet.createRow(2); //获取第一行
    rows2.createCell(0).setCellValue(""); //在第一行中创建一个单元格并赋值
    rows2.getCell(0).setCellStyle(titleStyle);
    rows2.createCell(1).setCellValue("");
    rows2.createCell(2).setCellValue("");
    rows2.createCell(3).setCellValue("");
    rows2.getCell(1).setCellStyle(titleStyle);
    rows2.getCell(2).setCellStyle(titleStyle);
    rows2.getCell(3).setCellStyle(titleStyle);
    HSSFRow row1 = sheet.createRow(3); //获取第二行,并为每一行添加列
    row1.createCell(0).setCellValue("信访人姓名");
    row1.getCell(0).setCellStyle(userInfoStyle);
    row1.createCell(1).setCellValue("");
    row1.getCell(1).setCellStyle(userInfoStyle);
    row1.createCell(2).setCellValue("性别");
    row1.getCell(2).setCellStyle(userInfoStyle);
    row1.createCell(3).setCellValue("");
    row1.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row2 = sheet.createRow(4); //获取第三行
    row2.createCell(0).setCellValue("手机号");
    row2.getCell(0).setCellStyle(userInfoStyle);
    row2.createCell(1).setCellValue("");
    row2.getCell(1).setCellStyle(userInfoStyle);
    row2.createCell(2).setCellValue("身份证号");
    row2.getCell(2).setCellStyle(userInfoStyle);
    row2.createCell(3).setCellValue("");
    row2.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row3 = sheet.createRow(5); //获取第四行
    row3.createCell(0).setCellValue("住址");
    row3.getCell(0).setCellStyle(userInfoStyle);
    row3.createCell(1).setCellValue("");
    row3.getCell(1).setCellStyle(userInfoStyle);
    row3.createCell(2).setCellValue("");
    row3.createCell(3).setCellValue("");
    row3.getCell(2).setCellStyle(userInfoStyle);
    row3.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row4 = sheet.createRow(6); //获取第五行
    row4.createCell(0).setCellValue("矛盾分类");
    row4.getCell(0).setCellStyle(userInfoStyle);
    row4.createCell(1).setCellValue("");
    row4.getCell(1).setCellStyle(userInfoStyle);
    row4.createCell(2).setCellValue("");
    row4.createCell(3).setCellValue("");
    row4.getCell(2).setCellStyle(userInfoStyle);
    row4.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row5 = sheet.createRow(7); //获取第五行
    row5.createCell(0).setCellValue("工作开展情况");
    row5.getCell(0).setCellStyle(titleStyle);
    row5.createCell(1).setCellValue("");
    row5.createCell(2).setCellValue("");
    row5.createCell(3).setCellValue("");
    row5.getCell(1).setCellStyle(titleStyle);
    row5.getCell(2).setCellStyle(titleStyle);
    row5.getCell(3).setCellStyle(titleStyle);
    HSSFRow rows5 = sheet.createRow(8); //获取第五行
    rows5.createCell(0).setCellValue("");
    rows5.getCell(0).setCellStyle(titleStyle);
    rows5.createCell(1).setCellValue("");
    rows5.createCell(2).setCellValue("");
    rows5.createCell(3).setCellValue("");
    rows5.getCell(1).setCellStyle(titleStyle);
    rows5.getCell(2).setCellStyle(titleStyle);
    rows5.getCell(3).setCellStyle(titleStyle);
    HSSFRow row8 = sheet.createRow(9); //获取第五行
    row8.createCell(0).setCellValue("具体诉求");
    row8.getCell(0).setCellStyle(userInfoStyle);
    row8.createCell(1).setCellValue("");
    row8.getCell(1).setCellStyle(userInfoStyle);
    row8.createCell(2).setCellValue("");
    row8.createCell(3).setCellValue("");
    row8.getCell(2).setCellStyle(userInfoStyle);
    row8.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row9 = sheet.createRow(10); //获取第五行
    row9.createCell(0).setCellValue("工作情况");
    row9.getCell(0).setCellStyle(userInfoStyle);
    row9.createCell(1).setCellValue("");
    row9.getCell(1).setCellStyle(userInfoStyle);
    row9.createCell(2).setCellValue("");
    row9.createCell(3).setCellValue("");
    row9.getCell(2).setCellStyle(userInfoStyle);
    row9.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row10 = sheet.createRow(11); //获取第五行
    row10.createCell(0).setCellValue("多元化解工作流程");
    row10.createCell(1).setCellValue("");
    row10.createCell(2).setCellValue("");
    row10.createCell(3).setCellValue("");
    row10.getCell(1).setCellStyle(titleStyle);
    row10.getCell(2).setCellStyle(titleStyle);
    row10.getCell(3).setCellStyle(titleStyle);
    row10.getCell(0).setCellStyle(titleStyle);
    HSSFRow rows10 = sheet.createRow(12); //获取第五行
    rows10.createCell(0).setCellValue("");
    rows10.createCell(1).setCellValue("");
    rows10.createCell(2).setCellValue("");
    rows10.createCell(3).setCellValue("");
    rows10.getCell(1).setCellStyle(titleStyle);
    rows10.getCell(2).setCellStyle(titleStyle);
    rows10.getCell(3).setCellStyle(titleStyle);
    rows10.getCell(0).setCellStyle(titleStyle);
    HSSFRow row11 = sheet.createRow(13); //获取第五行
    row11.createCell(0).setCellValue("责任单位");
    row11.getCell(0).setCellStyle(userInfoStyle);
    row11.createCell(1).setCellValue("");
    row11.getCell(1).setCellStyle(userInfoStyle);
    row11.createCell(2).setCellValue("");
    row11.createCell(3).setCellValue("");
    row11.getCell(2).setCellStyle(userInfoStyle);
    row11.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row12 = sheet.createRow(14); //获取第五行
    row12.createCell(0).setCellValue("属地");
    row12.getCell(0).setCellStyle(userInfoStyle);
    row12.createCell(1).setCellValue(""); 
    row12.getCell(1).setCellStyle(userInfoStyle);
    row12.createCell(2).setCellValue("");
    row12.createCell(3).setCellValue("");
    row12.getCell(2).setCellStyle(userInfoStyle);
    row12.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row13 = sheet.createRow(15); //获取第五行
    row13.createCell(0).setCellValue("社会力量");
    row13.getCell(0).setCellStyle(userInfoStyle);
    row13.createCell(1).setCellValue("");    
    row13.getCell(1).setCellStyle(userInfoStyle);
    row13.createCell(2).setCellValue("");
    row13.createCell(3).setCellValue("");
    row13.getCell(2).setCellStyle(userInfoStyle);
    row13.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row14 = sheet.createRow(16); //获取第五行
    row14.createCell(0).setCellValue("化解方案");
    row14.getCell(0).setCellStyle(userInfoStyle);
    row14.createCell(1).setCellValue("");
    row14.createCell(2).setCellValue("");
    row14.createCell(3).setCellValue("");
    row14.getCell(2).setCellStyle(userInfoStyle);
    row14.getCell(3).setCellStyle(userInfoStyle);
    row14.getCell(1).setCellStyle(userInfoStyle);
    HSSFRow row15 = sheet.createRow(17); //获取第五行
    row15.createCell(0).setCellValue("负责人");
    row15.getCell(0).setCellStyle(userInfoStyle);
    row15.createCell(1).setCellValue("");  
    row15.getCell(1).setCellStyle(userInfoStyle);
    row15.createCell(2).setCellValue("");
    row15.createCell(3).setCellValue("");
    row15.getCell(2).setCellStyle(userInfoStyle);
    row15.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row16 = sheet.createRow(18); //获取第五行
    row16.createCell(0).setCellValue("事件标签");
    row16.getCell(0).setCellStyle(userInfoStyle);
    row16.createCell(1).setCellValue("");
    row16.getCell(1).setCellStyle(userInfoStyle);
    row16.createCell(2).setCellValue("");
    row16.createCell(3).setCellValue("");
    row16.getCell(2).setCellStyle(userInfoStyle);
    row16.getCell(3).setCellStyle(userInfoStyle);
    HSSFRow row17 = sheet.createRow(19); //获取第五行
    row17.createCell(0).setCellValue("处理建议");
    row17.getCell(0).setCellStyle(userInfoStyle);
    row17.createCell(1).setCellValue("");
    row17.getCell(1).setCellStyle(userInfoStyle);
    row17.createCell(2).setCellValue("");
    row17.createCell(3).setCellValue("");
    row17.getCell(2).setCellStyle(userInfoStyle);
    row17.getCell(3).setCellStyle(userInfoStyle);
    response.setContentType("application/ms-excel;charset=UTF-8");
    response.setHeader("Content-Disposition", "attachment;filename="
        +java.net.URLEncoder.encode("信访导出.xls","UTF-8"));
    OutputStream out = response.getOutputStream();
        wb.write(out);
        out.close();
  }
  

  /**
   * 初始化Excel表格
   */
  public HSSFSheet createCellStyle(HSSFWorkbook wb) {
    HSSFSheet sheet = wb.createSheet("信访详情");       //创建一个sheet页
    sheet.setDefaultColumnWidth(40);                 //创建单元格的默认宽度40
    sheet.setDefaultRowHeightInPoints(25);           //设置单元格的默认高度
    for(int i =0; i< 20; i++) {                      //循环创建cell
      HSSFRow hssRow = sheet.createRow(i);
      for(int j =0; j<4 ; j++) {
        hssRow.createCell(j);
      }
    }
    return sheet;
  }
  
  /**
   * 用户信息样式
   * @param workBook
   * @return
   */
  public HSSFCellStyle createUserInfoCellStyle(HSSFWorkbook workBook) {
    HSSFFont fontInfo=workBook.createFont(); 
    fontInfo.setFontHeightInPoints((short)15);  //设置字体大小
    HSSFCellStyle style = workBook.createCellStyle(); //设置标题单元格的样式
    style.setAlignment(HorizontalAlignment.CENTER);//左右居中
    style.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
    style.setBorderBottom(BorderStyle.THIN);//下边框        
    style.setBorderLeft(BorderStyle.THIN);//左边框        
    style.setBorderRight(BorderStyle.THIN);//右边框        
    style.setBorderTop(BorderStyle.THIN);//上边框 
    style.setFont(fontInfo); 
    return style;
  }
  
  /**
   * 标题信息样式
   * @param workBook
   * @return
   */
  public HSSFCellStyle createTitleCellStyle(HSSFWorkbook workBook) {
    HSSFFont fontTitle=workBook.createFont(); //设置单元格字体样式
    fontTitle.setColor(IndexedColors.BLACK.index); //设置颜色
    fontTitle.setFontHeightInPoints((short)20);  //设置字体大小
    //fontTitle.setBold(true); //加粗
    HSSFCellStyle titleStyle = workBook.createCellStyle(); //设置标题单元格的样式
    titleStyle.setAlignment(HorizontalAlignment.CENTER);//左右居中
    titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);//上下居中
    titleStyle.setBorderBottom(BorderStyle.THIN);//下边框        
    titleStyle.setBorderLeft(BorderStyle.THIN);//左边框        
    titleStyle.setBorderRight(BorderStyle.THIN);//右边框        
    titleStyle.setBorderTop(BorderStyle.THIN);//上边框 
    titleStyle.setFont(fontTitle);
    return titleStyle;
  }

  /**
   * 合并单元格
   * @param sheet
   * @return
   */
  public HSSFSheet mergedRegion(HSSFSheet sheet) {
    sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 3)); //信访件详情
    sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 3)); //具体住址
    sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 3)); //求决类
    sheet.addMergedRegion(new CellRangeAddress(7, 8, 0, 3)); //工作开展情况
    sheet.addMergedRegion(new CellRangeAddress(9, 9, 1, 3)); //具体诉求
    sheet.addMergedRegion(new CellRangeAddress(10, 10, 1, 3)); //工作情况
    sheet.addMergedRegion(new CellRangeAddress(11, 12, 0, 3)); //多元化解工作流程
    sheet.addMergedRegion(new CellRangeAddress(13, 13, 1, 3)); //责任单位
    sheet.addMergedRegion(new CellRangeAddress(14, 14, 1, 3)); //属地
    sheet.addMergedRegion(new CellRangeAddress(15, 15, 1, 3)); //社会力量
    sheet.addMergedRegion(new CellRangeAddress(16, 16, 1, 3)); //化解方案
    sheet.addMergedRegion(new CellRangeAddress(17, 17, 1, 3)); //负责人
    sheet.addMergedRegion(new CellRangeAddress(18, 18, 1, 3)); //事件标签
    sheet.addMergedRegion(new CellRangeAddress(19, 19, 1, 3)); //处理建议
    return sheet;
  }
  /**
   * 设置单元格样式
   * @param sheet
   * @param titie
   * @param userInfo
   * @return
   */
  public HSSFSheet setStyle(HSSFSheet sheet,HSSFCellStyle titie, HSSFCellStyle userInfo) {
    for(int i =0; i< 20; i++) {                      //循环创建cell
      HSSFRow row = sheet.getRow(i);
      for(int j =0; j<4 ; j++) {
        HSSFCell cell = row.getCell(j);
        if (i ==0 || i== 1 || i==2 || i==7 || i==8 || i==11 || i==12) {
          cell.setCellStyle(titie);
        }else {
          cell.setCellStyle(userInfo);
        }
      }
    }
    return sheet;
  }  
  
  /**
   * 初始化Excel模板
   * @param sheet
   * @param titie
   * @param userInfo
   * @return
   */
  public HSSFSheet init(HSSFSheet sheet) {
    sheet.getRow(0).getCell(0).setCellValue("信访件详情预览");
    sheet.getRow(3).getCell(0).setCellValue("信访人姓名");
    sheet.getRow(3).getCell(2).setCellValue("性别");
    sheet.getRow(4).getCell(0).setCellValue("手机号");
    sheet.getRow(4).getCell(2).setCellValue("身份证号");
    sheet.getRow(5).getCell(0).setCellValue("住址");
    sheet.getRow(6).getCell(2).setCellValue("矛盾分类");
    sheet.getRow(7).getCell(0).setCellValue("工作开展情况");
    sheet.getRow(9).getCell(0).setCellValue("具体诉求");
    sheet.getRow(10).getCell(0).setCellValue("工作情况");
    sheet.getRow(11).getCell(0).setCellValue("多元化解工作流程");
    sheet.getRow(13).getCell(0).setCellValue("责任单位");
    sheet.getRow(14).getCell(0).setCellValue("属地");
    sheet.getRow(15).getCell(0).setCellValue("社会力量");
    sheet.getRow(16).getCell(0).setCellValue("化解方案");
    sheet.getRow(17).getCell(0).setCellValue("负责人");
    sheet.getRow(18).getCell(0).setCellValue("事件标签");
    sheet.getRow(19).getCell(0).setCellValue("处理建议");
    return sheet;
  }
  @RequestMapping("/outTest")
  public void outTest(HttpServletResponse response) throws Exception {
    HSSFWorkbook wb = new HSSFWorkbook();
    HSSFSheet sheet = createCellStyle(wb);
    sheet.setColumnWidth(0, 20 * 256);     //设置第一列和第三列的宽度
    sheet.setColumnWidth(2, 20 * 256); 
    HSSFCellStyle userCellStyle = createUserInfoCellStyle(wb);
    HSSFCellStyle titleCellStyle = createTitleCellStyle(wb);
    sheet = mergedRegion(sheet);
    sheet = setStyle(sheet,titleCellStyle,userCellStyle);
    HSSFSheet init = init(sheet);
    response.setContentType("application/ms-excel;charset=UTF-8");
    response.setHeader("Content-Disposition", "attachment;filename="
        +java.net.URLEncoder.encode("信访导出.xls","UTF-8"));
    OutputStream out = response.getOutputStream();
        wb.write(out);
        out.close();
  }
}
