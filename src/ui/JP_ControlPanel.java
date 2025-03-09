package ui;
import javax.swing.*;

public class JP_ControlPanel extends JPanel {
	/**
	 * Control Panel for the game 
	 */
	private static final long serialVersionUID = 1L;
	public JButton productMap;
	public JButton startRoad;
	public JButton rePaintMap;
	public JButton clearPath;
	public JButton[] direction;
	public JTextField jtSize;
	public JLabel jlSize;
	public JTextField jtf_x,jtf_y;
	public JTextArea jtInfo;
	public JP_ControlPanel(){
		init();
	}
	public void init(){
		productMap=new JButton("Generate Map");
		startRoad=new JButton("Generate Path"); 
		clearPath=new JButton("Clear Path");
	    rePaintMap=new JButton("Clear All");	    
	    jlSize=new JLabel("Map Size(3-8)");
	    jtSize=new JTextField(); 
	    JLabel jlx,jly;	    
		jlx=new JLabel("Forgie location (location range:0-Map Size-1)  x:");
	    jly=new JLabel("y:");
		jtf_x=new JTextField(5);
		jtf_y=new JTextField(5);		
	    jtSize.setColumns(5);
	    this.add(productMap);
	    this.add(startRoad);
	    this.add(clearPath);
	    this.add(rePaintMap);
	    this.add(jlSize);
	    this.add(jtSize);
	    this.add(jlx);
		this.add(jtf_x);
		this.add(jly);
		this.add(jtf_y);
		this.setSize(100, 300);
		this.setVisible(true);
	}
	

}
