/** Given a 9 digit keypad to type pwd. One key is faulty. When user inputs the password, 
 * the system checks for correct password considering the faulty key input with the 
 * input sequence preserved
 * Eg. faulty digit 8, Password 16878. Input due to fault 167, 1687, 1678. System fixed 
 * the fault to 16878 */
package com.key.faulty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Scanner;
import java.util.ArrayList;
@SpringBootApplication
public class FaultyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FaultyApplication.class, args);
		Scanner myObj = new Scanner(System.in);
		String pwd = "168378";
		char faultyDigit = '8';
		System.out.println("Please enter password");
		String userInput = myObj.nextLine();
		myObj.close();
		FaultyApplication app = new FaultyApplication();
		app.checkPwd(pwd,userInput,faultyDigit);
	}
	
	public void checkPwd(String pwd, String Input, char faultyDigit)
	{
		int pwdLen = pwd.length();
		int inputLen = Input.length();
		int x = pwdLen - inputLen;
		String y = null;
		// arraylist to store amended input strings
		ArrayList<String> al = new ArrayList<>();
		// while inputLen is less than pwdLen
		if(x>0)
		{   // inner loop for inserting the faulty digit 
			for (int j=0; j<inputLen; j++)
			{
				if (j==0) // insert at the beginning
				{
					y = faultyDigit + Input;
					al.add(y);
				}
				else if (j==inputLen-1) // insert at the end
				{
					y = Input + faultyDigit;
					al.add(y);
				}
				else // insert the faultyDigit in the middle of the Input String
				{
					y = Input.substring(0,j) + faultyDigit + Input.substring(j);
					al.add(y);
				}
			}// end inner for
		}// end if
		
		/* check if the string length of arraylist element is less than pwdLen to proceed with 
		 * addition of faulty digit into array list elements
		 */
		
		
		int s = al.size();
		// traverse through arraylist elements
		for (int i=0; i<=s-1; i++)
		{   
			// fetch arraylist elt, check its size against pwd size if it is less insert digit
			String elt = al.get(i);
			int eltLen = elt.length();
			if (eltLen < pwdLen)
			{  
				for (int j=0; j<eltLen; j++)
				{
					if (j==0) // insert at the beginning
					{
						y = faultyDigit + elt;
						//System.out.println(y);
						al.add(y);
					}
					else if (j==eltLen-1) // insert at the end
					{
						y = elt + faultyDigit;
						//System.out.println(y);
						al.add(y);
					}
					else // insert the faultyDigit in the middle of the Input String
					{
						y = elt.substring(0,j) + faultyDigit + elt.substring(j);
						//System.out.println(y);
						al.add(y);
					}
				}// end inner for
			}
		}
		int t = al.size();
		int flag = 0;
		// traverse through arraylist elements and check for correct password
		for (int z=0;z<t;z++)
		{
			String str = al.get(z);
			System.out.println(str);
			if(str.contentEquals(pwd))
			{
				System.out.println("Access Granted");
				flag = 1;
			}
		}
		// if the password generated do not match the actual password
		if (flag == 0)
		{
			System.out.println("Access Denied!!!");
		}
	}
}
