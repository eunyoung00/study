package kr.co.sist.lunch.admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import kr.co.sist.lunch.admin.model.LunchAdminDAO;
import kr.co.sist.lunch.admin.view.LunchAddView;
import kr.co.sist.lunch.admin.view.LunchDetailView;
import kr.co.sist.lunch.admin.view.LunchMainView;
import kr.co.sist.lunch.amdin.vo.CalcVO;
import kr.co.sist.lunch.amdin.vo.LunchDetailVO;
import kr.co.sist.lunch.amdin.vo.LunchVO;
import kr.co.sist.lunch.amdin.vo.OrderVO;

public class LunchMainController extends WindowAdapter implements ActionListener,MouseListener{

	private LunchMainView lmv;
	private LunchAdminDAO la_dao;
	
	public static final int DBL_CLICK=2;
	
	private String orderNum;
	private String lunchName;
	private int selectedRow;
	
	public LunchMainController(LunchMainView lmv) {
		this.lmv=lmv;
		la_dao=LunchAdminDAO.getInstance();
		//도시락 목록을 초기화-설정 한다.
		setLunch();
		orderNum="";
	}//LunchMainController
	
	/**
	 * JTable에 DB에서 조회한 도시락 정보를 보여준다.
	 */
	public void setLunch() {//사용범위를 고려햐여 밖에서 쓰이지 않으면 private
		DefaultTableModel dtmLunch=lmv.getDtmLunch();
		dtmLunch.setRowCount(0);//있던 row4개를 싹다 지워주고
		
		try {
			//DB에서 도시락 정보를 조회
			List<LunchVO> listLunch=la_dao.selectLunch();
			//JTable에 조회한 정보를 출력
			
			LunchVO lv=null;
			String imgPath="C:/dev/workspace/lunch_prj/src/kr/co/sist/lunch/admin/img/s_";
			
			Object[] rowData=null;
			for(int i=0; i<listLunch.size(); i++) {
				lv=listLunch.get(i);
				
				//<dtm에 넣을떄에는 1차원 배열이나 vector로만 넣을수있다.
				//DTM에 데이터를 추가하기 위한 일차원배열(or Vector)를 생성하고 데이터를 추가
				rowData=new Object[5];
				rowData[0]=new Integer(i+1);//<기본형이 오브젝으로 들어감으로 int를 객체로 만들어 넣어준다.
				rowData[1]=lv.getLunchCode();
				rowData[2]=new ImageIcon(imgPath+lv.getLunchImg());
				rowData[3]=lv.getLunchName();
				rowData[4]=new Integer(lv.getPrice());//<aotoBoxing으로 잘 들어가지만 객체로 포장?해준다.
				
				//DTM에 추가
				dtmLunch.addRow(rowData);
				
			}//end for
			
			if(listLunch.isEmpty()) {//입력된 도시락이 없을 때 
				JOptionPane.showMessageDialog(lmv, "입력된 제품이 없습니다.");
			}//end if
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(lmv, "DB에서 데이터를 받아오는 중에 문제가 발생했습니다.");
			e.printStackTrace();
		}//end catch
		
	}//setLunch
	
