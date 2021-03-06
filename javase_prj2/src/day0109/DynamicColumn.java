package day0109;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import kr.co.sist.connection.GetConnection;

/**
 * 컬럼명이 동적으로 변경되는 경우 <br>
 * 컬럼명을 입력받아 해당 컬럼명으로 조회<br>
 * EMP테이블을 조회-사원번호와 컬럼명(을 입력받아 조회<br>
 * joptionpane으로 String을 받아 bind변수로 쿼리문에 넣어 조회
 * @author owner
 */
public class DynamicColumn {
	
	public DynamicColumn() throws SQLException{
		Connection con =null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String[] columnName= {"ename","job","mgr","hiredate","sal","comm","deptno"};
		//<db에 맞추어 대문자로 하는게 좋으나 에러가 잘보이지 않으므로 소문자로 입력해 두었다가 대문자로 변환시켜 이용하기도 한다.

		String input=JOptionPane.showInputDialog("사원번호나 컬럼명 하나를 입력해 주세요.\n예)사원번호, 컬럼명");//CSV컴마세퍼레이티드value
		String [] temp=input.split(",");
		
		if(temp.length!=2) {
			JOptionPane.showMessageDialog(null, "입력형식을 확인하세요.");
			return;
		}//end if
		
		try {
		int empno=Integer.parseInt(temp[0].trim());
		String inputColumn=temp[1].trim();
		
		boolean columnFlag=false;
		for(String column : columnName) {
			if(column.equals(inputColumn.toLowerCase())) {//DB의 컬럼명과 같은 컬럼명이라면 다 소문자로 처리하여 받아들임
				columnFlag=true;
			}//end if
		}//end for
		
		if(!columnFlag) {
			JOptionPane.showMessageDialog(null, inputColumn+"은 EMP테이블에 컬럼으로 존재하지 않습니다.");
			//<테이블이 특정지어졌기 때문에 그냥 컬럼명을 넣어도 괜춘
			return;
		}//end if
		
		try {
		//1.
		//2.
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String id="scott";
			String pass="tiger";
			con=GetConnection.getInstance().getConn(url, id, pass);
			//<시간이줄어 빠르게 쓸수 있다.이 생각을 db에 대입함=> DBCP?(생성->사용)
		//3.
			//컬럼명이 hiredate인 경우 문자열로 처리하기 위해 to_char 함수 사용.
			//<hiredate인경우 tochar로 나오는게 좋겠다.
			if(inputColumn.equals("hiredate")){
				inputColumn="to_char(hiredate,'yyyy-mm-dd day hh24:mi:ss') hiredate";
			}//end if
			
			StringBuilder selectEmp=new StringBuilder();
			//컬럼명, 테이블명은 bind 변수로 처리되지 않는다.쿼리문에 직접넣어 사용한다.
			selectEmp.append("select ").append(inputColumn/*?아니고*/).append(" from emp ")
//			selectEmp.append("select ? from emp ")
			.append(" where empno=?");
			
			pstmt=con.prepareStatement(selectEmp.toString());
		//4.
//			//<bind변수가 컬럼명자리에 쓰여지게 되면 인식을 못한다.Error부적합한 열(컬럼) 이름
//			pstmt.setString(1, inputColumn);
//			pstmt.setInt(2, empno);
			pstmt.setInt(1, empno);
		//5.
			rs=pstmt.executeQuery();
			boolean f=rs.next();
			System.out.println( f +" " +selectEmp+ " "+ inputColumn);
		
			
			if(f) {//사원번호로 조회된 레코드가 존재한다면

				String stringData="";
				int intData=0;
				if(temp[1].trim().equals("ename")||temp[1].trim().equals("job")||
						temp[1].trim().equals("hiredate")) {
//					stringData=rs.getString(1);//같게쓰이나 첫번째컬럼이 무조건 있는것? 부적합한 열 이름 에러가 안걸러진다..
					stringData=rs.getString(temp[1].trim());
					
				}else {
					intData=rs.getInt(temp[1].trim());
				}//end else
				JOptionPane.showMessageDialog(null,temp[1]+"으로 조회된 값 : "+(intData==0? stringData:intData));
			}else {
				JOptionPane.showMessageDialog(null,"입력한 사원번호는 존재하지 않습니다.");
			}//end else
		}finally {
			//6.
			if(rs!=null) {rs.close();}//end if
			if(pstmt!=null) {pstmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
		
		}catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "사원번호는 숫자이어야 합니다.");
		}//end catch
	}//DynamicColumn

	public static void main(String[] args) {
		try {
			new DynamicColumn();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//main

}//class
