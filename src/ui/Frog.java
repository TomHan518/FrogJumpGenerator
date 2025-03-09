package ui;

import java.util.ArrayList;

public class Frog {
	public boolean[][] map;
	private int mapSize;
	private boolean[][] resMap;
	private ArrayList<Location> reslove;
	private ArrayList<Resolver> resList;
	private int nodeSize;
	
	public  Frog(){		
	}
	public Frog(int aMapSize,int aIsX){
		
	}
	public void run(){
		Frog f=new Frog();
		boolean[][] testMap;
		int size=4;
		int nodeSize=7;
		testMap=makeModel(size);
		f.init(testMap,size);
		f.solve(nodeSize,0,0,0);
	}

	public boolean[][] makeTestModel(int size){
		boolean[][] testMap;
		testMap=new boolean[size][];
		for(int i=0;i<size;i++){
			testMap[i]=new boolean[size];
			for(int j=0;j<size;j++)
				testMap[i][j]=false;
			}
		
		testMap[0][0]=true;
		testMap[1][0]=true;		
		testMap[1][2]=true;		
		testMap[1][3]=true;
		testMap[3][0]=true;
		testMap[3][2]=true;
        testMap[3][3]=true;
		
		
		return testMap;	
	}

	public boolean[][] makeModel(int size){
		boolean[][] testMap;
		testMap=new boolean[size][];
		for(int i=0;i<size;i++){
			testMap[i]=new boolean[size];
			for(int j=0;j<size;j++)
				testMap[i][j]=false;
			}		
		return testMap;	
	}
	public void init(boolean[][] aMap,int aMapSize){		
		mapSize=aMapSize;
		//System.out.println("mapSize:"+mapSize);
		map=new boolean[mapSize][];
		resMap=new boolean[mapSize][];
		for(int i=0;i<mapSize;i++)
		{
			map[i]=new boolean[mapSize];
			resMap[i]=new boolean[mapSize];
			for(int j=0;j<mapSize;j++){
				map[i][j]=aMap[i][j];
				resMap[i][j]=aMap[i][j];
			}
		}	
		reslove=new ArrayList<Location>();	
	}
	public void restoreMapByNode(int aNode){
		int resSize=reslove.size();
		int i;
		for(i=aNode+1;i<resSize;i++){
			int x,y;
			x=reslove.get(i).getX();
			y=reslove.get(i).getY();			
			map[x][y]=true;			
		}
		for(i=resSize-1;i>aNode;i--){
			reslove.remove(i);
			
		}
			
	}
	public void restoreMap(){
		for(int i=0;i<mapSize;i++)		{
			
			for(int j=0;j<mapSize;j++){
				map[i][j]=resMap[i][j];			
			}
		}		
	}
	public void printMap(){
		int intSize=mapSize-1;
		for(int j=intSize;j>=0;j--)	{
			System.out.println();
			for(int i=0;i<mapSize;i++){
				if(resMap[i][j])
				 System.out.print(" * ");
				else
					System.out.print(" X ");
			}
		}
	}
	public ArrayList<Resolver> solve(int aNode,int x,int y,int aDirect){
		nodeSize=aNode;
		resList=new ArrayList<Resolver>();
		search(map,aNode,x,y,aDirect);
		restoreMap();
		return resList;
	}
	public boolean search(boolean[][] aMap,int aNode,int x,int y,int aDirect){
		boolean isMove=false;
		aMap[x][y]=false;
		boolean[][] useMap=this.copyMap(aMap);
		if(aNode<1) return false;
		if(aNode==1) {
			int resSize=reslove.size()+1;
			if(resSize==this.nodeSize){
				Location aLoc=new Location();
				aMap[x][y]=false;
				aLoc.set(x, y, aDirect);
				reslove.add(aLoc);
				Resolver newRes=new Resolver();
				newRes.init(reslove,mapSize);
				resList.add(newRes);
				reslove.remove(resSize-1);			//reslove.clear();
				return true;
			}
		}
		Location aLoc=new Location();
		aLoc.set(x, y, aDirect);
		reslove.add(aLoc);
		boolean isMoveA;
		isMoveA=false;
		switch(aDirect){
		case 0://X+ Down
			for(int move=x+1;move<mapSize;move++)
				if(useMap[move][y]==true&&(this.isOrder(x, y, move, y)==true)){						   
				   isMoveA=search(useMap,aNode-1,move,y,aDirect);
				   if(isMoveA) isMove=true;
				   useMap=this.copyMap(aMap);
				   //restoreMapByNode(aNode);
			}
			//restoreMapByNode(aNode);
			for(int move=y+1;move<mapSize;move++)
				if(useMap[x][move]==true&&(this.isOrder(x, y, x,move)==true)){
					int dis=move-y;
					int dir=2;
					if(dis<0) dir++;
					isMoveA=search(useMap,aNode-1,x,move,dir);
					if(isMoveA) isMove=true;
					useMap=this.copyMap(aMap);
					//restoreMapByNode(aNode);
			}
			for(int move=y-1;move>=0;move--)
				if(useMap[x][move]==true&&(this.isOrder(x, y, x,move)==true)){
					int dis=move-y;
					int dir=2;
					if(dis<0) dir++;
					isMoveA=search(useMap,aNode-1,x,move,dir);
					if(isMoveA) isMove=true;
					useMap=this.copyMap(aMap);
					//restoreMapByNode(aNode);
			}
			break;
		case 1://X- Up
			for(int move=x-1;move>=0;move--)
				if(useMap[move][y]==true&&(this.isOrder(x, y, move, y)==true)){							
					isMoveA=search(useMap,aNode-1,move,y,aDirect);
					if(isMoveA) isMove=true;
					useMap=this.copyMap(aMap);
					//restoreMapByNode(aNode);
			}
			//restoreMapByNode(aNode);
			for(int move=y+1;move<mapSize;move++)
				if(useMap[x][move]==true&&(this.isOrder(x, y, x,move)==true)){
					int dis=move-y;
					int dir=2;
					if(dis<0) dir++;
					isMoveA=search(useMap,aNode-1,x,move,dir);
					if(isMoveA) isMove=true;
					useMap=this.copyMap(aMap);
					//restoreMapByNode(aNode);
			}
			for(int move=y-1;move>=0;move--)
				if(useMap[x][move]==true&&(this.isOrder(x, y, x,move)==true)){
					int dis=move-y;
					int dir=2;
					if(dis<0) dir++;
					isMoveA=search(useMap,aNode-1,x,move,dir);
					if(isMoveA) isMove=true;
					useMap=this.copyMap(aMap);
					//restoreMapByNode(aNode);
			}				
			break;
		case 2://Y+ Right
			for(int move=y+1;move<mapSize;move++)
				if(useMap[x][move]==true&&(this.isOrder(x, y, x,move)==true)){							
					isMoveA=search(useMap,aNode-1,x,move,aDirect);
					if(isMoveA) isMove=true;
					useMap=this.copyMap(aMap);
					//restoreMapByNode(aNode);
			}
			for(int move=x+1;move<mapSize;move++)
				if(useMap[move][y]==true&&(this.isOrder(x, y, move, y)==true)){
					int dis=move-x;
					int dir=0;
					if(dis<0) dir++;
					isMoveA=search(useMap,aNode-1,move,y,dir);
					if(isMoveA) isMove=true;
					useMap=this.copyMap(aMap);
					//restoreMapByNode(aNode);
			}
			for(int move=x-1;move>=0;move--)
				if(useMap[move][y]==true&&(this.isOrder(x, y, move, y)==true)){
					int dis=move-x;
					int dir=0;
					if(dis<0) dir++;
					isMoveA=search(useMap,aNode-1,move,y,dir);
					if(isMoveA) isMove=true;
					useMap=this.copyMap(aMap);
					//restoreMapByNode(aNode);
			}
			//restoreMapByNode(aNode);						
			break;
		case 3://Y-	Left	
			for(int move=y-1;move>=0;move--)
				if(useMap[x][move]==true&&(this.isOrder(x, y, x,move)==true)){
				   isMoveA=search(useMap,aNode-1,x,move,aDirect);
				   if(isMoveA) isMove=true;
				   useMap=this.copyMap(aMap);
				   //restoreMapByNode(aNode);
			}
			//restoreMapByNode(aNode);
			for(int move=x+1;move<mapSize;move++)
				if(useMap[move][y]==true&&(this.isOrder(x, y, move, y)==true)){
					int dis=move-x;
					int dir=0;
					if(dis<0) dir++;
					isMoveA=search(useMap,aNode-1,move,y,dir);
					if(isMoveA) isMove=true;
					useMap=this.copyMap(aMap);
					//restoreMapByNode(aNode);
			}
			for(int move=x-1;move>=0;move--)
				if(useMap[move][y]==true&&(this.isOrder(x, y, move, y)==true)){
					int dis=move-x;
					int dir=0;
					if(dis<0) dir++;
					isMoveA=search(useMap,aNode-1,move,y,dir);					
					if(isMoveA) isMove=true;
					useMap=this.copyMap(aMap);					
					//restoreMapByNode(aNode);
					
			}			
			break;	
		
		}
		//restoreMapByNode(aNode);
		if(!isMove){
			isMove=false;
			aMap[x][y]=true;
			}
		return isMove; 	    
	}
	public boolean[][] copyMap(boolean[][] sourceMap){
		boolean[][] testMap;
		testMap=new boolean[mapSize][];
		for(int i=0;i<mapSize;i++){
			testMap[i]=new boolean[mapSize];
			for(int j=0;j<mapSize;j++)
				testMap[i][j]=sourceMap[i][j];
			}		
		return testMap;					
	}
	public boolean isOrder(int ax,int ay,int aloc,int bloc){
		int dx,dy;
		boolean bl=false;
		dx=ax-aloc;
		dy=ay-bloc;
		if(dx==0||dy==0) bl=true;
		return bl;		
	}
	
	public boolean[] makeDirect(int aDir){
		boolean[] bls=new boolean[4];
		for(int i=0;i<4;i++){
			bls[i]=true;			
		}
		if(aDir==0||aDir==2) bls[aDir+1]=false;
		if(aDir==1||aDir==3) bls[aDir-1]=false;
		return bls;
	}
}
