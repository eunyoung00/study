package day1212;


import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;


/**
 * Has a 관계로 Event 처리
 * @author owner
 */

@SuppressWarnings("serial")
public class FriendsList extends Frame {
	
	private List listFriend,listBlockFriend;
	private Button btnAllBlock,btnAllUnBlock;
	
	public FriendsList() {
		super("친구관리");
		
		Label lbl1 = new Label("친구 목록");
		Label lbl2 = new Label("차단된 친구");
		
		listFriend = new List();
		listBlockFriend = new List();
		listFriend.add("이재찬");
		listFriend.add("이재현");
		listFriend.add("정택성");
		
		btnAllBlock = new Button(">>");
		btnAllUnBlock = new Button("<<");
		
		setLayout(null);
		lbl1.setBounds(80, 50, 150, 50);
		lbl2.setBounds(355, 50, 150, 50);
		listFriend.setBounds(30, 100, 150, 200);
		listBlockFriend.setBounds(320, 100, 150, 200);
		btnAllBlock.setBounds(225, 150, 50, 30);
		btnAllUnBlock.setBounds(225, 220, 50, 30);

		add(lbl1);
		add(lbl2);
		add(listFriend);
		add(listBlockFriend);
		add(btnAllBlock);
		add(btnAllUnBlock);
		
		//이벤트 등록
		//디자인 클래스와 이벤트 처리 클래스는 has a 관계
		FriendsListEvt fle=new FriendsListEvt(this);
		btnAllBlock.addActionListener(fle);
		btnAllUnBlock.addActionListener(fle);
		
		listBlockFriend.addItemListener(fle);
		listFriend.addItemListener(fle);
		addWindowListener(fle);//<종료해주던 부분		
		
		setBounds(200, 200, 500, 400);
		setResizable(false);
		
		setVisible(true);
	}//FriendsList
	
//alt+shift+s=>generate getter~
	public List getListFriend() {
		return listFriend;
	}

	public List getListBlockFriend() {
		return listBlockFriend;
	}

	public Button getBtnAllBlock() {
		return btnAllBlock;
	}

	public Button getBtnAllUnBlock() {
		return btnAllUnBlock;
	}

	public static void main(String[] args) {
		new FriendsList();
	}//main
}//class
