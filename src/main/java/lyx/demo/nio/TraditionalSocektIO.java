package lyx.demo.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by landyChris on 2017/11/3.
 * 使用telnet localhost 8888进行测试
 */
public class TraditionalSocektIO {

    public static void main(String[] args) throws Exception{
        //加上线程池就变成了可以连接多个客户端
        //但是每个线程还是只能连接一个客户端
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //新建一个serversocket对象
        ServerSocket ss = new ServerSocket(8888);

        System.out.println("server is running....");

        while(true) {
            //获取socket客户端套接字
            final Socket socket = ss.accept();

            //每连接上一个客户端，就放入线程池
            threadPool.execute(new Runnable() {
                public void run() {
                    System.out.println("new client is connected to server.....");
                    InputStream is = null;
                    try {
                        is = socket.getInputStream();
                        byte[] b = new byte[1024];
                        while(true) {
                            int data = is.read(b);
                            if(data != -1) {
                                String info = new String(b,0,data,"GBK");
                                System.out.println(info);
                            }else {
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

}
