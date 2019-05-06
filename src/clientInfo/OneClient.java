package clientInfo;


public class OneClient
{
	private String UserName;
	private String CurrentPass;
	
	
	public OneClient(String a,String b)
	{
		this.UserName=a;
		this.CurrentPass=b;
	}
	
	public String getInfo()
	{
		return "UserName\n" + UserName + "\nCurrentPass\n" + CurrentPass + "\n";
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
