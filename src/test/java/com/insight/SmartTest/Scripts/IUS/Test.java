package com.insight.SmartTest.Scripts.IUS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.String;

public class Test {

	public static void main(String[] args) throws Throwable{
		
		String data = "BR1300G|209.99|1";
		 {
			/*
			 * String[] filedatasplit = data.split("\\|"); String Material =
			 * filedatasplit[0]; String Price = filedatasplit[1]; String Part =
			 * filedatasplit[2];
			 */
		       String Price = "-130.00";
		       String a = Price.replace(",", "");
		       Float f = Float.parseFloat(a);
		       int a1 = Math.round(f);
		      // int price1 = Integer.parseInt(Price);
		       System.out.println(a1);
			/*
			 * File dir = new File(System.getenv("USERPROFILE")+"\\Downloads"); File[]
			 * dirContents = dir.listFiles(); label: for (int i = 0; i < dirContents.length;
			 * i++) { String filename = dirContents[i].getName(); FileReader FR = new
			 * FileReader(dir+"\\"+filename); BufferedReader BR = new BufferedReader(FR);
			 * String Content = ""; while((Content = BR.readLine())!= null){ data= Content;
			 * System.out.println(data); break label;
			 * 
			 * }
			 * 
			 * }
			 */
		
		 
		}
	}

}
