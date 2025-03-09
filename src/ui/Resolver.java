package ui;
import java.util.ArrayList;

public class Resolver{
	public int size;
	public int mapSize;
	public char[][] resMap;//��ͼ
	public ArrayList<Location> reslove;	
	public Resolver(){		
		size=0;
	}
	public Location getLocation(int index){
		return reslove.get(index);
	}
	public boolean getDirect(int ax,int ay,Integer dir){
		boolean find=false;
		for(int i=0;i<reslove.size();i++){
			int x,y;
			x=reslove.get(i).getX();
			y=reslove.get(i).getY();
			int aDirection=reslove.get(i).getDirect();
			if(ax==x&&ay==y) {
				System.out.println(aDirection);
				//dir=new Integer(aDirection);
				dir=aDirection;	
				return true;				
			} 
			
		}
		return find;
	}
	public void init(ArrayList<Location> aReslove,int aMapSize){		
		reslove=new ArrayList<Location>();
		size=aReslove.size();
		mapSize=aMapSize;
		resMap=new char[mapSize][];
		for(int i=0;i<mapSize;i++){
			resMap[i]=new char[mapSize];
			for(int j=0;j<mapSize;j++){
				resMap[i][j]='X';
				}
		}
		
		for(int i=0;i<size;i++){
			Location newLoc=aReslove.get(i);
			int x,y,dir;
			x=newLoc.getX();
			y=newLoc.getY();
			dir=newLoc.getDirect();
			switch(dir){
			case 0:
				resMap[x][y]='>';
				break;
			case 1:
				resMap[x][y]='<';
				break;
			case 2:
				resMap[x][y]='^';
				break;
			case 3:
				resMap[x][y]='v';
				break;
			}
			reslove.add(newLoc); 
		}
		
	}
	public void printMapRoad(){
		int strLoc=mapSize-1;
		for(int i=strLoc;i>=0;i--){
			System.out.println();
			for(int j=0;j<mapSize;j++) {
				System.out.print(" "+resMap[j][i]+" ");
				
			}
		}
	}
	public boolean isRight(ArrayList<Location> reslove){
		boolean bl=true;		
		for(int i=0;i<reslove.size()-1;i++){
			Location x,y;
			x=reslove.get(i);
			y=reslove.get(i+1);
			int dirx,diry;
			dirx=x.getX()-y.getX();
			diry=x.getY()-y.getY();
			if((dirx!=0)&&(diry!=0)) {
				bl=false;
				break;
			}
		}
		return bl;	
	}
	public void print(){
		System.out.println("\nResolver pass :size="+reslove.size());
		for(int i=0;i<size;i++){
			Location loc=reslove.get(i);
			loc.print();
		}
		System.out.println();
		//System.out.println("\nMapRoad");
		//printMapRoad();
	}
	

}
