package day1214;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import kr.co.sist.memo.view.JavaMemo;

/**
 * TitledBorder)
 * @author owner
 */
@SuppressWarnings("serial")
public class UseTitledBorder extends JFrame implements ActionListener {
	
	private JTextField jtfId;
	private JPasswordField jpfPass;
	private JLabel jlOutput;
	
	public UseTitledBorder() {
		super("border税 紫遂");
		
		jtfId =new JTextField();
		jpfPass =new JPasswordField();
		jlOutput =new JLabel("衣引 : ");
		
		//析鋼陳匂獲闘
		jtfId.setBorder(new TitledBorder("焼戚巨"));
		jpfPass.setBorder(new TitledBorder("搾腔腰硲"));
		jlOutput.setBorder(new TitledBorder("窒径但"));
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 1));
		
		//珍砺戚格 陳匂獲闘
		panel.setBorder(new TitledBorder("稽益昔"));
		
		panel.add(jtfId);
		panel.add(jpfPass);
		panel.add(jlOutput);
		
		//戚坤闘 去系
		jtfId.addActionListener(this);
		jpfPass.addActionListener(this);
		
		add("Center",panel);
		
		setBounds(100, 100, 250, 300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//UseTitledBorder
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String id= jtfId.getText().trim();
		String pass= new String(jpfPass.getPassword());
		
		if(ae.getSource()==jtfId) {//焼戚巨拭辞 戚坤闘亜 降持馬檎~
			//焼戚巨拭 葵戚 級嬢赤陥檎 朕辞研 搾腔腰硲稽 戚疑
			
			if(id.equals("")) {
				jtfId.requestFocus();
				jlOutput.setText("焼戚巨研 脊径背爽室推.");
				return;//鎧形亜走源壱 亜獄形!
				//鋼発莫戚 左戚球昔 五社球拭辞 坪球税 叔楳聖 誇蓄壱 硲窒廃 員生稽 宜焼亜虞~!
				//奄糎税 軒渡引澗 葵鋼発生稽 陥牽陥. 戚軒渡精 葵聖 宜形爽澗惟 焼艦虞 坪球税 叔楳聖 誇茶
			}//end if
			if(!id.equals("")) {
				jpfPass.requestFocus();
			}//end if
			//蟹たたたたたたたたた
//			if(jtfId.getText()!=null) {
//				jpfPass.requestFocus();
//			}//end if
		}//end if
		if(ae.getSource()==jpfPass) {//搾腔腰硲拭辞 戚坤闘亜 降持馬檎~
			//焼戚巨拭 葵戚 蒸陥檎 朕辞研 焼戚巨稽 戚疑.
			//搾腔腰硲拭 葵戚 蒸陥檎 窒径但拭 "搾腰 脊径"聖 左食 爽壱, 朕辞研 搾腔腰硲拭 戚疑
			//搾腔腰硲拭 葵戚 赤陥檎 焼戚巨亜 admin, 搾腔腰硲亜 123引 旭精走 搾嘘馬食
			//旭陥檎 切郊 五乞舌 適掘什研 叔楳.
			//getText澗 床走源虞壱 廃陥.
//			String pass= jpfPass.getPassword();//Error照級嬢娃陥.
/*			String pass= new String(jpfPass.getPassword());*/
			if(pass.trim().equals("")) {
//				jtfId.requestFocus();
				jlOutput.setText("搾腔腰硲研 脊径背爽室推.");
				return;
			}//end if
			
			if(id.equals("admin")&&pass.equals("123")) {
//				new JavaMemo();
				dispose();
			}else {
				jlOutput.setText("焼戚巨蟹 搾腔腰硲研 溌昔背 爽室推.");
			}
			
			//蟹たたたたたたたたた
//			if(jtfId.getText()==null) {
//				jtfId.requestFocus();
//			}//end if
//			char[] passchar =jpfPass.getPassword();
//			String passstr = new String(passchar);
//			if(passchar==null) {
//				jlOutput.setText("搾腰 脊径");
//				jpfPass.requestFocus();
//			}else if(jtfId.getText().equals("admin")&&passstr.equals("123")) {
//				jlOutput.setText("焼戚巨澗 admin, 搾腔腰硲澗 123");
//			}
			
		}//end if
		
	}//actionPerformed

	public static void main(String[] args) {
		new UseTitledBorder();
	}//main

}//class
