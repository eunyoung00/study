package day0111;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class BtnMsg extends JFrame implements ActionListener {
	
	private JButton[] jbtNames;
	private String[] names;
	private String[] msg;
	
	public BtnMsg() {
		super("이름을 클릭해주세요.");
		
		names=new String[]{"김정윤","박영민","노진경","김희철","김정운","이재찬","이재현","공선의","김건하","오영근"};
		msg=new String[]{"서울은 춥다.","각자 해 보세요","담배피러 가자","집에 가고싶다..","재찬이가 좋아요","조퇴하고 싶다..",
						"형!제가 캐리할께요","수영하고 싶다","점심먹으러 가자","주말에 공부하세요"};
		
		setLayout(new GridLayout(5, 2));
		jbtNames=new JButton[10];//<객체 생성만되어 null이 들어가 있다.
		for(int i=0;i<jbtNames.length;i++) {
			jbtNames[i]=new JButton(names[i]);//생성
			jbtNames[i].addActionListener(this);//이벤트 등록
			add(jbtNames[i]);//배치
		}//end for
		
//		setLayout(new GridLayout(5, 2));
		
		for(int i=0;i<jbtNames.length;i++) {
			//배치 때에 따라 떨어져 써야할때가 있어 떨어져 쓰지만 지금은 한번에 사용해도 된다. 단 그리드 레이아웃을 위로 올리고!
		}//end for
		
		setBounds(100, 100, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//BtnMsg

	@Override
	public void actionPerformed(ActionEvent ae) {
		for(int i=0; i<jbtNames.length; i++) {
			if(ae.getSource()==jbtNames[i]) {
				JOptionPane.showMessageDialog(this, msg[i]);
				break;//<굳이 뒤에오는것들도 비교할 필요가 없으니 나가준다.
			}//end if
		}//end for
	}//actionPerformed

	public static void main(String[] args) {
		new BtnMsg();
	}//main
}//class