	@Override
	public void windowClosing(WindowEvent we) {
		lmv.dispose();
	}//windowClosing
	
	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(0);//JVM의 모든 인스턴스 종료
	}//windowClosed
	
	private void searchOrder() {//이메소드가 thread로 돌아야 한다. 원랜
		try {
			List<OrderVO> list=la_dao.selectOrderList();
			DefaultTableModel dtm=lmv.getDtmOrder();
			dtm.setRowCount(0);//초기화
			
			Vector<Object> vec=null;
			OrderVO ovo=null;
			for(int i=0;i<list.size(); i++) {
				ovo=list.get(i);
				vec=new Vector<Object>();
				vec.add(new Integer(i+1));
				vec.add(ovo.getOrderNum());
				vec.add(ovo.getLunchCode());
				vec.add(ovo.getLunchName());
				vec.add(ovo.getOrderName());
				vec.add(ovo.getQuan());
				vec.add(ovo.getPrice());
				vec.add(ovo.getorderDate());
				vec.add(ovo.getPhone());
				vec.add(ovo.getIpAddress());
				vec.add(ovo.getStatus());
				
				//추가
				dtm.addRow(vec);
				
			}//end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//searchOrder
	
	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getSource()==lmv.getJtb()) {
			if(lmv.getJtb().getSelectedIndex()==1) {//처음탭에서 이벤트 발생
//				System.out.println("이벤트발생발생~~~");
				//현제까지의 주문사항을 조회 (Thread로 돌려야 한다)
				searchOrder();
			}//end if
		}//end if
		if(me.getSource()==lmv.getJtOrder() && me.getButton()==MouseEvent.BUTTON3) {//우클릭?
			JTable jt=lmv.getJtOrder();
			//마우스 포인터가 클릭되면 테이블에서 클릭한 행을 가져오는 일
			int r = jt.rowAtPoint(me.getPoint());//위치뽑아 행가져와
		        if (r >= 0 && r < jt.getRowCount()) {
		        	jt.setRowSelectionInterval(r, r);//시작행과 끝행 사이의 행을 선택하는 일(시작과 끝이 같은게선택되어 그행이 선택되는)
		        } else {
		        	jt.clearSelection();
		        }//end else
		        //선택한 행을 넣는다.
		        selectedRow=r;
//			System.out.println("아오 우클릭"+me.getX()+"/"+me.getY());
			JPopupMenu jp=lmv.getJpOrderMenu();
			jp.setLocation(me.getXOnScreen(),me.getYOnScreen());
//			jp.setBounds(me.getXOnScreen(),me.getYOnScreen(),100,100);
			jp.setVisible(true);
			
			orderNum=(String)jt.getValueAt(jt.getSelectedRow(), 1);//우클릭이 판정이 안돼서...
//			System.out.println(jt.getValueAt(jt.getSelectedRow(), 1));
//			System.out.println(jt.getSelectedRow());
			lunchName=(String)jt.getValueAt(jt.getSelectedRow(), 3)+" "+
						(String)jt.getValueAt(jt.getSelectedRow(), 4);
		}else {
			JPopupMenu jp= lmv.getJpOrderMenu();
			jp.setVisible(false);
			
		}//end else
//		System.out.println(me.getClickCount());//클릭횟수
//		if(me.getClickCount()==DBL_CLICK/*2를 써도 되나 상수로 선언해 사용하면 가독성up*/) {
		switch(me.getClickCount()) {
		case DBL_CLICK:
//			System.out.println("더블 클릭!!");
			if(me.getSource()==lmv.getJtLunch()) {//도시락테이블에서 더블클릭
				//도시락 코드로 DB Table을 검색해 상세정보를 전달한다.
				JTable jt=lmv.getJtLunch();
//				System.out.println(jt.getValueAt(jt.getSelectedRow(), 1));//코드 출력
				try {
					LunchDetailVO ldvo=la_dao.selectDetailLunch((String)jt.getValueAt(jt.getSelectedRow(), 1));
					new LunchDetailView(lmv,ldvo,this);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(lmv, "DB에서 문제가 발생했습니다.");
					e.printStackTrace();
				}//end catch
			}//end if
		}//end switch
	}//mouseClicked
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==lmv.getJbtAddLunch()) {//도시락 추가버튼
			new LunchAddView(lmv,this);
		}//end if

		if(ae.getSource()==lmv.getJcbMonth()) {//월별 마지막 일자 변경
			setDay();
		}//end if
		
		if(ae.getSource()==lmv.getJbtCalcOrder()) {//정산 버튼 클릭
			searchCalc();
		}//end if
		
		if(ae.getSource()==lmv.getJmOrderRemove()) {
//			JOptionPane.showConfirmDialog(lmv, "정말 삭제인 부분");
		}//end if
		if(ae.getSource()==lmv.getJmOrderStatus()) {
			switch(JOptionPane.showConfirmDialog(lmv, "["+orderNum+lunchName+"] 주문이 완료었습니까?")) {
			case JOptionPane.OK_OPTION : 
				JTable jt=lmv.getJtOrder();
				jt.setValueAt("Y", selectedRow, 10);//화면만 바꿈!DB는 아님!
				//테이블의 값만 변경
////////////////////////////////////////////////////////////////////////////////
/////////////////////01-17-2019 : 주문변경은 내일 ~!~!~!~~!`
////////////////////////////////////////////////////////////////////////////////
			}//end switch
		}//end if
	}//actionPerformed

	/**
	 * 년,월,일 정보를 가져와서 정산 
	 */
	private void searchCalc() {
		int selYear=((Integer)lmv.getJcbYear().getSelectedItem()).intValue();
		int selMonth=(Integer)lmv.getJcbMonth().getSelectedItem();
		int selDay=(Integer)lmv.getJcbDay().getSelectedItem();
		
		StringBuilder searchDate=new StringBuilder();
		searchDate.append(selYear).append("-")
		.append(selMonth).append("-").append(selDay);
		
//		System.out.println(searchDate);
		try {
			//선택한일자의 조회결과를 받아서 JTable에 출력
			List<CalcVO> list =la_dao.selectCalc(searchDate.toString());
//			System.out.println(list);
			//JTable에 데이터를 추가하는 코드 작성

//	        String[] row = new String[list.size()];
//	        list.toArray(row);
//			for(int i=0; i<list.size(); i++) {
//				String[] comma=row[i].split(",");
////				CalcVO cvo=new CalcVO(lunchCode, lunchName, price, total)
//				lmv.getDtmCalc().addRow(comma[i]);
//			}//end for
			DefaultTableModel dtm=lmv.getDtmCalc();
			if(!list.isEmpty()) {
				dtm.setRowCount(0);
			}//end if
			Object[] rowData=null;
			CalcVO cv=null;
			
			for(int i=0; i<list.size(); i++) {
				cv=list.get(i);
				rowData=new Object[4];
				rowData[0]=new Integer(i+1);
				rowData[1]=cv.getLunchName()+"("+cv.getLunchCode()+")";
				rowData[2]=new Integer(cv.getTotal());
				rowData[3]=new Integer(cv.getPrice());
				dtm.addRow(rowData);
				//DTM에 넣을때에는 1차원 배열혹은 벡터로 넣어야함. 추가하기위한 배열을 생성해 추가.
			}//end for
			
			//데이터가 없는 날에는 "판매된 도시락이 없습니다" 를 출력
			if(list.isEmpty()) {
				dtm.setRowCount(0);
				JOptionPane.showMessageDialog(lmv, searchDate.toString()+"에 판매된 도시락이 없습니다.");
			}//end if
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
	}//searchCalc

	/**
	 * 월이 선택되면 해당년의 해당월의 마지막날을 설정.
	 */
	private void setDay() {
		int selYear=((Integer)lmv.getJcbYear().getSelectedItem()).intValue();
		int selMonth=(Integer)lmv.getJcbMonth().getSelectedItem();
		
		//마지막날 얻기
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, selYear);
		cal.set(Calendar.MONTH, selMonth-1);
		
		int lastDay=cal.getActualMaximum(Calendar.DATE);
		int nowDay=cal.get(Calendar.DAY_OF_MONTH);

		lmv.getCbmDay().removeAllElements();//모델을 초기화 하고
		for(int day=1; day<lastDay+1; day++) {
			lmv.getCbmDay().addElement(day);//마지막날을 설정한다.
		}//end for
		
//		lmv.getCbmDay().setSelectedItem(new Integer(nowDay));//그리고 오늘을 선택한다.
		//<기본형을 객체로 변환하여 넣어주는것auto Boxing되어서 들어가나 변환해준거당..
		
	}//setDay

	
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}//class
