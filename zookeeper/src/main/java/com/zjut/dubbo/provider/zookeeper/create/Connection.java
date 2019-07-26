package com.zjut.dubbo.provider.zookeeper.create;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import org.apache.zookeeper.*;

import java.util.concurrent.*;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

@Data
public class Connection implements Watcher {

    private ZooKeeper zk;
    private static CountDownLatch cdl = new CountDownLatch(1);
    private static final int DEFAULT_TIME_OUT = 5000;
    private final String connectString;
    private int sessionTimeOut;
    private final boolean canBeReadOnly;
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder().setNameFormat("开启一个zookeeper连接--%d").build());

    public Connection(String connectString) {
        this(connectString, DEFAULT_TIME_OUT, false);
    }

    public Connection(String connectString, boolean canBeReadOnly) {
        this(connectString, DEFAULT_TIME_OUT, canBeReadOnly);
    }

    public Connection(String connectString, int sessionTimeOut, boolean canBeReadOnly) {
        this.sessionTimeOut = sessionTimeOut;
        this.connectString = connectString;
        this.canBeReadOnly = canBeReadOnly;
    }

    public void connection() {
        Future<Object> submit = executor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                zk = new ZooKeeper(connectString, sessionTimeOut, new Connection(connectString), canBeReadOnly);
                cdl.await();
                return "success";
            }
        });
        try {
            Object o = submit.get();
        } catch (Exception e) {
            System.out.println("连接出错:" + e);
        }
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }

    public void close() {
        if (cdl.getCount()>0) {
            cdl.countDown();
        }
        if (zk.getState().isAlive()) {
            try {
                zk.close();
            } catch (InterruptedException e) {
                System.out.println("关闭失败");
            }
        }
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }

    public String create(String path, String data) {
        String test = null;
        try {
            test = zk.create(path, data.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println("打印创建结点的输出:" + test);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return test;
    }

    public boolean isActive() {
        return zk.getState().isAlive();
    }

    public boolean isConnection() {
        return zk.getState().isConnected();
    }

    public String getDate(String path) {
        String result = null;
        try {
            byte[] data = zk.getData(path, this, null);
            result = new String(data);
            System.out.println("根据结点查询到的值为:" + result);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("监听的消息:" + watchedEvent);
    }

    public static void main(String[] args) throws InterruptedException {
        Connection connection = new Connection("localhost:2181");
        connection.connection();

        // 休息一秒钟，以防止连接zookeeper的线程还没完全连接上而造成的不必要的影响
        Thread.sleep(1000);

        String s = connection.create("/jack", "aaaaaa");
        connection.getDate("/jack");

        connection.close();
    }
}
