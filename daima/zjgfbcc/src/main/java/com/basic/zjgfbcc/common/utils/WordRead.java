package com.basic.zjgfbcc.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * word文档读取
 *
 * @author wzl
 */
public class WordRead {
    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    /**
     * 获取流文件
     */
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     *
     * @param file
     */
    public static void delteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }

    /**
     * 读取word中的内容
     *
     * @param inputStream
     * @param fileName
     * @param beanPropertys
     * @return
     */
    public JSONObject readWord(InputStream inputStream, String fileName, String[] beanPropertys) throws Exception {
        JSONObject res = new JSONObject();
        String fileType = fileName.substring(fileName.indexOf("."));
        try {
            if (".doc".equals(fileType) || ".docx".equals(fileType)) {
                HWPFDocument doc = new HWPFDocument(inputStream);
                //输出书签信息
                //this.printInfo(doc.getBookmarks());
                //输出文本
                System.out.println("123" + doc.getDocumentText());
                Range range = doc.getRange();
                this.printInfo(range);
                //读表格
                this.readTable(range);
                //读列表
                this.readList(range);
                doc.close();
            } else {
                System.out.println("此文件不是word文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 输出书签信息
     *
     * @param bookmarks
     */
    private void printInfo(Bookmarks bookmarks) {
        int count = bookmarks.getBookmarksCount();
        System.out.println("书签数量：" + count);
        Bookmark bookmark;
        for (int i = 0; i < count; i++) {
            bookmark = bookmarks.getBookmark(i);
            System.out.println("书签" + (i + 1) + "的名称是：" + bookmark.getName());
            System.out.println("开始位置：" + bookmark.getStart());
            System.out.println("结束位置：" + bookmark.getEnd());
        }
    }

    /**
     * 读表格
     * 每一个回车符代表一个段落，所以对于表格而言，每一个单元格至少包含一个段落，每行结束都是一个段落。
     *
     * @param range
     */
    private void readTable(Range range) {
        //遍历range范围内的table。
        TableIterator tableIter = new TableIterator(range);
        Table table;
        TableRow row;
        TableCell cell;
        while (tableIter.hasNext()) {
            table = tableIter.next();
            int rowNum = table.numRows();
            for (int j = 0; j < rowNum; j++) {
                row = table.getRow(j);
                int cellNum = row.numCells();
                for (int k = 0; k < cellNum; k++) {
                    cell = row.getCell(k);
                    //输出单元格的文本
                    System.out.println("单元格文本-" + j + "~" + k + "-" + cell.text().trim());
                }
            }
        }
    }

    /**
     * 读列表
     *
     * @param range
     */
    private void readList(Range range) {
        int num = range.numParagraphs();
        Paragraph para;
        for (int i = 0; i < num; i++) {
            para = range.getParagraph(i);
            if (para.isInList()) {
                System.out.println("list: " + i + "-" + para.text());
            }
        }
    }

    /**
     * 输出Range
     *
     * @param range
     */
    private void printInfo(Range range) {
        //获取段落数
        int paraNum = range.numParagraphs();
        System.out.println("段落数-" + paraNum);
        for (int i = 0; i < paraNum; i++) {
            System.out.println("段落" + (i + 1) + "：" + range.getParagraph(i).text());
        }
        int secNum = range.numSections();
        System.out.println("sec-" + secNum);
        Section section;
        for (int i = 0; i < secNum; i++) {
            section = range.getSection(i);
            System.out.println("段落-" + i + "-" + section.getMarginLeft());
            System.out.println("段落-" + i + "-" + section.getMarginRight());
            System.out.println("段落-" + i + "-" + section.getMarginTop());
            System.out.println("段落-" + i + "-" + section.getMarginBottom());
            System.out.println("段落-" + i + "-" + section.getPageHeight());
            System.out.println("段落-" + i + "-" + section.text());
        }
    }
}
