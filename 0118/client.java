package edu.ssafy.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class client {
	public static void main(String[] args) {
		new client().start();
	}
	private void start() {
		new ClientThread().start();
	}
	
	private class ClientThread extends Thread{
		@Override
		public void run() {
			go();
		}
	}

	private void go() {
		Socket sSock = null;
		try {
			sSock = new Socket("localhost",5000);
			OutputStream os = sSock.getOutputStream();    //서버랑 반대로쓰세용 인풋아웃풋
			InputStream is = sSock.getInputStream();
			System.out.println("서버로 보낼 문자열");
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(os));
			oos.writeInt(111);
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(is));
//			oos.writeObject(222);
			int num = ois.readInt();
			System.out.println(num);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(sSock != null)
				try {
					sSock.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
