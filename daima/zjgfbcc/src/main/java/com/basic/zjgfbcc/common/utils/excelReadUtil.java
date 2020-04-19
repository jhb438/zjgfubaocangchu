package com.basic.zjgfbcc.common.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class excelReadUtil {


    public JSONObject importExcel(InputStream inputStream, String fileName,String[] beanPropertys,int startRow) throws Exception{

        String message = "Import success";

        boolean isE2007 = false;
        //判断是否是excel2007格式
        if(fileName.endsWith("xlsx")){
            isE2007 = true;
        }
        int rowIndex = 0;
        JSONArray array = new JSONArray();
        JSONObject res = new JSONObject();
        try {
            InputStream input = inputStream;  //建立输入流
            Workbook wb;
            //根据文件格式(2003或者2007)来初始化
            if(isE2007){
                wb = new XSSFWorkbook(input);
            }else{
                wb = new HSSFWorkbook(input);
            }
            Sheet sheet = wb.getSheetAt(0);    //获得第一个表单
            int rowCount = sheet.getLastRowNum()+1;
            
            for(int i = startRow; i < rowCount;i++){
                rowIndex = i;
                Row row ;
                JSONObject obj = new JSONObject();
                int a = 0;
//                if (hasXh) {
//    				a = 1;
//    			}
                for(int j = a;j<beanPropertys.length;j++){
                    if(isMergedRegion(sheet,i,j)){
//                    	System.out.println(beanPropertys[j]+"  >>> "+getMergedRegionValue(sheet,i,j));
                    	if (getMergedRegionValue(sheet,i,j) == null) {
                    		obj.put(beanPropertys[j], "");
						}else{
							obj.put(beanPropertys[j], getMergedRegionValue(sheet,i,j));
						}
                    	
//                        System.out.print(getMergedRegionValue(sheet,i,j)+"\t");
                    }else{
                        row = sheet.getRow(i);
//                        System.out.println(row.getCell(j)+"  2342423");
//                        System.out.println(beanPropertys[j]+"  >>> "+getCellValueByCell(row.getCell(j)));
                        
                        if (getCellValueByCell(row.getCell(j)) == null) {
                        	obj.put(beanPropertys[j],"");
						}else{
							obj.put(beanPropertys[j],getCellValueByCell(row.getCell(j)));
						}
//                      
//                        System.out.print(row.getCell(j)+"\t");
                    }
                }
                array.add(obj);
            }
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
            message =  "Import failed, please check the data in "+rowIndex+" rows ";
            res.put("code", "500");
            res.put("msg", message);
            return res;
        }
        
        res.put("code", "0");
        res.put("data", array);
        return res;
    }
    
    /**
     * 删除本地临时文件
     * @param file
     */
    public static void delteTempFile(File file) {
	    if (file != null) {
	        File del = new File(file.toURI());
	        del.delete();
	    }
    }
    
    
    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    public  String getCellValue(Cell cell){
        if(cell == null) return "";
        return cell.getStringCellValue();
    }
    
    /**
     * 通用的读取excel单元格的处理方法
     * @param cell
     * @return
     */
    @SuppressWarnings("deprecation")
	private static Object getCellValueByCell(Cell cell) {
        Object result = null;
        if (cell != null) {
//        	cell.setCellType(CellType.STRING);
//        	System.out.println(cell.getCellType());
            switch (cell.getCellType()) {
            case STRING:
                result = cell.getStringCellValue();
                break;
            case NUMERIC:
                //对日期进行判断和解析
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    double cellValue = cell.getNumericCellValue();
                    result = HSSFDateUtil.getJavaDate(cellValue);
                }else {
                	cell.setCellType(CellType.STRING);
					result = cell.getStringCellValue();
				}
                break;
            case BOOLEAN:
                result = cell.getBooleanCellValue();
                break;
            case FORMULA:
                result = cell.getCellFormula();
                break;
            case ERROR:
                result = cell.getErrorCellValue();
                break;
            case BLANK:
                break;
            default:
                break;
            }
        }else{
        	result = "";
        }
        return result;
    }


    /**
     * 合并单元格处理,获取合并行
     * @param sheet
     * @return List<CellRangeAddress>
     */
    public  List<CellRangeAddress> getCombineCell(Sheet sheet)
    {
        List<CellRangeAddress> list = new ArrayList<>();
        //获得一个 sheet 中合并单元格的数量
        int sheetmergerCount = sheet.getNumMergedRegions();
        //遍历所有的合并单元格
        for(int i = 0; i<sheetmergerCount;i++)
        {
            //获得合并单元格保存进list中
            CellRangeAddress ca = sheet.getMergedRegion(i);
            list.add(ca);
        }
        return list;
    }

    private  int getRowNum(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet){
        int xr = 0;
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    xr = lastR;
                }
            }

        }
        return xr;

    }
    /**
     * 判断单元格是否为合并单元格，是的话则将单元格的值返回
     * @param listCombineCell 存放合并单元格的list
     * @param cell 需要判断的单元格
     * @param sheet sheet
     * @return
     */
    public  String isCombineCell(List<CellRangeAddress> listCombineCell,Cell cell,Sheet sheet)
            throws Exception{
        int firstC = 0;
        int lastC = 0;
        int firstR = 0;
        int lastR = 0;
        String cellValue = null;
        for(CellRangeAddress ca:listCombineCell)
        {
            //获得合并单元格的起始行, 结束行, 起始列, 结束列
            firstC = ca.getFirstColumn();
            lastC = ca.getLastColumn();
            firstR = ca.getFirstRow();
            lastR = ca.getLastRow();
            if(cell.getRowIndex() >= firstR && cell.getRowIndex() <= lastR)
            {
                if(cell.getColumnIndex() >= firstC && cell.getColumnIndex() <= lastC)
                {
                    Row fRow = sheet.getRow(firstR);
                    Cell fCell = fRow.getCell(firstC);
                    cellValue = getCellValue(fCell);
                    break;
                }
            }
            else
            {
                cellValue = "";
            }
        }
        return cellValue;
    }

    /**
     * 获取合并单元格的值
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public  String getMergedRegionValue(Sheet sheet ,int row , int column){
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell) ;
                }
            }
        }

        return null ;
    }


    /**
     * 判断指定的单元格是否是合并单元格
     * @param sheet
     * @param row 行下标
     * @param column 列下标
     * @return
     */
    private  boolean isMergedRegion(Sheet sheet,int row ,int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if(row >= firstRow && row <= lastRow){
                if(column >= firstColumn && column <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }
    
}
