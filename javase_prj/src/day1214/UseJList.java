package day1214;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * JList)
 * MVC Pattern聖 奄鋼生稽廃 Component税 紫遂.
 * @author owner
 */
@SuppressWarnings("serial")
public class UseJList extends JFrame implements ActionListener,MouseListener/*ListSelectionListener*/{

	private JList<String> jl;  //View
	private DefaultListModel<String> dlm;  //Model
	private JLabel jlOutput;
	private JButton jb;
	
	public UseJList() {
		super("JList税 紫遂");
		
		dlm=new DefaultListModel<String>();
		dlm.addElement("Java SE");
		dlm.addElement("Oracle");
		dlm.addElement("JDBC");
		dlm.addElement("HTML");
		dlm.addElement("JavaScript");
		dlm.addElement("CSS");
		
		jl=new JList<String>(dlm);
		
		//ScrollBar亜 蒸澗 JList拭惟 ScrollBar 竺舛
		JScrollPane jsp=new JScrollPane(jl);
		
		jlOutput=new JLabel("窒径");
		jlOutput.setBorder(new TitledBorder("識澱 焼戚奴"));
		
		jb=new JButton("脊径");
		
		add("North",jb);
//		add("Center",jl);
		add("Center",jsp);//陳匂獲闘研 亜遭 什闘継郊 梓端研 壕帖
		add("South",jlOutput);
		
		//戚坤闘
		jb.addActionListener(this);
//		jl.addListSelectionListener(this);
		jl.addMouseListener(this);
		
		setBounds(100, 100, 300, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}//UseJList
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		String lang= JOptionPane.showInputDialog("陳濃斗 鯵降淫恵 情嬢研 脊径");
//		System.out.println(lang);
		//脊径汽戚斗戚糠稽 Model梓端研 紫遂廃陥.
		//溌昔精 mt"", 昼社澗 null
//		if(lang!=null && !lang.equals("")) {
//			dlm.addElement(lang);
//		}//end if//蟹たたたたたた
		
//		if(!lang.equals("") && lang!=null) {
//税 庚薦澗 蒋拭依叔楳馬壱 及拭依 叔楳馬惟喫. 庚薦澗 昼社馬檎 null戚級嬢亜澗汽,lang戚 照幻級嬢閃辞 昔什渡什 痕呪研 握壱赤走 省焼辞 
//梓端亜 幻級嬢走走省紹奄 凶庚拭 Error, null戚檎 五社球研 災君 析聖 希 拝呪 赤陥...悦汽 蒋拭 床檎 null析凶 五社球研 採研呪 蒸嬢 Error
		//mt析匡 ==生稽澗 搾嘘拝呪 蒸陥!
		
		if(lang!=null && !lang.equals("")) {
			dlm.addElement(lang);
		}
		
	}//actionPerformed
	
//	int i=0;//穿蝕痕呪稽 背辞 馬蟹析凶 窒径...21常腰 角生檎 益及稽澗 坦軒研 公廃陥.(Error)廃域亜 赤製.
//	private	boolean flag;//情嬢災庚馬壱 陥承呪 赤陥..
//	@Override
//	public void valueChanged(ListSelectionEvent lse) {
//		//識澱吉 焼戚奴(JList)税 葵(DefaultListModel)聖 条嬢人辞
//		////////
////		int i=0;//走蝕痕呪澗 域紗 段奄鉢亜 鞠奄凶庚拭
////		i++;
////		if(i%2==0) {
//		if(flag) {
//		////////
//			int idx=jl.getSelectedIndex();
//		String value=dlm.get(idx);
//		//JLabel拭 蓄亜
//		jlOutput.setText(value);//売 刊研凶 馬蟹 郷脅 馬蟹 =>災軒澗 切郊税 疑拙精 鯵降切亜 厳聖呪 蒸陥. 
//		//魚虞辞 砧腰 叔楳鞠亀 廃腰 窒径鞠亀系 幻級嬢醤 馬澗依...
////		System.out.println(value);//売 刊研凶 馬蟹 郷脅 馬蟹 2鯵亜 窒径吉陥...
//		//痕呪人 薦嬢..?研 硝檎 拝呪 赤陥..嬢憩
//
//		//識澱吉 葵聖 肢薦
//		dlm.removeElementAt(idx);//jl.getSelectedIndex();聖 暁馬檎 暁 達焼 隔嬢醤 馬奄 凶庚拭 軒什闘拭 羨悦聖2腰拭辞 1腰生稽 匝析呪 赤陥.
//		//羨悦旗馬檎 反晴失穣穣//but 2腰 昔窒鞠檎辞 昔畿什亜 痕井吉陥...Error.....
//
//		}//end if
//		flag=!flag;//魁戚 照蟹澗 坪球! boolean戚 希 慨陥.
//	}//valueChanged


	@Override
	public void mouseClicked(MouseEvent e) {
		int idx=jl.getSelectedIndex();
		String value=dlm.get(idx);
		//JLabel拭 蓄亜
		jlOutput.setText(value);
		//識澱吉 葵聖 肢薦
		dlm.removeElementAt(idx);
	}//mouseClicked
	
	@Override
	public void mousePressed(MouseEvent e) {
	}//喚険凶
	@Override
	public void mouseReleased(MouseEvent e) {
	}//郷凶?
	@Override
	public void mouseEntered(MouseEvent e) {
	}//働舛慎蝕拭 級嬢穐聖凶
	@Override
	public void mouseExited(MouseEvent e) {
	}//原酔什朕辞亜 匙閃蟹穐聖凶
	
	public static void main(String[] args) {
		new UseJList();
	}//main

}//class
