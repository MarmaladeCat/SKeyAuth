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
	private String Pass = "";

	public static void main(String[] args)
	{
		System.out.println("Hello ");
		// TODO Auto-generated method stub
//		Socket clientSocket = null;
//		BufferedReader in = null;
//		BufferedWriter out = null;
//		OneClient client = null;
//		try
//		{
//			clientSocket = new Socket();
//			SocketAddress remoteAddr = new InetSocketAddress("127.0.0.1" , 4999);
//			System.out.println("创建客户端套接字成功");
//			
//			clientSocket.connect(remoteAddr,5000);
//			System.out.println("连接认证服务器成功，本地："+clientSocket.getLocalSocketAddress());
//			
//			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//			out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//			Scanner sc = new Scanner(System.in);
//			System.out.print("请输入用户名：");
//			String username = sc.nextLine();
//			System.out.print("请输入用户名：");
//			String password = sc.nextLine();
//			client = new OneClient(username,password);
//			out.write(client.getInfo());
//			out.newLine();
//			out.flush();
//			System.out.println("发送字符串成功");
//			String recvstr = in.readLine();
//			System.out.println("收到："+recvstr);
//			
//		}
//		 catch (Exception e)
//		{
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		try
//		{
//			in.close();
//			out.close();
//			clientSocket.close();
//			System.out.println("关闭成功");
//		} catch (Exception e)
//		{
//			// TODO: handle exception
//			e.printStackTrace();
//			
//		}
	}

}
