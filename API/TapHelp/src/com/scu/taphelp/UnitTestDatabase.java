package com.scu.taphelp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UnitTestDatabase {

	@Test
	/*@Author: Abhishek
	 * @desc: Unit Test the Login Function
	 * @input: Takes in username and password as a String
	 */
	public void testConnect() {
		MySQLAccess testDbConnect = new MySQLAccess();
		//Test Case for Correct Credentials
		assertEquals("<[AUTH01] Success>", "[AUTH01] Success", testDbConnect.NativeLogin("ab", "123456"));
		//Test Case for InCorrect Credentials
		assertEquals("<[AUTH02] Incorrect Password>", "[AUTH02] Incorrect Password", testDbConnect.NativeLogin("ab", "1234567"));
		//Test Case for InCorrect Credentials
		assertEquals("<[AUTH03] User Not Registered>", "[AUTH03] User Not Registered", testDbConnect.NativeLogin("ab1", "1234567"));
		
	}
}
