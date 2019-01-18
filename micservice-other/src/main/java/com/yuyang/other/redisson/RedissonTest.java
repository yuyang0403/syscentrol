package com.yuyang.other.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author yuyang
 * @create 2019/1/18 16:44
 * @desc
 **/
public class RedissonTest {
    public static void main(String[] args) {
        //Redisson连接配置文件
        Config config = new Config();
        config.useSingleServer().setAddress("39.105.162.72:6379").setPassword("laoyu0403");
        RedissonClient redisson = Redisson.create(config);
        RLock yuxinLock = redisson.getLock("yuxinLock"); // 1.获得锁对象实例

        new RedissonTest().testM(yuxinLock);
    }

    public void testM(RLock yuxinLock){
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++){
            fixedThreadPool.execute(new Task("woker_" + i,yuxinLock));
        }
    }


    class Task implements Runnable{

        private String name;
        private RLock yuxinLock;

        public Task(String name,RLock yuxinLock){
            this.name = name;
            this.yuxinLock=yuxinLock;
            System.out.println(name);
        }
        public void run() {
            boolean res=false;
            try{
                System.out.println(name+"开始夺锁大战");
                //1.不支持过期自动解锁，不会超时
                //yuxinLock.lock();

                // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
                //lock.lock(10, TimeUnit.SECONDS);

                // 3. 尝试加锁，最多等待20秒，上锁以后10秒自动解锁（实际项目中推荐这种，以防出现死锁）
                res = yuxinLock.tryLock(20, 10, TimeUnit.SECONDS);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally{
                if(res){
                    yuxinLock.unlock();
                }
            }
        }

    }
}
