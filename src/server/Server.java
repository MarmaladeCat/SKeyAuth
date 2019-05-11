package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import clientInfo.ClientInfo;
import clientInfo.OneClient;

public class Server
{
	static ServerSocket listenSocket = null;
	static Socket clientSocket = null;
	static BufferedReader in = null;
	static BufferedWriter out = null;
	static ClientInfo clientinfo = null;

	static void ServerInitial()
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
				Thread.sleep(200);
				String recvstr1 = in.readLine();
				String recvstr2 = in.readLine();
				String recvstr3 = in.readLine();
				String recvstr4 = in.readLine();

				if (recvstr1.equals("UserName") && recvstr3.equals("CurrentPass"))
				{
					System.out.printf("接收到来自客户端初始化请求:%s\n",
							recvstr1 + ":" + recvstr2 + "," + recvstr3 + ":" + recvstr4);
					byte[] recvbytes4 = recvstr4.getBytes("iso-8859-1");
					OneClient.BytesPrint("CurrentPass:", recvbytes4);
					clientinfo = new ClientInfo(recvstr2, recvbytes4);
					out.write("ack" + clientinfo.toString());
					out.newLine();
					out.flush();
				}
				System.out.println("\n回信发送成功");
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (clientSocket != null)
					clientSocket.close();
				break;
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

	public static void ServerAuth()
	{
		try
		{
			listenSocket = new ServerSocket();
			SocketAddress serverAddr = new InetSocketAddress("127.0.0.1", 5001);
			listenSocket.bind(serverAddr);
			System.out.println("身份认证服务器启动，在5001端口侦听连接,等待用户认证");
		} catch (Exception e)
		{
			System.out.println("身份认证服务器启动失败");
			e.printStackTrace();
		}

		try
		{
			while (true)
			{
				clientSocket = listenSocket.accept();
				System.out.println("收到连接，来自" + clientSocket.getRemoteSocketAddress());

				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				Thread.sleep(200);
				String recvstr1 = in.readLine();
				String recvstr2 = in.readLine();
				String recvstr3 = in.readLine();
				String recvstr4 = in.readLine();

				if (recvstr1.equals("UserName") && recvstr3.equals("Password"))
				{
					System.out.printf("接收到来自客户端认证请求:%s\n", recvstr1 + ":" + recvstr2 + "," + recvstr3 + ":" + recvstr4);
					byte[] recvbytes4 = recvstr4.getBytes("iso-8859-1");
					OneClient.BytesPrint("CurrentPass:", recvbytes4);
					boolean flag = clientinfo.Authentication(recvstr2, recvbytes4);
					if (flag)
					{
						out.write("True 恭喜通过验证");
						out.newLine();
						out.flush();
					} else
					{
						out.write("False 没能通过验证");
						out.newLine();
						out.flush();
					}
					System.out.println(flag + "\n回信发送成功");
					
				}

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
		ServerInitial();
		ServerAuth();
	}

}
