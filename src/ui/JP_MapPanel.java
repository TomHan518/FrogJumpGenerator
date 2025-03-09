package ui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class JP_MapPanel extends JPanel {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton[][] jbt;
	  public int mapSize;
	  public JF_Frame jf;
	  public Color clBut=new Color(238,238,238);
      public JP_MapPanel(JF_Frame ajf){
    	  jf=ajf;    	  
      }
      public JP_MapPanel(int aMapSize,JF_Frame ajf){
    	  jf=ajf;
    	  init(aMapSize);    	  
      }
      public void init(int aMapSize){  
    	  this.removeAll();    	  
    	  mapSize=aMapSize;
    	  GridLayout layout=new GridLayout(mapSize,mapSize);
    	  this.setLayout(layout);    	  
    	  jbt=new JButton[mapSize][];
    	  for(int i=0;i<mapSize;i++){
    		  jbt[i]=new JButton[mapSize];
    		  for(int j=0;j<mapSize;j++){
    			  JButton aJbt;
    			  jbt[i][j]=new JButton();
    			  aJbt=jbt[i][j];
    			  aJbt.setText("("+i+","+j+")");    			    	
    			  aJbt.setBackground(clBut);
    			  aJbt.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent event){	 
							change(event);
						}
					});	    			 
    			  this.add(aJbt);
    		  }
    	  }
    	  this.revalidate();    	  
      }
      public void delMap(){
    	  this.removeAll();
    	  this.revalidate();
      }
      public void setMapColor(int x,int y,boolean blSet){
    	  if(blSet)
    		  jbt[x][y].setBackground(Color.ORANGE);
    	  else
    		  jbt[x][y].setBackground(this.getBackground());
      } 
      public void clearMapPath() {
    	  for(int i=0;i<mapSize;i++)
    		  for(int j=0;j<mapSize;j++)
    			  jbt[i][j].setText("("+i+","+j+")");
      }
      public void change(ActionEvent e){
    	  for(int i=0;i<mapSize;i++)
    		  for(int j=0;j<mapSize;j++)
    			  if(e.getSource()==jbt[i][j]){    				  
    				  Color jbtC=jbt[i][j].getBackground();
    				  if(jbtC==clBut){
    				  jbt[i][j].setBackground(Color.ORANGE);    				  
    				  jf.setNode(i, j, true);
    				  }
    				  else{
    				   jbt[i][j].setBackground(clBut);
    				   jbt[i][j].setText("("+i+","+j+")");
    				   jf.setNode(i, j, false);
    				  }    				
    			  }    
    	  
      }
      public void printRoad(ArrayList<Resolver> road){
    	  for(int i=0;i<road.size();i++){
    		  Resolver res=road.get(i);
    		  for(int j=0;j<res.size;j++){
    			  Location loc=res.getLocation(j);
    			  String str;
    			  str="S"+j;    					   			  
        		  //jbt[loc.x][loc.y].setText(str+": direct:"+loc.getStrDir());
    			  jbt[loc.x][loc.y].setText(str);
    		  }    		  
    	  }
      }
      public void printRoad(Resolver road){
    	     int roadSize=road.size;
    		  for(int j=0;j<roadSize;j++){
    			  Location loc=road.getLocation(j);
    			  String str;
    			  str="S"+j;    					   			  
        		  jbt[loc.x][loc.y].setText(str+": direct:"+loc.getStrDir());
    		  }   		  
    	  
      }
}
