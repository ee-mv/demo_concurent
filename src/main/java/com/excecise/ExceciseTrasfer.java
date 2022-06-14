package com.excecise;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j(topic = "c.ExceciseTicket")
public class ExceciseTrasfer {
    public static void main(String[] args) throws InterruptedException {
        // 模拟多人买票
        TicketWindow ticketWindow = new TicketWindow(10000);

        //  线程集合
        List<Thread> list = new ArrayList<>();

        //  卖出的票数统计 Vector 是线程安全的.  如果换成ArrayList就会出错.
        List<Integer> acountList = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(() -> {
                int acount = ticketWindow.sell((new Random().nextInt(5) + 1));
                acountList.add(acount);
            });

            list.add(thread);
            thread.start();

        }

        //  统计卖出票数和剩余票数
        for (Thread thread : list) {
            thread.join();
        }
        log.debug("剩余票数:{}", ticketWindow.getCount());
        log.debug("卖出的票数:{}", acountList.stream().mapToLong(i -> Objects.isNull(i) ? 0L : i).sum());

    }
}

class TicketWindow {
    // shengyu piao shumu
    private int count;

    public TicketWindow(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    //  shoupiao  --  acount:goumai shuliang
    public synchronized int sell(int acount) {
        if (this.count >= acount) {
            this.count -= acount;
            return acount;
        } else {
            return 0;
        }
    }

}
