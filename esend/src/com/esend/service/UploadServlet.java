package com.esend.service;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
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
@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet{

    
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
        	  //��ȡ�ļ�������Ϣ
          
            //��ȡ�ϴ����ļ�
            Part part=request.getPart("file");
            //��ȡ�������Ϣ
            String name=part.getHeader("content-disposition");
            String fileName = name.substring(name.lastIndexOf("=") + 2, name.length() - 1);
            //System.out.println(fileName);//����ʹ��
            //System.out.println(desc);//
            
            //��ȡ�ϴ��ļ���Ŀ¼
            String root=request.getServletContext().getRealPath("/upload");
            
            System.out.println("�����ϴ��ļ���·����"+root);
            
            //��ȡ�ļ��ĺ�׺
            String str=name.substring(name.lastIndexOf("."), name.length()-1);
           // System.out.println("���Ի�ȡ�ļ��ĺ�׺��"+str);
            int rand=(int) ((Math.random()*9+1)*1000);
            String idString=String.valueOf(rand);
            //����һ���µ��ļ��������ظ������ݿ�洢�ľ�������ļ��������ظ���
            String filename=root+"\\"+String.valueOf(rand)+str;
            System.out.println("���Բ����µ��ļ�����"+filename);  
            //�ϴ��ļ���ָ��Ŀ¼�������ϴ��ļ��Ͳ��������
            part.write(filename);	
            FIle fIle=new FIle();
            fIle.setFid(idString);
            fIle.setFname(fileName);
            fIle.setRoot(str);
            System.out.println(fIle.getFid()+"id");
            System.out.println(fIle.getFname()+"name");
            System.out.println(fIle.getRoot()+"root");
            FIleDAO fIleDAO=new FIleDAO();
            fIleDAO.addFile(fIle);
            
            request.setAttribute("info", "�ϴ��ļ��ɹ���ȡ��Ϊ"+rand);
           
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("info", "�ϴ��ļ�ʧ��");
        }
        
        request.getRequestDispatcher("/upload.jsp").forward(request, response);
    }
    
}