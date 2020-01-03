package com.zjut.factory.provider.zookeeper.create;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.proto.WatcherEvent;

public class ZookeeperWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("显示进入到监听中了，可以开始监听消息了");
        System.out.println("监听器收到信息:" + watchedEvent);
        WatcherEvent wrapper = watchedEvent.getWrapper();
        String path = wrapper.getPath();
        System.out.println("监听到的路径是:" + path);
        int type = wrapper.getType();
        int state = wrapper.getState();
        System.out.println("显示收到的类型:" + String.valueOf(type) + "，状态值是:" +  String.valueOf(state));
    }
}
