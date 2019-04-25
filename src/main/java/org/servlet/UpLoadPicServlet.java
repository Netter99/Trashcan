package org.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.constant.ResponseCode;
import org.util.JsonUtil;

@WebServlet(name = "UpLoadPicServlet")
public class UpLoadPicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        Map<String, Object> map = new HashMap<>();
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");
        if (id >= 0) {
            if (isMultipart) {
                DiskFileItemFactory factory = new DiskFileItemFactory();

                ServletFileUpload upload = new ServletFileUpload(factory);

                factory.setSizeThreshold(10240);
                factory.setRepository(new File("F:\\Eclipese\\Trashcan\\images"));
                upload.setFileSizeMax(20480);

                List<FileItem> items = null;
                try {
                    items = upload.parseRequest(request);

                    Iterator<FileItem> iter = items.iterator();
                    while (iter.hasNext()) {
                        FileItem item = iter.next();

                        if (!item.isFormField()) {
                            String fileName = item.getName();//a.txt  a.docx  a.png
                            String ext = fileName.substring(fileName.indexOf(".") + 1);
                            if (!(ext.equals("png") || ext.equals("jpg") || ext.equals("gif"))) {
                                System.out.println("图片类型有误！格式只能是phg gif jpg");
                                return;
                            }
                            String path = "F:\\software\\upload";

                            File file = new File(path, fileName);

                            item.write(file);
                            System.out.println(fileName + "上传成功");
                            map.put("code", ResponseCode.REQUEST_SUCCEED.getValue());
                            String json = JsonUtil.mapToJson(map);
                            PrintWriter writer = response.getWriter();
                            writer.write(json);
                            return;
                        } else {
                            map.put("code", ResponseCode.PARAM_ILEGALL.getValue());
                            map.put("msg", "用户名或密码错误");
                            String json = JsonUtil.mapToJson(map);
                            PrintWriter writer = response.getWriter();
                            writer.write(json);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            map.put("request_result", ResponseCode.NOT_LOGIN.getValue());
            String json = JsonUtil.mapToJson(map);
            response.getWriter().write(json);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
