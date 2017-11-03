package lyx.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by landyChris on 2017/11/3.
 * 使用telnet localhost 8888进行测试
 */
public class NioSocketDemo {

    private Selector selector;//通道选择器

    public static void main(String[] args) throws IOException {
        NioSocketDemo nioSocketDemo = new NioSocketDemo();
        nioSocketDemo.initServer(8888);
        nioSocketDemo.listenSelector();
    }

    public void initServer(int port) throws IOException {
        //通道
        ServerSocketChannel ssc = ServerSocketChannel.open();

        ssc.configureBlocking(false);//设置成非阻塞

        //绑定端口
        ssc.socket().bind(new InetSocketAddress(port));

        //获取到选择器
        selector = Selector.open();

        //注册到选择器上
        //监听用户的连接事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务已经启动.......");
    }

    public void listenSelector() throws IOException{
        //轮询监听Selector
        while(true) {
            //最终会调用到操作系统中的多用复用选择器（本地方法）方法（如poll/epoll/select等）
            //等待客户连接
            this.selector.select();
            //迭代SelectionKey中的键值
            Iterator<SelectionKey> it = this.selector.selectedKeys().iterator();

            while(it.hasNext()) {
                SelectionKey key = it.next();
                //删除当前SelectionKey
                it.remove();
                //处理请求
                handler(key);
            }
        }
    }

    private void handler(SelectionKey key) throws IOException{
        if(key.isAcceptable()) { //连接事件
            System.out.println("新客户端连接上了....");
            //处理客户端请求事件
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);//设置成非阻塞
            //接收客户端发送的信息时，需要给通道设置读的权限
            sc.register(selector,SelectionKey.OP_READ);
        }else if(key.isReadable()) {
            SocketChannel sc = (SocketChannel)key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int readData = sc.read(buffer);
            if(readData > 0) {
                String info = new String(buffer.array(),"GBK");
                System.out.println("服务端接收到数据：" + info);
            }else {
                System.out.println("客户端关闭了....");
                key.cancel();
            }
        }
    }

}
