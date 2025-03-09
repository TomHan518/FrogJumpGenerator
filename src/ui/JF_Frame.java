package ui;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class JF_Frame extends JFrame  {	
	/**
	 * Main Frame for the software
	 */
	private static final long serialVersionUID = 1L;
	public JP_MapPanel jp;//map panel
	public JP_ControlPanel jpCtl=new JP_ControlPanel();//control panel
	public JP_DirectPanel jpDir=new JP_DirectPanel();//direction panel
	private int mapSize;	
	private int nodeSize,dirNum;//node size and direction code
	private int loc_x,loc_y;//frog location 
	private  Frog frogFind=new Frog();
	private boolean[][] map;
	public JF_Frame(){		
		BorderLayout border=new BorderLayout();
		dirNum=1;
		jp=new JP_MapPanel(this);
		this.setLayout(border);
		this.add(jpDir,BorderLayout.EAST);
		this.add(jp,BorderLayout.CENTER);	
		this.add(jpCtl,BorderLayout.NORTH);
		jpCtl.productMap.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {            	         	
				setMap();	               
            }			
		});
		jpCtl.rePaintMap.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {            	         	
				delMap();	               
            }			
		});
		jpCtl.startRoad.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {            	         	
				startRoad();	               
            }			
		});
		jpCtl.clearPath.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {            	         	
				jp.clearMapPath();	               
            }			
		});
		
		for(int i=0;i<4;i++)
		jpDir.direction[i].addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt) {            	         	
				setDirect(evt);	               
            }			
		});
		setSize(1000,1000);
		setVisible(true);
		setTitle("Frog Jump Path Generator");		
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}	
	public void setMap(){		
		String strmapSize=jpCtl.jtSize.getText().trim();
		if(strmapSize.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please input the map size!", "Noticeʾ",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try{
		int size=Integer.parseInt(strmapSize);
		  if(size<3||size>8) {
			  JOptionPane.showMessageDialog(null, "Please input validate map size", "Notice",JOptionPane.INFORMATION_MESSAGE);
			  return;
		  }
		  mapSize=size;
		 jp.init(mapSize);
		 nodeSize=0;
		 map=frogFind.makeModel(mapSize);		
		 this.repaint();		 
		 
		}catch(Exception e1){
			JOptionPane.showMessageDialog(null, "Please input the map size!", "Noticeʾ",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public void delMap(){
		//clear all map information
		this.nodeSize=0;
		jp.delMap();
		this.repaint();
	}
	public void setDirect(ActionEvent e){
		//set frog direction left,right,up,down
		for(int i=0;i<4;i++)
	       if(e.getSource()==jpDir.direction[i]){
	    	   dirNum=i;
	    	   jpDir.setPICDirect(i);
	       }		
	}
	public void startRoad(){
		jp.clearMapPath();
		if(this.nodeSize==0){
			  JOptionPane.showMessageDialog(null, "Please generate the map first and choose validate nodes", "Notice",JOptionPane.INFORMATION_MESSAGE);
			  return;
		} 
		if(mapSize<2||mapSize>8) {
			  JOptionPane.showMessageDialog(null, "Please input validate map size", "Notice",JOptionPane.INFORMATION_MESSAGE);
			  return;
		}
		String strlocx,strlocy;
		strlocx=jpCtl.jtf_x.getText().trim();
		strlocy=jpCtl.jtf_y.getText().trim();
		if(strlocx.isEmpty()||strlocy.isEmpty()) {
			  JOptionPane.showMessageDialog(null, "Please input validate frog location", "Notice",JOptionPane.INFORMATION_MESSAGE);
			  return;
		}
		try{
			  loc_x=Integer.parseInt(strlocx);			  
			  loc_y=Integer.parseInt(strlocy);
			  if(loc_x>=mapSize||loc_y>=mapSize||loc_x<0||loc_y<0) {
				  JOptionPane.showMessageDialog(null, "Please input validate frog location", "Notice",JOptionPane.INFORMATION_MESSAGE);
				  return;
			  }
			  if(map[loc_x][loc_y]==false) {
				  JOptionPane.showMessageDialog(null, "Please input validate frog location", "Notice",JOptionPane.INFORMATION_MESSAGE);
				  return;
			  }

			  frogFind.init(map, mapSize);
			  ArrayList<Resolver> road=frogFind.solve(nodeSize, loc_x, loc_y, dirNum);			  
			  if(road.size()>0) 
				  jp.printRoad(road);
			  else
				JOptionPane.showMessageDialog(null, "No find the road path!Please check the map size and frog location", "Noticeʾ",JOptionPane.INFORMATION_MESSAGE);

			}catch(Exception e1){
				JOptionPane.showMessageDialog(null, "No find the road path!Please check the map size and frog location", "Noticeʾ",JOptionPane.INFORMATION_MESSAGE);
			}		
	}
	public void setNode(int x,int y,boolean blIs){
		 map[x][y]=blIs;
		 if(blIs==true) 
			 nodeSize++;
		 else
			 nodeSize--;
	}
 
	
}
