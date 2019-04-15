package kr.co.sist.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.sist.domain.Notice;

public class MyBatisDAO {
	
	private static MyBatisDAO mb_dao;
	private SqlSessionFactory ssf=null;
	
	private MyBatisDAO() {
		
	}//MyBatisDAO
	
	public static MyBatisDAO getInstance() {
		if(mb_dao==null) {
			mb_dao=new MyBatisDAO();
		}//end if
		return mb_dao;
	}//getInstance
	
	public synchronized SqlSessionFactory getSessionFactory() {
		if(ssf==null) {
			org.apache.ibatis.logging.LogFactory.useLog4JLogging();
			
			Reader reader=null;
			try {
				//1.설정용 xml 로딩
				reader=Resources.getResourceAsReader("kr/co/sist/dao/mybatis_config.xml");
				//2.MyBatis Framework 생성
				SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
				//3.DB와 연동된 객체 받기
				ssf=ssfb.build(reader);
				if(reader!=null) {reader.close();}//end if
				
			} catch (IOException e) {
				e.printStackTrace();
			}//end catch
		}//end if
		return ssf;
	}//getSessionFactory
	
	public List<Notice> selectMainNotice(){
		SqlSession ss=getSessionFactory().openSession();
		List<Notice> list=ss.selectList("noticeList");
		
		return list;
	}//selectMainNotice
	
	
/*	public static void main(String[] args) {
		MyBatisDAO m=MyBatisDAO.getInstance();
		m.selectMainNotice();
	}//main
*/	
}//class
