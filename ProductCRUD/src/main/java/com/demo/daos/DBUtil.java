package com.demo.daos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
 public static final Properties props=new Properties();
 private static volatile boolean initialized=false;
 private static synchronized void init() {
	 if(initialized) return;
	 try {
		 InputStream in=DBUtil.class.getResourceAsStream("/com/demo/resources/db.properties");
		 if(in ==null) {
			 throw new IllegalStateException("Db.properties file jot found");
		 }
		 try (InputStream is=in){
			 props.load(is);
		 }
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 }catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBC DRiver not found",e);
		}
		 initialized=true;
	 }catch (IOException e) {
		throw new IllegalStateException("failed to load db.prop file",e);
	}
 }
 public static Connection getConnection() throws SQLException{
	 if(initialized) {
		 init();
	 }
	 String url=props.getProperty("db.url");
	 String user=props.getProperty("db.username");
	 String pass=props.getProperty("db.password");
	 if(url==null||user==null)
		 throw new SQLException("Problem in db file");
	 return DriverManager.getConnection(url,user,pass);
 }
 
}
