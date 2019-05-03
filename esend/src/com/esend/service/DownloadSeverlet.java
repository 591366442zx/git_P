package com.esend.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.esend.Bead.FIle;
import com.esend.dao.FIleDAO;

/** 
* @author BieHongLi 
* @version ����ʱ�䣺2017��3��4�� ����5:29:03 
* ע�⣺�ϴ��ļ��������@MultipartConfig()���������ϴ��ļ��Ĵ�С
*/
@WebServlet("/down")
@MultipartConfig
public class DownloadSeverlet extends HttpServlet{

    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	String fid = request.getParameter("fid"); //�ͻ��˴��ݵ���Ҫ���ص��ļ���
        System.out.println("fid is "+fid);
        try {
        	  //��ȡ�ļ�������Ϣ
          
            //��ȡ�ϴ����ļ�
           FIleDAO fIleDAO=new FIleDAO();
           String root=fIleDAO.SerchFile(fid);
           System.out.println("root is"+root);
           
           String path = request.getServletContext().getRealPath("/upload")+"\\"+fid+root; //Ĭ����Ϊ�ļ��ڵ�ǰ��Ŀ�ĸ�Ŀ¼
           System.out.println(path);
           FileInputStream fis = new FileInputStream(path);
           response.setCharacterEncoding("utf-8");
           response.setHeader("Content-Disposition", "attachment; filename="+fid+root);
           ServletOutputStream out = response.getOutputStream();
          byte[] bt = new byte[1024];
          int length = 0;
          while((length=fis.read(bt))!=-1){
               out.write(bt,0,length);
         }
           out.close();
        	
        
           request.setAttribute("info", "���سɹ�");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("info", "�����ļ�ʧ��");
        }
        
        request.getRequestDispatcher("/down.jsp").forward(request, response);
    }
    
}