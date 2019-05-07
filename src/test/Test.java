package test;

import java.security.MessageDigest;
import java.util.Random;

import clientInfo.ClientInfo;
import clientInfo.OneClient;

public class Test
{
	
	
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
//		try
//		{
//			OneClient client = new OneClient("username" , "123456");
//			client.PassInitial();
//			for(int i=1;i<22;i++)
//			{
//				OneClient.BytesPrint("第"+i+"个口令S:",client.getPassN(i));
//			}
//			
//			ClientInfo auth = new ClientInfo("username" , client.getPassN(12));
//			
//			System.out.println(auth.Authentication("username", client.getPassN(13)));
//			
//
//		} catch (Exception e)
//		{
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		
		try
		{
			OneClient client = new OneClient("username" , "123456");
			client.PassInitial();
			
			byte[] bs = client.getPassN(5);
			OneClient.BytesPrint("bs:",bs);
			String str = new String(bs,"iso-8859-1");
			System.out.println(str);
			OneClient.BytesPrint("bS:",str.getBytes("iso-8859-1"));
		
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}



	}

}
