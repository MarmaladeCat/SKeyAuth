package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import clientInfo.OneClient;

public class Server
{
	ServerSocket listenSocket = null;
	Socket clientSocket = null;
	BufferedReader in = null;
	BufferedWriter out = null;
	OneClient client = null;
	void ServerInitial()
	{
		try
		{
			listenSocket = new ServerSocket();
			SocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 4999);
			listenSocket.bind(serverAddr);
			System.out.println("初始化服务器启动，在4999端口侦听连接,等待初始化");
		} catch (Exception e)
		{
			System.out.println("初始化服务器启动失败");
			e.printStackTrace();
		}
		
		try
		{
			while (true)
			{
				clientSocket = listenSocket.accept();
				System.out.println("连接成功，来自" + clientSocket.getRemoteSocketAddress());

				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

				String recvstr1 = in.readLine();
				String recvstr2 = in.readLine();
				String recvstr3 = in.readLine();
				String recvstr4 = in.readLine();

				if(recvstr1.equals("Username") && recvstr3.equals("CurrentPass"))
				{
					System.out.printf("接收到来自客户端初始化请求:%s",recvstr1+":"+recvstr2+","+recvstr3+":"+recvstr4);
					this.client = new OneClient(recvstr2, recvstr4);
					out.write("ack"+ this.client.toString());
				}
				System.out.println("发送成功");
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (clientSocket != null)
					clientSocket.close();
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try
		{
			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (listenSocket != null)
				listenSocket.close();
			if (clientSocket != null)
				clientSocket.close();
			System.out.println("套接字关闭成功，流关闭成功");
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
