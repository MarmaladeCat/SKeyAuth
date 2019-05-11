package clientInfo;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;

import org.apache.log4j.Logger;

import test.Test;

public class ClientInfo
{

	private String UserName;
	private byte[] CurrentPass;
	private static Logger logger = Logger.getLogger(ClientInfo.class);
	public ClientInfo(String username, byte[] currentPass)
	{
		this.UserName = username;
		this.CurrentPass = currentPass.clone();
	}

	@Override
	public String toString()
	{
		try
		{
			return "ClientInfo [UserName=" + UserName + ", CurrentPass=" + new String(CurrentPass, "iso-8859-1") + "]";
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean Authentication(String name, byte[] pass) throws Exception
	{
		boolean re = false;
		MessageDigest digest = MessageDigest.getInstance("MD5");
		byte[] passMD5 = digest.digest(pass);

		OneClient.BytesPrint("服务器保存口令：", this.CurrentPass);
		OneClient.BytesPrint("待验证的pass:", pass);
		OneClient.BytesPrint("pass的MD5:", passMD5);
		System.out.println("passMD5与CurrentPass比较结果：" + Arrays.equals(this.CurrentPass, passMD5));
		if (this.UserName.equals(name) && Arrays.equals(this.CurrentPass, passMD5))
		{
			re = true;
			this.CurrentPass = pass.clone();
		}
		if(re)
		{
			logger.info(this.UserName+" try login:"+"成功");
		}
		else {
			logger.info(this.UserName+" try login:"+"失败");
		}
		
		return re;
	}

	
}
