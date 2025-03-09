package ui;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class JP_DirectPanel extends JPanel {
	/**
	 * The Frogie dirction panel
	 */
	private static final long serialVersionUID = 1L;
	public JButton[] direction;	
	private ImageIcon img=null;
	private JLabel jl;
	public JP_DirectPanel(){
		init();
	}
	public void init(){    
	BorderLayout flwLayout=new BorderLayout();
	this.setLayout(flwLayout);	
	direction=new JButton[4];
    direction[0]=new JButton("down");
    direction[0].setSize(50,20);
    direction[1]=new JButton("up");
    direction[1].setSize(50,20);
    direction[2]=new JButton("right");
    direction[2].setSize(20,50);
    direction[3]=new JButton("left");
    direction[3].setSize(20,50);    
    jl=new JLabel(); 
    img=new ImageIcon("frog5.gif");
    jl.setIcon(img);
	this.add(jl);	    
    this.add(direction[0],BorderLayout.PAGE_END);//x+
    this.add(direction[1],BorderLayout.PAGE_START);//x-
    this.add(direction[2],BorderLayout.LINE_END);
    this.add(direction[3],BorderLayout.LINE_START);
	this.setSize(100, 200);
	this.setVisible(true);
	}
	 public void setPICDirect(int Direct){		 	  			  
			  switch(Direct){
			  case 0:		    
				  img=new ImageIcon("frogx+.gif");
				  jl.setIcon(img);
				  break;
			  case 1:
				  img=new ImageIcon("frogx-.gif");
				  jl.setIcon(img);
				  break;
			  case 2:
				  img=new ImageIcon("frogy+.gif");
				  jl.setIcon(img);
				  break;
			  case 3:
				  img=new ImageIcon("frogy-.gif");
				  jl.setIcon(img);
				  break;
			  }		  
	  }
}
