package com.esend.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
 
 
public class DBUtil {
   //ע�������Ĺ�����
	
	private static String url = "jdbc:mysql://localhost:3306/esend?useSSL=false";
	
	private static String user = "root";
	
	private static String  password = "1998";
	
	private static String  dv = "com.mysql.jdbc.Driver";
	
	static{
		
	  
	   
	   
	   
	   
	
		
		
		//ע������
		  try {
			Class.forName(dv);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
			
	
	
	
 
	//��ȡ���Ӷ���
	public static  Connection getCon() throws SQLException{
		 Connection conn = null;
		 
		 conn = (Connection) DriverManager.getConnection(url, user, password);
		 
		 return  conn;
	}
	
	//�رյķ���
	public static void close(Statement statement,Connection conn){
		   if(statement !=null){
			   try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		   
		   if(conn !=null){
			   try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	}
		
	//�رյķ���
		public static void close(PreparedStatement preparedStatement,Connection conn,ResultSet resultSet){
			   if(preparedStatement !=null){
				   try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
			   
			   if(conn !=null){
				   try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   }
			   
			   if(resultSet!=null){
				   try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
	}
}
