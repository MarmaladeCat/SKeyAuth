package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

import clientInfo.OneClient;

public class Client
{
	static Socket clientSocket = null;
	static BufferedReader in = null;
	static BufferedWriter out = null;
	static OneClient client = null;

	static void ClientInitial()
	{
		try
		{
			clientSocket = new Socket();
			SocketAddress remoteAddr = new InetSocketAddress("127.0.0.1", 4999);
			System.out.println("创建客户端套接字成功");

			clientSocket.connect(remoteAddr, 5000);
			System.out.println("连接认证服务器成功，本地：" + clientSocket.getLocalSocketAddress());
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			Scanner sc = new Scanner(System.in);
			System.out.print("请输入用户名：");
			String username = sc.nextLine();
			System.out.print("请输入密码：");
			String password = sc.nextLine();
			client = new OneClient(username, password);
			client.PassInitial();
			String sendmsg = "UserName\n" + username + "\nCurrentPass\n" + new String(client.getPassN(1), "iso-8859-1")
					+ "\n";
			out.write(sendmsg);
			out.newLine();
			out.flush();
			System.out.printf("发送：%s", sendmsg);
			System.out.println("发送字符串成功");
			Thread.sleep(200);
			String recvstr = in.readLine();
			System.out.println("收到：" + recvstr);

			if (in != null)
				in.close();
			if (out != null)
				out.close();
			if (clientSocket != null)
				clientSocket.close();
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
			if (clientSocket != null)
				clientSocket.close();
			System.out.println("套接字关闭成功，流关闭成功");
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	static void ClientAuth()
	{

		try
		{

			for (int i = 2; i < 21; i++)
			{
				clientSocket = new Socket();
				SocketAddress remoteAddr = new InetSocketAddress("127.0.0.1", 5001);
				System.out.println("创建客户端套接字成功");

				clientSocket.connect(remoteAddr, 5000);
				System.out.println("连接初始化服务器成功，本地：" + clientSocket.getLocalSocketAddress());

				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				Scanner sc = new Scanner(System.in);
				System.out.print("请输入用户名：");
				String username = sc.nextLine();
				System.out.print("请输入密码：");
				String password = sc.nextLine();
				byte[] pass = client.getPassN(i);
				String sendmsg = "UserName\n" + username + "\nPassword\n" + new String(pass, "iso-8859-1");
				OneClient.BytesPrint("pass:", pass);
//				System.out.print("回车发送认证");
//				String tmp =sc.nextLine();
				out.write(sendmsg);
				out.newLine();
				out.flush();
				System.out.printf("发送：%s", sendmsg);
				System.out.println("发送字符串成功");
				Thread.sleep(200);
				String recvstr = in.readLine();
				System.out.println("收到：" + recvstr);
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
		System.out.println("Hello ");
		ClientInitial();
		try
		{
			for (int i = 1; i < 21; i++)
			{
				OneClient.BytesPrint(i+":", client.getPassN(i));
			}
		} catch (Exception e)
		{
			// TODO: handle exception
		}

		ClientAuth();
	}

}
