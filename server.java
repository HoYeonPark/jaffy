package edu.ssafy.test;

import java.io.BufferedInputStream;	
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
	ServerSocket ss = null;
	public void go()  {
        
        InputStream is=null;
        OutputStream os=null;
        Socket cSock=null;
        try {
            ss = new ServerSocket(5000);
            while(true) {
                cSock = ss.accept();
                is = cSock.getInputStream();
                os = cSock.getOutputStream();
                System.out.println("서버 실행");
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(is));
                int num = ois.readInt();
                System.out.println("데이터 받음 " + num);
                System.out.println("클라에게 데이터도 전송");
                // 받은 데이터 수량을 클라이언트에게 전송
                ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(os));
                oos.writeObject(1111);
                oos.flush();
                oos.close();
                ois.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}finally {
			if(ss!=null) {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        }
	}
	public static void main(String[] args) {
		new server().go();
	}
}
	