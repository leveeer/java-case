package com.jxau.actions;

import com.jxau.forms.FileForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;

/**
 * @author xie
 */
public class FileUpAction extends Action {
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        FileForm fileForm = (FileForm) form;
        String fileName = fileForm.getFileName();
        FormFile file = fileForm.getFile();

        if (file != null){
            FileOutputStream fos = new FileOutputStream("C:\\Users\\xie\\Desktop" + file.getFileName());
            fos.write(file.getFileData());
            fos.flush();
            fos.close();
        }
        return mapping.findForward("fileDown");
    }
}
