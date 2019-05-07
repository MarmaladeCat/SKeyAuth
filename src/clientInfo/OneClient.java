package clientInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class OneClient
{
	private String UserName;
	private String CurrentPass;
	private byte[] S;
	private  int N = 20;
	
	
	public OneClient(String a,String b)
	{
		this.UserName=a;
		this.CurrentPass=b;
	}
	
	public void PassInitial() throws Exception
	{
		byte[] randstr = getRandomString(CurrentPass.length()).getBytes();
		byte[] pass = CurrentPass.getBytes();
		byte[] bs = pass.clone();
		for(int i =0;i<pass.length;i++)
		{
			bs[i] = (byte) (randstr[i] | pass[i]);
		}
		
		MessageDigest digest =MessageDigest.getInstance("MD5");
		
		bs = digest.digest(bs);
		S = bs.clone();
		BytesPrint("未经过散列的S:",bs);
	}
	
	public byte[] getS()
	{
		return S;
	}

	public void setS(byte[] s)
	{
		S = s;
	}

	public byte[] getPassN(int n) throws Exception
	{
		MessageDigest digest =MessageDigest.getInstance("MD5");
		byte[] re = S.clone();
//		BytesPrint("未经过散列的S:",S);
		for(int i=0;i<N-n+1;i++)
		{
			re = digest.digest(re);
		}
		return re;
		
	}
	
	static public void BytesPrint(String string, byte[] bs)
	{
		System.out.print(string);
		for (int i=0;i<bs.length;i++)
		{
			System.out.printf("%02x ",bs[i]);
		}
		System.out.println("");
	}
	
	public String getRandomString(int len)
	{
		Random r = new Random();
		String seed = "qwertyuiopasdfghjklzxcvbnm,./;'[]\\=-0987654321`<>?:";
		String str = "";
		for(int i=0;i<len;i++)
		{
			str = str + seed.charAt(r.nextInt(seed.length()));
		}
		return str;
	}
	
	
	public String getInfo()
	{
		return "UserName\n" + UserName + "\nCurrentPass\n" + new String(S) + "\n";
		
	}

	@Override
	public String toString()
	{
		return "OneClient [UserName=" + UserName + ", CurrentPass=" + CurrentPass + "]";
	}
	

	public String getUsername()
	{
		return UserName;
	}

	public void setUsername(String username)
	{
		UserName = username;
	}

	public String getCurrentPass()
	{
		return CurrentPass;
	}

	public void setCurrentPass(String currentPass)
	{
		CurrentPass = currentPass;
	}


	
}
