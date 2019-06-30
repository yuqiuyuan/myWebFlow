package com.drebander.myWebFlow;

import com.drebander.myWebFlow.task.AsyncTask;
import com.drebander.myWebFlow.task.FutureAsyncTask;
import com.drebander.myWebFlow.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWebFlowApplicationTests {

    @Autowired
    private Task task;

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private FutureAsyncTask futureAsyncTask;

    @Test
    public void test() throws Exception {
        task.doTaskOne();
        task.doTaskTwo();
        task.doTaskThree();
    }

    @Test
    public void asyncTaskTest() throws Exception {
        asyncTask.doTaskOne();
        asyncTask.doTaskTwo();
        asyncTask.doTaskThree();
    }

    @Test
    public void futureAsyncTaskTest() throws Exception {
        long start = System.currentTimeMillis();
        Future<String> task1 = futureAsyncTask.doTaskOne();
        Future<String> task2 = futureAsyncTask.doTaskTwo();
        Future<String> task3 = futureAsyncTask.doTaskThree();

        while (true) {
            if (task1.isDone() && task2.isDone() && task3.isDone()) {
                break;
            }
//            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        System.out.println("任务全部完成。总耗时：" + (end - start) + "毫秒");
    }

    @Test
    public void completableFutureTest() throws Exception {
        long s = System.currentTimeMillis();
        CompletableFuture result = futureAsyncTask.doTaskFour();
        if (result.isDone()){

        }
        long e = System.currentTimeMillis();
        System.out.println("任务全部完成。总耗时：" + (e - s) + "毫秒");
    }

    @Test
    public void contextLoads() {
    }

}
