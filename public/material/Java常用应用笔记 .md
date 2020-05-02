反射

## 反射机制	

反射机制是在**运行状态**中：

​		对于任意一个类，都能够知道这个类的所有属性和方法；

​		对于任意一个对象，都能够调用它的任意一个方法和属性；

## 功能	

反射提供的功能：

​		在运行时判断任意一个对象所属的类；

​		在运行时构造任意一个类的对象；

​		在运行时判断任意一个类所具有的成员变量和方法；

​		在运行时调用任意一个对象的方法；

​		生成动态代理。



# Socket

## 流程图

[![Socket](https://s1.ax1x.com/2020/04/30/JbP8bj.png)](https://imgchr.com/i/JbP8bj)



### 服务器代码

```java
package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    //绑定服务端口，IP为本机IP
    //暴漏了一个服务，该服务的地址： 本机IP:9999
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        Socket socket = server.accept();
        System.out.println("与客户端链接成功！");

        //服务端向客户端发送消息output
        OutputStream out = socket.getOutputStream();
        
        String info = "hello";
        //String -> byte[]
        out.write(info.getBytes());

        //接收客户端的消息
        InputStream in = socket.getInputStream();
        byte[] bs = new byte[100];
        in.read(bs);
        System.out.println("接收客户端发来的消息："+ new String(bs));

        //关闭服务
        fileIn.close();
        out.close();
        in.close();
        socket.close();
        server.close();
    }
}
```

### 客户端代码

```java
import java.io.OutputStream;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) throws IOException {
        //客户端链接Server发布的服务
        Socket socket = new Socket("127.0.0.1",9999);

        //接收服务端发送的消息InputStream
        InputStream in = socket.getInputStream();

        接收普通文字hello
        byte[] bs = new byte[100];
        in.read(bs); //读取发送来的数据
        //byte[] -> String
        System.out.println("Client接收到的数据为："+new String(bs));  

        //客户端向服务端做出反馈（向服务端发送消息）
        OutputStream out = socket.getOutputStream();
        out.write("world".getBytes());        

        //关闭服务
        fileOut.close();
        //out.close();
        in.close();
        socket.close();
    }
}
```





## 发送文件的过程

[![socket_file](https://s1.ax1x.com/2020/04/30/JbP3rQ.png)](https://imgchr.com/i/JbP3rQ)

### 服务器代码

```java
package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    //绑定服务端口，IP为本机IP
    //暴漏了一个服务，该服务的地址： 本机IP:9999
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        Socket socket = server.accept();
        System.out.println("与客户端链接成功！");

        //服务端向客户端发送消息output
        OutputStream out = socket.getOutputStream();

        //准备要发送的文件
        File file = new File("F:\\gson-2.8.5.jar");
        //将此文件从硬盘读入到内存
        InputStream fileIn = new FileInputStream(file);
        byte[] fileBytes = new byte[1000]; //定义每次发送的文件大小
        int len = -1;
        //发送（因为文件较大，不能一次发送完毕，因此需要通过循环来分次发送）
        while((len = fileIn.read(fileBytes)) != -1){
            out.write(fileBytes,0,len);
        }

        //关闭服务
        fileIn.close();
        out.close();
        socket.close();
        server.close();
    }
}

```

### 客户端代码

```java
package socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyClient {

    public static void main(String[] args) throws IOException {
        //客户端链接Server发布的服务
        Socket socket = new Socket("127.0.0.1",9999);

        //接收服务端发送的消息InputStream
        InputStream in = socket.getInputStream();

        byte[] bs = new byte[1000]; //接收每次发送的文件切片（1000byte）
        int len = -1;
        OutputStream fileOut = new FileOutputStream("f:\\aaa.jar");
        while ( (len = in.read(bs)) != -1){
            fileOut.write(bs,0,len);
        }
        System.out.println("下载成功");

        //关闭服务
        fileOut.close();
        //out.close();
        in.close();
        socket.close();
    }
}
```



# RPC

RPC：Remote Procedure Call



[![RPC](https://s1.ax1x.com/2020/04/30/JbP1Kg.png)](https://imgchr.com/i/JbP1Kg)





## 单线程实现RPC

### 接口方法代码

```java
package RPC.service;

public interface HelloService {
    public String sayHi(String name);
}
```

```java
package RPC.service;

public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHi(String name) {
        return "hi," + name;
    }
}
```



### 服务器注册中心代码

```java
package RPC.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

//服务中心
public interface Server {
    public void start();
    public void stop();
    //注册服务
    public void register(Class service,Class serviceImpl);
    //
}
```

```java
package RPC.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

//服务中心的具体实现
public class ServerCenter implements Server {
    //map：服务端的所有可供客户端访问的接口，都注册到该map中
    //key：接口的名字“HelloService”  value：真正的Hello Service实现
    private static HashMap<String,Class> serviceRegister = new HashMap<>();
    private static int port ; //一般不会写死9999
    public ServerCenter(int port){
        this.port = port;
    }

    //开启服务端服务
    @Override
    public void start() {
        ObjectOutputStream output = null;
        ObjectInputStream input = null;
        try {
            ServerSocket server = new ServerSocket();
            server.bind(new InetSocketAddress(port));

            Socket socket = server.accept();//等待客户端链接

            //接收到客户端链接以及请求，处理该请求。。。
            input = new ObjectInputStream(socket.getInputStream());
            //因为ObjectInputStream对发送数据的顺序严格要求，因此需要参照发送的顺序逐个接收
            String serviceName = input.readUTF();
            String methodName = input.readUTF();
            Class[] parameterTypes = (Class[]) input.readObject(); //方法的参数类型:String、Integer
            Object[] arguments = (Object[]) input.readObject();//方法的参数名
            //根据客户端请求，到map中找到与之对应的接口
            Class serviceClass = serviceRegister.get(serviceName);//HelloService
            //找到具体的方法
            Method method = serviceClass.getMethod(methodName, parameterTypes);
            //执行该方法
            Object result = method.invoke(serviceClass.newInstance(), arguments);//person.say(参数列表);

            //向客户端将方法执行完毕的返回值 传给客户端
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (output != null) output.close();
                if (input != null) input.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stop() {

    }

    @Override
    public void register(Class service,Class serviceImpl) {
        serviceRegister.put(service.getName(),serviceImpl);
    }
}
```



### 客户端代码

```java
package RPC.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Client {
    //获取代表服务端接口的动态代理对象（HelloService）
    //serviceInterface：请求的接口名
    //addr：待请求服务端的IP:端口
    @SuppressWarnings("unchecked")
    public static <T>T getRemoteProxyObj(Class serviceInterface, InetSocketAddress addr){
        //newProxyInstance(a,b,c)
        /**
         * a：类加载器：需要代理哪个类（例如HelloService接口），就需要将HelloService的类加载器传入第一个参数
         * b：需要代理的对象，具备哪些方法 say()  --接口
         * 单继承，多实现 A implements B接口,C接口
         */
        return (T)Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, new InvocationHandler() {
            //proxy：代理的对象  method：哪个方法（sayHello(参数列表)）  args：参数列表
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //客户端向服务端发送请求：请求某一个具体的接口
                Socket socket = new Socket();
                ObjectOutputStream output = null;
                ObjectInputStream input = null;
                //socketaddress: Ip:端口
                try {
                    socket.connect(addr);

                    output = new ObjectOutputStream(socket.getOutputStream());//发送：序列化流（对象流）
                    //接口名、方法名：writeUTF
                    output.writeUTF(serviceInterface.getName());
                    output.writeUTF(method.getName());
                    //方法参数的类型、方法参数、：String name  Object
                    output.writeObject(method.getParameterTypes());
                    output.writeObject(args);
                    //等待服务端处理
                    //接收服务端处理后的返回值
                    input = new ObjectInputStream(socket.getInputStream());
                    return input.readObject(); //客户端-服务端->返回值
                }catch (Exception e){
                    e.printStackTrace();
                    return null;
                }finally {
                    try{
                        if (output != null) output.close();
                        if (input != null) input.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
```



## 多线程实现RPC



### 服务器注册中心代码

```java
package RPC.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//服务中心的具体实现
public class ServerCenter implements Server {
    //map：服务端的所有可供客户端访问的接口，都注册到该map中
    //key：接口的名字“HelloService”  value：真正的Hello Service实现
    private static HashMap<String,Class> serviceRegister = new HashMap<>();
    private static int port ; //一般不会写死9999
    //连接池：连接池中存在多个链接对象，每个链接对象都可以处理一个客户请求
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static boolean isRunning = false;//表示服务是否开启

    public ServerCenter(int port){
        this.port = port;
    }

    //开启服务端服务
    @Override
    public void start() {

        ServerSocket server = null;
        try {
            server = new ServerSocket();
            server.bind(new InetSocketAddress(port));

            isRunning = true;//服务已经启动
            while (true){
                //100:1 1  1  ...1  -->如果向让多个客户端请求并发执行 -> 多线程
                System.out.println("start server....");
                Socket socket = null;
                //客户端每次请求一次链接（发出一次请求），则服务端从连接池中获取一个线程对象取处理
                try {
                    socket = server.accept();//等待客户端链接
                } catch (IOException e) {
                    e.printStackTrace();
                }
                executor.execute(new ServiceTask(socket)); //启动线程处理客户请求
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() { //关闭服务
        isRunning = false;
        executor.shutdown();
    }

    @Override
    public void register(Class service,Class serviceImpl) {
        serviceRegister.put(service.getName(),serviceImpl);
    }

    private static class ServiceTask implements Runnable{
        private Socket socket;

        public ServiceTask(){
        }

        public ServiceTask(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            ObjectOutputStream output = null;
            ObjectInputStream input = null;
            try{


                //接收到客户端链接以及请求，处理该请求。。。
                input = new ObjectInputStream(socket.getInputStream());
                //因为ObjectInputStream对发送数据的顺序严格要求，因此需要参照发送的顺序逐个接收
                String serviceName = input.readUTF();
                String methodName = input.readUTF();
                Class[] parameterTypes = (Class[]) input.readObject(); //方法的参数类型:String、Integer
                Object[] arguments = (Object[]) input.readObject();//方法的参数名
                //根据客户端请求，到map中找到与之对应的接口
                Class serviceClass = serviceRegister.get(serviceName);//HelloService
                //找到具体的方法
                Method method = serviceClass.getMethod(methodName, parameterTypes);
                //执行该方法
                Object result = method.invoke(serviceClass.newInstance(), arguments);//person.say(参数列表);

                //向客户端将方法执行完毕的返回值 传给客户端
                output = new ObjectOutputStream(socket.getOutputStream());
                output.writeObject(result);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try{
                    if (output != null) output.close();
                    if (input != null) input.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }
}
```



## 测试

### 服务器测试类

```java
package RPC.test;

import RPC.service.HelloService;
import RPC.service.HelloServiceImpl;
import RPC.service.Server;
import RPC.service.ServerCenter;

public class RPCServerTest {
    public static void main(String[] args) {
        //开启一个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //服务中心
                Server server = new ServerCenter(9999);
                //将HelloService接口以及实现类注册到服务中心
                server.register(HelloService.class, HelloServiceImpl.class);
                server.start();
            }
        }).start();


    }
}
```



### 客户端测试类

```java
package RPC.test;

import RPC.client.Client;
import RPC.service.HelloService;

import java.net.InetSocketAddress;

public class RPCClientTest {
    public static void main(String[] args) throws ClassNotFoundException {
        HelloService service = Client.getRemoteProxyObj(Class.forName("RPC.service.HelloService"),new InetSocketAddress("127.0.0.1",9999));
        System.out.println(service.sayHi("zs"));
    }
}
```



## 总结

1. 客户端通过socket请求服务端，并且通过字符串或Class形式将需要请求的接口发送给服务端。客户端使用动态代理发送接口名，方法名，参数类型，参数名。
2. 服务端将可以提供的接口注册到服务中心（通过map保存 key：接口的名字 ，value：接口的实现类）。
3. 服务端接收到客户端的请求后，通过请求的接口名在服务中心的map中寻找对应的接口实现类。
4. 服务端找到接口实现类后，解析刚才客户端发送来的接口名、方法名，解析完毕后，通过反射技术将该方法执行。
5. 执行完毕后，再将该方法的返回值返回给客户端。



# 文件拆分合并

## 具体

1. 将一个文件拆分成若干个子文件。（7.5M以一个子文件1M -> 8个子文件：7个1M和一个0.5M ）
2. 将拆分后的文件合并成源文件。



## 拆分

[![文件拆分](https://s1.ax1x.com/2020/04/30/JbPaGV.png)](https://imgchr.com/i/JbPaGV)





### 简单实现

```java
package fileSplitMerge;

import java.io.*;

public class SplitFile {

    public static void main(String[] args) throws IOException {
        //源文件（等待拆分的文件）
        File resFile = new File("f:\\testImage.jpeg");
        //拆分后的目录
        File splitDir = new File("f:\\splitDir");
        splitFile(resFile,splitDir);
    }

    //拆分
    public static void splitFile(File resFile,File splitDir) throws IOException {
        if (!splitDir.exists()){
            splitDir.mkdirs();
        }

        //思路：1个输入流，n个输出流（a,b,c）
        //合并：n个输入流，1个输出流（注意顺序a,b,c）

        //拆分
        //1个输入流
        InputStream in = new FileInputStream(resFile);

        OutputStream out = null;

        //定义缓冲区为1M，当缓冲区填满时，一次性刷出成一个文件
        byte[] buf = new byte[1024*1024];
        int len = -1;
        int count = 1;
        while ((len = in.read(buf)) != -1){
            out = new FileOutputStream(new File(splitDir,count++ + ".part"));
            out.write(buf,0,len);
            //out.flush(); //（1）清理缓冲区
            out.close();  //（2）关闭流、关闭之前会强制清理缓冲区
        }
        
        out = new FileOutputStream(new File(splitDir,"conf.properties"));
        //拆分的时候：如何将文件名、分割的数量保留，为后续合并做准备
        //再生成一个配置文件保存上述描述信息
        //方式一
/*
        //查询当前操作系统的换行符
        String lineSeparator = System.getProperty("line.separator");
        out.write(("filename=" + resFile.getName() + lineSeparator).getBytes());
        out.write(("partcount=" + (count-1)).getBytes());
        out.close();
*/        
        
        //方式二：Properties,将内存中的多个属性以key=value的形式写到硬盘中
        Properties prop = new Properties();
        prop.setProperty("filename",resFile.getName());
        prop.setProperty("partcount",(count-1)+"");
        //写入硬盘（保存：持久化）
        prop.store(out,"file configuration");
        out.close();
        
        in.close();
    }
}
```



### 优化

固定拆分的使用次数，通过一个配置文件记录已经使用的次数。核心在于hasRemainingTries方法。

```java
package fileSplitMerge;

import java.io.*;
import java.util.Properties;

public class SplitFile {

    public static void main(String[] args) throws IOException {
        //源文件（等待拆分的文件）
        File resFile = new File("f:\\testImage.jpeg");
        //拆分后的目录
        File splitDir = new File("f:\\splitDir");
        //软件使用次数：5
        if(hasRemainingTries()){
            //拆分文件
            splitFile(resFile,splitDir);
        }else {
            System.out.println("使用次数已到！");
        }

    }

    //判断是否还有试用次数
    //思路：将当前用的次数保存再硬盘，然后每一次使用时和5比较
    public static boolean hasRemainingTries() throws IOException {
        Properties prop = new Properties();
        //每使用一次：1、先获取之前用了几次  2、再将之前次数+1

        //查询本次之前已经使用了几次
        prop.load(new FileInputStream("F:\\splitDir\\tries.properties"));
        String times = prop.getProperty("times");

        //null , 1放回去
        if (times == null){
            prop.setProperty("times",1+"");
        }else {
            int timeCount = Integer.parseInt(times);
            timeCount++;
            prop.setProperty("times",timeCount+"");

            if (timeCount > 5){
                return false;
            }
        }
        prop.store(new FileOutputStream("F:\\splitDir\\tries.properties"),"try time...");
        return true;
    }

    //拆分
    public static void splitFile(File resFile,File splitDir) throws IOException {
        if (!splitDir.exists()){
            splitDir.mkdirs();
        }

        //拆分
        //1个输入流
        InputStream in = new FileInputStream(resFile);

        OutputStream out = null;

        //定义缓冲区为1M，当缓冲区填满时，一次性刷出成一个文件
        byte[] buf = new byte[1024*1024];
        int len = -1;
        int count = 1;
        while ((len = in.read(buf)) != -1){
            out = new FileOutputStream(new File(splitDir,count++ + ".part"));
            out.write(buf,0,len);
            out.close();  //关闭流、关闭之前会强制清理缓冲区
        }

        //拆分的时候：如何将文件名、分割的数量保留，为后续合并做准备
        //再生成一个配置文件保存上述描述信息
        out = new FileOutputStream(new File(splitDir,"conf.properties"));

        //Properties,将内存中的多个属性以key=value的形式写到硬盘中
        Properties prop = new Properties();
        prop.setProperty("filename",resFile.getName());
        prop.setProperty("partcount",(count-1)+"");
        //写入硬盘（保存：持久化）
        prop.store(out,"file configuration");
        out.close();
        in.close();
    }
}

```





## 合并

[![文件合并](https://s1.ax1x.com/2020/04/30/JbPd2T.png)](https://imgchr.com/i/JbPd2T)



### 简单实现

​       本实现并没有使用面向对象的思路，并且没有使用拆分时使用的配置文件，对此进行简单的理解。

```java
package fileSplitMerge;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MergeFile {
    public static void main(String[] args) throws IOException {
        //读取多个拆分后的文件(inputs：所有输入流的集合)
        List<FileInputStream> inputs = new ArrayList<>();
        for(int i =1;i<=2;i++){
            inputs.add(new FileInputStream("f:\\splitDir\\"+i+".part"));
        }
        //指定合并后的文件输出流
        OutputStream out = new FileOutputStream("f:\\Java\\testImage.jpeg");

        //将多个输入流依次读入内存，最后再一次性输出到testImage.jpeg
        byte[] buf = new byte[1024*1024];
        for (FileInputStream in : inputs){
            int len = in.read(buf);
            out.write(buf,0,len);
        }
        out.close();
        for (FileInputStream in : inputs){
            in.close();
        }
    }
```



### 优化

​        SequenceInputStream 表示其他输入流的逻辑串联。它从输入流的有序集合开始，并从第一个输入流开始读取，直到到达文件末尾，接着从第二个输入流读取，依此类推，直到到达包含的最后一个输入流的文件末尾为止。

​		本例中使用了SequenceInputStream （序列流）和Enumeration集合,同时仍旧没有使用之前的配置文件。

```java
package fileSplitMerge;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class MergeFile {
    public static void main(String[] args) throws IOException {
        //文件合并，方法二：
        //指定拆分后文件位置
        File splitDir = new File("f:\\splitDir");
        mergeFile(splitDir);
    }

    //文件合并
    public static void mergeFile(File splitDir) throws IOException {
        List<FileInputStream> inputs = new ArrayList<>();
        for(int i =1;i<=2;i++){
            inputs.add(new FileInputStream("f:\\splitDir\\"+i+".part"));
        }
        Enumeration<FileInputStream> en = Collections.enumeration(inputs);
        //多个流 -> 1个流
        SequenceInputStream sin = new SequenceInputStream(en);

        //指定合并后的文件输出流
        OutputStream out = new FileOutputStream("f:\\Java\\testImage.jpeg");

        //sin->输出(类似文件复制)
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len=sin.read(buf)) !=-1){
            out.write(buf,0,len);
        }
        out.close();
        sin.close();
    }
}
```



### 优化2

​		当使用配置文件里面的信息进行优化。主要时增加了getProperties()方法来获取配置文件。

```java
package fileSplitMerge;

import java.io.*;
import java.util.*;

public class MergeFile {
    public static void main(String[] args) throws IOException {
        //文件合并，方法二：
        //指定拆分后文件位置
        File splitDir = new File("f:\\splitDir");
        mergeFile(splitDir);
    }

    //文件合并
    public static void mergeFile(File splitDir) throws IOException {
        //合并之前，先读取配置信息
        Properties prop = getProperties();
        String fileName = prop.getProperty("filename");
        int partCount = Integer.parseInt(prop.getProperty("partcount"));

        List<FileInputStream> inputs = new ArrayList<>();
        for(int i =1;i<=partCount;i++){
            inputs.add(new FileInputStream("f:\\splitDir\\"+i+".part"));
        }
        Enumeration<FileInputStream> en = Collections.enumeration(inputs);
        //多个流 -> 1个流
        SequenceInputStream sin = new SequenceInputStream(en);

        //指定合并后的文件输出流
        OutputStream out = new FileOutputStream("f:\\Java\\" + fileName);

        //sin->输出(类似文件复制)
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len=sin.read(buf)) !=-1){
            out.write(buf,0,len);
        }
        out.close();
        sin.close();
    }

    private static Properties getProperties() throws IOException {
        //找到配置文件的位置
        String configFileName = "f:\\splitDir\\conf.properties";
        Properties prop = new Properties();
        prop.load(new FileInputStream(configFileName));
        return prop;
    }


}

```



## 序列化和反序列化

[![序列化和反序列化](https://s1.ax1x.com/2020/04/30/JbPwxU.png)](https://imgchr.com/i/JbPwxU)

​        序列化/反序列化对象操作过程，都依赖对象所在的类。

​        JVM会通过此类计算出一个serialVersionUID，当类改变了，serialVersionUID就会改变。即序列化自动会生成一个serialVersionUID，反序列化也会自动生成一个serialVersionUID，并且会和序列化的serialVersionUID进行相等校验，如果相等，说明序列化及反序列化时同一个对象，则正常执行；如果不相等，则说明两者不是同一个对象，则会报错。如果不想自动生成，而是手工写死，只需要写一个变量：private static final long serialVersionUID = 1234L;

​       假如在已经把对象写到硬盘之后，修改了原类，再将对象读回内存，将会报错。即如下：

```java
Exception in thread "main" java.io.InvalidClassException: serializable.Person; local class incompatible: 
stream classdesc serialVersionUID = 8297976723952959236, 
local class serialVersionUID = 1223578132420738836
```



### 简单实现

#### 实现序列化的类

```java
package serializable;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1234L;
    private String name;
    private int age;

    public Person() {
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
```

#### 读写类

```java
package serializable;

import java.io.*;

public class SeriSample {
    
    public static void writeObject() throws IOException{
        Person per = new Person("zs",23);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("f:\\per.obj"));
        //将对象网硬盘写
        oos.writeObject(per);
        oos.close();
    }

    public static void readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("f:\\per.obj"));
        Object perObj = ois.readObject();
        Person per = (Person)perObj;
        System.out.println(per.getName()+","+per.getAge());
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        writeObject();
        readObject();

    }
}
```



# Json

## 安装

1. 下载json库。

   ​		因为json属于第三方，所以需要到官网下载（http://www.json.org/） ---- JSON-java.

   ​		下载好的文件是.zip文件，里面装的都是.java文件。只有将.java文件编译成.class文件，才能够打成jar包。

2. 引入json

   ​		打成jar包。

   ​		导入jar包。

### 如何将这个json.zip打包成jar包？

1. 首先在idea中创建一个新的Java项目

   [![json导入1](https://s1.ax1x.com/2020/04/30/JbPuPP.png)](https://imgchr.com/i/JbPuPP)

   

2. 然后创建org.json包，并将下载到的.zip文件加压，获得JSON-java-master文件夹，并将文件夹中的所有.java文件复制到org.json包中。

   [![json导入2](https://s1.ax1x.com/2020/04/30/JbPM28.png)](https://imgchr.com/i/JbPM28)

   

3. 接着点击上方蓝色按钮

   [![JbPm5t.png](https://s1.ax1x.com/2020/04/30/JbPm5t.png)](https://imgchr.com/i/JbPm5t)

   

   [![JbPeUI.png](https://s1.ax1x.com/2020/04/30/JbPeUI.png)](https://imgchr.com/i/JbPeUI)

   

4. 点击”+“ --> Empty，然后给jar包取名字，并将下方的'json' compile output移到左边，点击”OK“。

5. 然后点击Build --> Build Artifacts -->Build 。

6. 接下来，找到自己java项目，进入F:\Java\json\out\artifacts\json就可以找到需要的json.jar包，将它导入自己的项目即可。

   [![JbPQxS.png](https://s1.ax1x.com/2020/04/30/JbPQxS.png)](https://imgchr.com/i/JbPQxS)

   



## Map/JavaBean/String->Json对象

语法：Map/JavaBean/String->Json对象

```java
JSONObject json = new JSONObject(Map/JavaBean/String);
```



### Map->Json对象代码

```java
public static void demo01(){
        Map<String,String> map = new HashMap<>();
        map.put("s01","zs");
        map.put("s02","ls");
        map.put("s03","ww");
        //map -> json
        JSONObject json = new JSONObject(map);
        System.out.println(json);
        //可以得到json格式：{"key":value,"key":value,"key":value}
    }
```



### JavaBean->Json对象代码

JavaBean对象

```java
package json;

public class Person {
    private int age;
    private String name;
    private Address address;

    setter和getter方法。。。
    有参和无参构造方法
}

package json;

public class Address {
    private String homeAddress;
    private String schoolAddress;

    setter和getter方法。。。
    有参和无参构造方法
}
```

json转换

```java
//2、JavaBean(普通对象Person) -->json
    public static void demo02(){
        Person per = new Person();
        per.setName("zs");
        per.setAge(23);

        Address address = new Address("西安","北京");
        per.setAddress(address);

        //Person(JavaBean) -->Json
        JSONObject json = new JSONObject(per);
        System.out.println(json);
        //对象->json：{对象的属性名1：属性值1，对象的属性名2：属性值}
        //{"address":{"schoolAddress":"北京","homeAddress":"西安"},"name":"zs","age":23}
    }
```



### String->Json对象代码

String的格式需要跟Json的输出格式相同

```java
//String->Json
    public static void demo03(){
        String str = "{\"name\":\"zs\",\"age\":23}";
        JSONObject json = new JSONObject(str);
        System.out.println(json);
    }
```



## 文件->Json对象

### 思路

文件->String->Json对象



### 代码实现1

​       从文件变成String需要用到流，这回选择使用相对路径，需要用到Object类的getResourceAsStream方法，这个方法是非静态的，所以这个方法也不能是静态的。

```java
    //文件->JSON(文件->String->Json)
    public void demo04() throws IOException {
        InputStream in = super.getClass().getClassLoader().getResourceAsStream("json/per.json");
        byte[] bs = new byte[10];
        int len = -1;
        StringBuffer sb = new StringBuffer();
        while ((len = in.read(bs)) != -1){
            //byte[]->String
            String str = new String(bs,0,len);
            sb.append(str);
        }
//        System.out.println(sb);
        //String->Json
        String s = sb.toString();
        JSONObject json = new JSONObject(s);
        System.out.println(json);
    }
```



### 代码实现2

​        可以导入commons-io.jar包，然后调用FileUtils.readFileToString方法，直接将文件转变成字符串，而不需要使用到流。

```java
    //文件->JSON(文件->String->Json)
    public void demo04() throws IOException {
        //文件->字符串commons-io.jar
        String s = FileUtils.readFileToString(new File("F:\\Java\\reflect\\src\\json\\per.json"));
        JSONObject json = new JSONObject(s);
        System.out.println(json);
    }
```



## 生成Json文件

### 方法

json对象.write(....);



### 代码实现

```java
//生成json文件
    public static void demo05() throws IOException {
        //准备json数据（map->json）
        Map<String,Person> map = new HashMap<>();
        Person p1 = new Person(23,"zs",new Address("xa","bj"));
        Person p2 = new Person(24,"ls",new Address("xa1","bj1"));
        Person p3 = new Person(25,"ww",new Address("xa2","bj2"));
        map.put("zs",p1);
        map.put("ls",p2);
        map.put("ww",p3);

        //map->json
        JSONObject json = new JSONObject(map);
        //生成json文件
        FileWriter writer = new FileWriter("f:\\java\\p.obj");
        json.write(writer);
        writer.close();

    }
```



## json数组

### 含义

jsonArray：用于存放json对象的数组。其中json数组用中括号"[ ]"包住所有元素。



### 方法

```java
JSONArray jsonArray = new JSONArray(String格式的json数组);
```



### 简单代码

```java
 /**
     *jsonArray:
     *{"name":"zs","age":23}
     * {"classname":"lq","classno":1}
     * {"schoolname":"xj",,"zone":"xj"}
     * 变成
     * [{"name":"zs","age":23},{"classname":"lq","classno":1},{"schoolname":"xj","zone":"xj"}]
     */
    //String格式的json数组 -> json数组
    public static void demo06(){
        String jsonArrayStr = "[{\"name\":\"zs\",\"age\":23},{\"classname\":\"lq\",\"classno\":1},{\"schoolname\":\"xj\",\"zone\":\"xj\"}]";
        //String格式的json数组 -> json数组
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        System.out.println(jsonArray);
    }
```



## Map转换为json数组

### 导包

此时还需要下载其他json的jar包：

commons-beanutils、commons-collections、commons-lang、commons-logging、ezmorph、json-lib-2.4-jdk15



### 代码实现

```java
 //对于json的类型转换，通常需要引入另一个json库
    public static void demo07(){
        Map<String,String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");

        //冲突：JSONArray即存在于json.jar，又存在于json-lib库，这里调用json-lib里面的
        net.sf.json.JSONArray jsonArray = new net.sf.json.JSONArray();
        //map->json
        jsonArray = jsonArray.fromObject(map);
        System.out.println(jsonArray);
    }
```



## json数组转换为map

### 思路

JSONArray->获取每一个json->key:value->map

其中JSONArray通过String获得。



### 代码实现

```java
	//之后，全部使用新包
    //JSONArray->map
    public static void demo08(){
        //JSONArray->获取每一个json->key:value->map
        //JSONArray->获取每一个json

        //准备jsonArray数据
        String jArrayStr = "[" +
                "{\"name\":\"zs\",\"age\":23}," +
                "{\"classname\":\"lq\",\"classno\":1}," +
                "{\"schoolname\":\"xa\",\"zone\":\"xj\"}" +
                "]";
        //String->jsonArray
        net.sf.json.JSONArray jsonArray = new net.sf.json.JSONArray();
        jsonArray = jsonArray.fromObject(jArrayStr);
        //JSONArray -> 获取每一个json
        Map<String,Object> map = new HashMap<>();

        for (int i = 0;i<jsonArray.size();i++){
            //获取每一个json
            Object o = jsonArray.get(i);
            net.sf.json.JSONObject json = (net.sf.json.JSONObject)o;
            //获取每个json的key/value ->map
            Set<String> keys = json.keySet(); //每个json的所有key
            for(String key : keys){  //遍历每一个json的所有key
                Object value = json.get(key);
                map.put(key,value);
            }
        }
        System.out.println(map);
    }
```



# Java 二维码

## 方式

1. QRCode   日本   jar
2. ZXing        Google



## 二维码的画法

[![二维码](https://s1.ax1x.com/2020/04/30/JbPJVs.png)](https://imgchr.com/i/JbPJVs)

加密的过程就是将字符串转成一个布尔二维数组，在布尔值为true的地方涂黑。



## 测试类代码

```java
package qrcode;

public class Test {
    public static void main(String[] args) throws Exception {
        //生成二维码
        /**
         * 生成图片的路径  src/二维码.png
         * 文字信息、网址信息："helloword"
         */
        String imgPath = "src/二维码.png";
        String content = "https://www.baidu.com";  //扫描二维码后，网页跳转

        /**
         * 加密：文字信息 -> 二维码
         * 解密：二维码 -> 文字信息
         */

        QRCodeUtil qrCodeUtil = new QRCodeUtil();
        //加密：文字信息 -> 二维码
        //假如解密的时候有些文字不显示，那就把size加大，因为图片太小，有些信息存不下
        qrCodeUtil.encoderQRCode(content,imgPath,"png",17);

        //解密：二维码 -> 文字信息
        String imgContent = qrCodeUtil.decoderQRCode(imgPath);
        System.out.println(imgContent);
    }
```



## 加密解密代码

```java
import jp.sourceforge.qrcode.data.QRCodeImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class QRCodeUtil {
    //加密：文字信息 -> 二维码

    /**
     *
     * @param content  文字信息
     * @param imgPath   图片路径 src/二维码.png
     * @param imgType   图片后缀，类型  "png"
     * @param size      图片边长
     */
    public void encoderQRCode(String content,String imgPath,String imgType,int size) throws Exception{

        //BufferedImage：内存中的一张图片

        BufferedImage bufferedImage = qRcodeCommon(content,imgPath,size);

        //"src/二维码.png"-->二维码.png
        File file = new File(imgPath);
        //API
        //生成图片
        ImageIO.write(bufferedImage,imgType,file);

    }

    //产生一个二维码的BufferedImage
    /**
     *
     * @param content  二维码中的隐藏信息
     * @param imgType   图片格式
     * @param size      二维码边长
     * @return
     */
    public BufferedImage qRcodeCommon(String content,String imgType,int size) throws Exception{

        BufferedImage bufImg = null;

        //Qrcoder对象 字符串 -> boolean[][]
        Qrcode qrCodeHandler = new Qrcode();
        //设置二维码的排错率：L7%<M<Q<H30%  排错率越高，二维码可存储的信息越少，但是对二维码清晰度要求越小
        qrCodeHandler.setQrcodeErrorCorrect('M');
        //二维码可存放的信息类型： N：数字   A：数字+A-Z   B：所有
        qrCodeHandler.setQrcodeEncodeMode('B');
        //尺寸：取值范围：1-40
        qrCodeHandler.setQrcodeVersion(size);

        byte[] contentBytes = content.getBytes("UTF-8");//"Hello world" -> byte[]"Hello world"
        // -->boolean[][]
        boolean[][] codeOut = qrCodeHandler.calQrcode(contentBytes);

        //将传入的size方法，之后就用这个imgSize
        int imgSize = 67 + 12 * (size - 1);

        bufImg = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB); //red green blue
        //创建一个画板
        Graphics2D gs = bufImg.createGraphics();
        //将画板的背景色设置为白色
        gs.setBackground(Color.WHITE);
        //初始化
        gs.clearRect(0,0,imgSize,imgSize);
        //设置画板上图像的颜色（二维码的颜色）
        gs.setColor(Color.BLACK);
        int pixoff = 2;

        for (int i = 0;i<codeOut.length;i++){
            for (int j =0;j<codeOut.length;j++){
                if(codeOut[i][j]){
                    gs.fillRect(i*3+pixoff,j*3+pixoff,3,3);
                }
            }
        }

        //增加LOGO
        //将硬盘中的src/logo.png 加载为一个Image对象
        Image logo = ImageIO.read(new File("src/logo.png"));
        int maxHeight = bufImg.getHeight();
        int maxWidth = bufImg.getWidth();
        //在已生成的二维码上画logo
        gs.drawImage(logo,imgSize/5*2,imgSize/5*2,maxWidth/5,maxHeight/5,null);

        gs.dispose();   //释放空间
        bufImg.flush(); //清理
        return bufImg;
    }

    //解密：二维码（图片路径） -> 文字信息
    public String decoderQRCode(String imgPath)throws Exception{
        //BufferedImage内存中的图片：硬盘中的imgPath图片->内存BufferedImage
        BufferedImage bufferedImage = ImageIO.read(new File(imgPath));
        //解密
        QRCodeDecoder decoder = new QRCodeDecoder();
        TwoDimensionCodeImage tdcImage = new TwoDimensionCodeImage(bufferedImage);
        byte[] bs = decoder.decode(tdcImage);
        //byte[] --> String
        String content = new String(bs,"UTF-8");
        return content;
    }
}
```



## 解密所需类代码

```java
package qrcode;

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

public class TwoDimensionCodeImage implements QRCodeImage {
    BufferedImage bufImg;//内存中的二维码
    public TwoDimensionCodeImage(BufferedImage bufImg){
        this.bufImg = bufImg;
    }

    @Override
    public int getWidth() {
        return bufImg.getWidth();
    }

    @Override
    public int getHeight() {
        return bufImg.getHeight();
    }

    @Override
    public int getPixel(int i, int i1) {
        //像素点
        return bufImg.getRGB(i,i1);
    }
}
```



## 成果图

[![二维码成果图](https://s1.ax1x.com/2020/04/30/JbPt5q.png)](https://imgchr.com/i/JbPt5q)

扫码后跳转到百度

[![二维码跳转图](https://s1.ax1x.com/2020/04/30/JbPUP0.png)](https://imgchr.com/i/JbPUP0)





# XML解析

## DOM解析

### 含义

一次性将整个dom树装入内存。



### 解析图

[![DOM](https://s1.ax1x.com/2020/04/30/JbPK8f.png)](https://imgchr.com/i/JbPK8f)



### 弊端

如果解析xml文档，就会将整个文档以树的形式存储在内存。无论需要节点的哪个子节点，都需要将整个树进行存储。



### xml文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<dogs>
    <dog id="1">
        <name>YAYA</name>
        <score>100</score>
        <level>10</level>
    </dog>
    <dog id="2">
        <name>OUOU</name>
        <score>90</score>
        <level>15</level>
    </dog>
    <dog id="3">
        <name>wangcai</name>
        <score>100</score>
        <level>99</level>
    </dog>
</dogs>
```



### JavaBean代码

```java
package xmlParse;

public class Dog {
    private int id ;
    private String name;
    private double score;
    private int level;
    
    setter和getter方法
    有参和无参构造
    toString()方法
}
```



### XML->List

```java
package xmlParse;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParseUtil {
    //输入一个xml文件名的路径（xmlParse//dogs.xml）,输出一个List<Dog>
    public static List<Dog> parseXmlToList(String fileName) throws ParserConfigurationException, IOException, SAXException {
        List<Dog> dogs = new ArrayList<>();
        //DOM方式解析：入口（工厂）
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//产生一个DOM工厂实例
        //产品
        DocumentBuilder builder = factory.newDocumentBuilder();
        //准备输入流，为parse()做准备
        //解析为一个可以用Java处理的document对象
        Document document = builder.parse(new FileInputStream(fileName));
        //获取所有文档的节点
        Element element = document.getDocumentElement();
        //获取dog节点,nodeList存储了3个<dog>
        NodeList nodeList = element.getElementsByTagName("dog");
        //遍历nodeList（3个<dog>）
        for (int i = 0;i<nodeList.getLength();i++){
            Dog dog = new Dog();
            //获取每一个<dog>
            Element dogElement = (Element)nodeList.item(i);//list.get(i)
            //获取dog的id属性
            int id = Integer.parseInt(dogElement.getAttribute("id"));
            dog.setId(id);

            //获取dog的子节点（<name>、<score>...）
            NodeList childNodes = dogElement.getChildNodes();
            //遍历每一个子节点
            for (int j = 0;j<childNodes.getLength();j++){
                //每一个子节点<name>、<score>...（也有可能是空格、文字，例如说回车或者tab）
                Node dogChild = childNodes.item(j);
                //只拿<xxx>形式的子节点,即可Node.ELEMENT_NODE类型
                if (dogChild.getNodeType() == Node.ELEMENT_NODE){
                    if (dogChild.getNodeName().equals("name")){  //<name>
                        String name = dogChild.getFirstChild().getNodeValue();
                        dog.setName(name);
                    }else if(dogChild.getNodeName().equals("score")){
                        double score = Double.parseDouble(dogChild.getFirstChild().getNodeValue());
                        dog.setScore(score);
                    }else {
                        int level = Integer.parseInt(dogChild.getFirstChild().getNodeValue());
                        dog.setLevel(level);
                    }
                }

            }
            dogs.add(dog);
        }
        return dogs;
    }
}
```



### 主函数

```java
package xmlParse;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

//XML解析
public class Sample {

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        //输入一个xml文件名的路径（xmlParse//dogs.xml）,输出一个List<Dog>
        List<Dog> dogs = XMLParseUtil.parseXmlToList("src/xmlParse/dogs.xml");
        System.out.println(dogs);
    }
}
```



## SAX解析

### 含义

事件驱动（程序在执行时，到哪个阶段时，自动触发那个阶段相应的方法 ajax）。扫描开头和结束标签，触发相应的事件。



### xml文档

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<students>
    <student id="1001">
        <name>张三</name>
        <age>23</age>
        <birthday>1966-06-06</birthday>
    </student>
    <student id="1002">
        <name>李四</name>
        <age>24</age>
        <birthday>1999-09-09</birthday>
    </student>
</students>
```



### JavaBean

```java
public class Student {
    private int id;
    private String name;
    private int age;
    private Date birthday;
 
    setter、getter、有参和无参构造函数、toString()
}
```



### XML->List  SAX

```java
package xmlParse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SAXParseXML extends DefaultHandler {
    private List<Student> students;
    //元素的名字
    private String tagName;  //null->student
    private Student student;

    public List<Student> getStudents() {
        return students;
    }

    //开始解析xml文件（执行一次）
    @Override
    public void startDocument() throws SAXException {
        students = new ArrayList<>();

    }

    //解析xml文件 结束（执行一次）
    @Override
    public void endDocument() throws SAXException {
        System.out.println("SAX解析结束...");
    }

    //开始解析xml元素（执行多次）
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("student")){
            student = new Student();
            int id = Integer.parseInt(attributes.getValue(0));
            student.setId(id);
        }
        this.tagName = qName;
    }

    //结束解析xml元素（执行多次）
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("student")){
            students.add(student);
        }
        this.tagName = null;
    }

    //在startElement、endElement之间调用多次
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.tagName != null) {  //student
            String data = new String(ch,start,length);  //ch[] -> String
            //name
            if (this.tagName.equals("name")) {
                student.setName(data);
            }

            //age
            if (this.tagName.equals("age")) {
                student.setAge(Integer.parseInt(data));
            }

            //birthday
            if (this.tagName.equals("birthday")) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
                    student.setBirthday(simpleDateFormat.parse(data));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```



### 主函数

```java
package xmlParse;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
        InputStream in = Test.class.getClassLoader().getResourceAsStream("xmlParse/student.xml");
        SAXParseXML saxParseXML = new SAXParseXML();
        parser.parse(in,saxParseXML);
        List<Student> students = saxParseXML.getStudents();
        for (Student student:students){
            System.out.println(student);
        }
    }
}
```

