package lyx.demo.classloader;

import java.io.*;

/**
 * Created by landyChris on 2017/10/29.
 */
public class MyClassLoader extends ClassLoader {

    private String path;//加载类的路径
    private String name;//类加载器的名称

    //指定父类加载器
    public MyClassLoader(ClassLoader parent, String path, String name) {
        super(parent);//显示指定父类加载器
        this.path = path;
        this.name = name;
    }

    public MyClassLoader(String path, String name) {
        super();//让系统类加载器成为该类的父加载器
        this.path = path;
        this.name = name;
    }

    /**
     * 通过自定义ClassLoader加载我们自己定义的类
     * @param name 全路径名称，类似：lyx.demo.xxx.Demo
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = readClassFile2ByteArray(name);
        return this.defineClass(name,data,0,data.length);
    }

    /**
     * 获取.class字节数组
     * lyx.demo.xxx.Demo-->d:/lyx/demo/xxx/Demo.class
     * @param name
     * @return
     */
    private byte[] readClassFile2ByteArray(String name) {

        InputStream is = null;

        byte returnData[] = null;

        name = name.replaceAll("\\.", File.separator);

        String filePath = this.path + name + ".class";

        File file = new File(filePath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            is = new FileInputStream(file);

            int tmp = 0;
            while ((tmp = is.read()) != -1){
                baos.write(tmp);
            }
            returnData = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if(is != null) {
                    is.close();
                }
                if(baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return returnData;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
