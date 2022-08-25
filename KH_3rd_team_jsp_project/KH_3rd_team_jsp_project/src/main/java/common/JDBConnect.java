package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	// 기본 생성자 
	public JDBConnect() {
		try {
			// JDBC 드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");
			
			// DB에 연결
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			// 오라클의 프로토콜 + 호스트명 + 포트명 + SID
			
			String id = "webmarket";
			String pwd= "1234";
			con = DriverManager.getConnection(url, id, pwd);
			
			// System.out.println("DB 연결 성공(기본 생성자)");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 두번째 생성자
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			// JDBC 드라이버 로드
			Class.forName(driver);
			
			// DB에 연결
			con = DriverManager.getConnection(url,id,pwd);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 세번째 생성자
	public JDBConnect(ServletContext application) {
		try {
			// JDBC 드라이버 로드
			String driver = application.getInitParameter("OracleDriver");
			Class.forName(driver);
			
			// DB에 연결
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pwd = application.getInitParameter("OraclePwd");
			con = DriverManager.getConnection(url,id,pwd);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 연결해제(자원반납)
	public void close() {
		try {
			if(rs!= null) rs.close();
			if(stmt !=null) stmt.close();
			if(psmt!= null) psmt.close();
			if(con !=null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
