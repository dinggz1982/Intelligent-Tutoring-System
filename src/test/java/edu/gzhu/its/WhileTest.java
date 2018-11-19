package edu.gzhu.its;

public class WhileTest {
	
	
	public static void main(String[] args) {
		int i=1000;
		int k=0;
		String strs = "";
		for (int j = 0; j < i; j++) {
			if(k<10){
				strs += j+",";
				k++;
			}
			else{
				System.out.println(strs);
				k=0;
				strs="";
			}
			
		}
		
		
		
	}

}
