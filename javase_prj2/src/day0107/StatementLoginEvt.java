package day0107;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class StatementLoginEvt extends WindowAdapter implements ActionListener{

	private StatementLogin sl;
	
	public StatementLoginEvt(StatementLogin sl) {
		this.sl=sl;//has a 관계
	}//StatementLoginEvt

	@Override
	public void windowClosing(WindowEvent we) {
		sl.dispose();
	}//windowClosing
	
	public boolean chkNull(String id, String pass) {
		boolean flag=false;
		
		try {
			if(id.equals("")) {
				JOptionPane.showMessageDialog(sl, "아이디는 필수 입력");
				sl.getJtfId().requestFocus();
				flag= true;//아래쪽으로 흘러가지 않게...?
				//예외를 강제로 발생시켜 아이디와 비밀번호가 없을때 두개의 창이뜨는것을 막았다.
				//예외를 강제로 발생!: throw new 예외처리 클래스명();
					throw new LoginException();
			}//end if
			if(pass.equals("")) {
				JOptionPane.showMessageDialog(sl, "비밀번호는 필수 입력");
				sl.getJpfPass().requestFocus();
				flag= true;//아래쪽으로 흘러가지 않게...?
			}//end if
		} catch (LoginException le) {
//			le.printStackTrace();
		}//end catch
		
		return !flag;//없다면 false가 나갈것.return은 한번에 처리하는게 잘한 코드.
	}//chkNull
	
	public String login(String id, String pass) throws SQLException{
		String name="";
		
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
		//2.Connection 얻기
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String dbo_id="scott";
			String dbo_pass="tiger";//꼭 외워준다~!~!!!!
			con=DriverManager.getConnection(url, dbo_id, dbo_pass);
		//3.쿼리문 생성 객체얻기
			stmt=con.createStatement();
		//4.쿼리 수행후 결과 얻기
			StringBuilder selectName=new StringBuilder();
			selectName
			.append(" select name ")
			.append(" from test_login ")
			.append(" where id='").append(blockSqlInjection(id)).append("' and ")
			.append(" pass='").append(blockSqlInjection(pass)).append("'");
			
			rs=stmt.executeQuery(selectName.toString());
			if(rs.next()) {//입력된 id와 pass에 맞는 이름이 존재 => 로그인 성공
				name=rs.getString("name");
			}//end if
		}finally {
			//5.연결 끊기
			if(rs!=null) {rs.close();}//end if
			if(stmt!=null) {stmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
		
		return name;
	}//login
	
	public String blockSqlInjection(String data) {
		return data.replace(" ", "").replace("'", "").replaceAll("--", "");
	}//blockSqlInjection
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==sl.getJtfId() || ae.getSource()==sl.getJpfPass() || ae.getSource()==sl.getJbtLogin()) {
			
			String id=sl.getJtfId().getText().trim();
			String pass=new String(sl.getJpfPass().getPassword()).trim();
			
			if(chkNull(id,pass)) {
				
				try {
					String name=login(id, pass);
					
					if(name.isEmpty()) {
						JOptionPane.showMessageDialog(sl, "아이디나 비밀번호를 확인해 주세요.");
						sl.getJtfId().setText("");
						sl.getJpfPass().setText("");
						sl.getJtfId().requestFocus();
						return;
					}//end if
					
//					JOptionPane.showMessageDialog(sl, name+"님 로그인 하셨습니다.");
					JDialog jd=new JDialog(sl, "로그인 성공", true);
					jd.add(new JLabel(name+"님 로그인 하셨습니다."));
					jd.setBounds(sl.getX()+100, sl.getY()+100, 300, 200);
					jd.setVisible(true);
					jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);//exit_on~아님  왤까
					
				} catch (SQLException se) {
					JOptionPane.showMessageDialog(sl, "DB작업 중 문제가 발생하였습니다.");
					se.printStackTrace();
				}//end catch
			}//end if
		}//end if
		
		if(ae.getSource()==sl.getJbtCancel()) {
			sl.getJtfId().setText("");
			sl.getJpfPass().setText("");
			sl.getJtfId().requestFocus();
		}//end if
	}//actionPerformed
	
}//class