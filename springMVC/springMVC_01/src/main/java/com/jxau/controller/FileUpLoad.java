package com.jxau.controller;

import com.jxau.domain.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("file")
public class FileUpLoad {


    /**
     * 传统文件上传方式
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/fileUpload")
    public String fileUpload(HttpServletRequest request) throws Exception {
        //上传的位置
        String path = request.getSession().getServletContext().getRealPath("/updates");
        //判断该路径是否存在
        File file = new File(path);
        if (!file.exists()){
            //文件夹不存在，创建
            file.mkdirs();
        }

        //解析request对象，获取文件上传项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解析request
        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            //获取每一个item
            //进行判断，当前item对象是否是上传文件项
            if (item.isFormField()){
                //普通表单项

            }else {
                //是上传文件项
                //获取上传文件的名称
                String fileName = item.getName();
                //把文件名称设置成唯一值，防止名称相同覆盖
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName = uuid + "_" + fileName;
                //完成文件上传
                item.write(new File(path,fileName));
                //删除临时文件
            }
        }

        return "upload_success";
    }

    /**
     * springmvc文件上传
     * springmvc已经将解析工作通过配置文件配置解析器将request做解析，可以通过参数绑定直接拿到请求中的上传文件项
     * 方法参数名字必须与表单name属性值一致
     * @return
     */
    @RequestMapping("/springmvcFileUpload")
    public String springmvcFileUpload(HttpServletRequest request, MultipartFile file) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/uploads");
        File uploadFile = new File(path);
        if (! uploadFile.exists()){
            uploadFile.mkdirs();
        }
        //获取文件名称
        String filename = file.getOriginalFilename();
        //将文件名称设置成唯一值，防止同名覆盖
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + "_" + filename;
        //完成文件上传
        file.transferTo(new File(path, filename));
        return "upload_success";
    }
}
