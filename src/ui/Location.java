package ui;

public class Location {	
		public int x;		
		public int y;
		public int direct;
		public Location(){			
		}	
		public  Location(int ax,int ay,int aDirect){
			set(ax,ay,aDirect);
	   }
       public void set(int ax,int ay,int aDirect){
			x=ax;
			y=ay;
			direct=aDirect;
	   }
       public void print(){
    	   //System.out.println("Location:   x:"+x+"  y:"+y+"  dircect:"+direct);
    	   String strDir="";
    	   switch(direct){
    	   case 0:
    		   strDir="x+";
    		   break;
    	   case 1:
    		   strDir="x-";
    		   break;
    	   case 2:
    		   strDir="y+";
    		   break;
    	   case 3:
    		   strDir="y-";
    		   break;    	   
    	   }
    	   System.out.print("("+x+","+y+","+strDir.toString()+")    ");
       }
       public String getStrDir(){
    	   String strDir="";
    	   switch(direct){
    	   case 0:
    		   strDir="x+";
    		   break;
    	   case 1:
    		   strDir="x-";
    		   break;
    	   case 2:
    		   strDir="y+";
    		   break;
    	   case 3:
    		   strDir="y-";
    		   break;    	   
    	   }
    	   return strDir;    	   
       }
       public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		public int getDirect() {
			return direct;
		}
		public void setDirect(int direct) {
			this.direct = direct;
		}
}
