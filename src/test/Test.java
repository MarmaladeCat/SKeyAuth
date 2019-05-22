package test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import clientInfo.OneClient;

public class Test
{
	private static Logger logger = Logger.getLogger(Test.class);
	public static void main(String[] args) throws Exception
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

//		try
//		{
//			OneClient client = new OneClient("username", "123456");
//			client.PassInitial();
//
//			byte[] bs = client.getPassN(5);
//			OneClient.BytesPrint("bs:", bs);
//			String str = new String(bs, "iso-8859-1");
//			System.out.println(str);
//			OneClient.BytesPrint("bS:", str.getBytes("iso-8859-1"));
//
//		} catch (Exception e)
//		{
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//        logger.info("This is info message.");  
		
		String str = "12345";
		MessageDigest digest =MessageDigest.getInstance("MD5");

		byte[] bs = digest.digest(str.getBytes());
		OneClient.BytesPrint("未经过散列的S:",bs);
		String bb = "";
		for(int i =0;i<bs.length;i++)
		{
			bb += byteHEX(bs[i]);
		}
		
		String aa = new String(bs, "iso-8859-1");	
		System.out.println(bb);
		
		

	}

	public static String byteHEX(byte ib)
	{
		char[] Digit ={ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}
}
