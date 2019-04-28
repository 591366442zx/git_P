package com.esend.dao;

import com.esend.Bead.User;
import com.esend.Utils.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
 

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserDAO {
	//���ݿ����Ӷ���
		public  User  login(String username,String password) {
		   User u=null;
		   Connection connection =null;
		   PreparedStatement pstmt=null;
		   ResultSet resultSet=null;
	 
		  //��ֵ
		  try {
			connection=DBUtil.getCon();
			  //��̬sql���
			String sql = "select * from user where name=? and password=?"; 
		    pstmt = (PreparedStatement) connection.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);  
			resultSet = pstmt.executeQuery();
			if(resultSet.next()){
				u=new User();
				u.setUsername(resultSet.getString("name"));
				u.setPassword(resultSet.getString("password"));
				System.out.println("��¼�ɹ���");
			}else{
				System.out.println("�û��������������");
			}  
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
		   DBUtil.close(pstmt, connection);
		}
		 return u;
	 
	}
		public void addUser(User user) {
			Connection connection = null;
			PreparedStatement psmt = null;
			try {
				 connection =DBUtil.getCon();
				 
				 String sql  ="insert into user(id,name,password,role)values(?,?,?,?);";
				 
				 psmt = (PreparedStatement) connection.prepareStatement(sql);
				 
				 //����ʵ�������в�����ֵ
				 psmt.setInt(1, user.getId());
				 psmt.setString(2, user.getUsername());
				 psmt.setString(3,user.getPassword());
				 psmt.executeUpdate();		 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				 DBUtil.close(psmt, connection);
			}
		}	
}



