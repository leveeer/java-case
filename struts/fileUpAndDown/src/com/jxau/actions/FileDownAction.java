package com.jxau.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;


/**
 * @author xie
 */
public class FileDownAction extends DownloadAction {
    @Override
    public StreamInfo getStreamInfo(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse response) throws Exception {

        //通过ServletContext对象来获取要下载的文件的真实路径
        String path = request.getSession().getServletContext().getRealPath("images/1.jpg");

        //通过真实路径构建一个File对象
        File file = new File(path);
        //设置好response的一些头和文件的文件名
        response.setHeader("content-disposition", "attachment;filename="+file.getName());
        //通过返回FileStreamInfo对象来把数据发送给浏览器
        return new DownloadAction.FileStreamInfo("image/jpg", file);
    }
}
