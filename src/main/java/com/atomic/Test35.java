package com.atomic;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;

@Slf4j(topic = "c.Test35")
public class Test35 {
    public static void main(String[] args) {
        DecimalAcount.demo(new DecimalAcountCas(new BigDecimal("10000")));

        AtomicLong aLong = new AtomicLong(10);

        log.debug("aLong : {}", new LongAdder());

        LongAdder longAdder = new LongAdder();
        for (int i = 0; i < 10; i++) {
            longAdder.add(i);
        }

        log.debug("longAdder = {}", longAdder);

        AtomicInteger aI = new AtomicInteger(12);
        log.debug(aI.toString());
    }
}

class DecimalAcountCas implements DecimalAcount {
    //  private BigDecimal bigBalance;
    private AtomicReference<BigDecimal> balance;


    public DecimalAcountCas(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withDrawn(BigDecimal acount) {
        while (true) {
            BigDecimal prev = balance.get();
            BigDecimal next = prev.subtract(acount);
            if (balance.compareAndSet(prev, next)) {
                break;
            }
        }
    }
}

interface DecimalAcount {
    // 获取余额
    BigDecimal getBalance();

    //  取款
    void withDrawn(BigDecimal acount);

    static void demo(DecimalAcount account) {
        List<Thread> ts = new ArrayList<Thread>();
        for (int i = 0; i < 1000; i++) {
            ts.add(new Thread(() -> {
                account.withDrawn(BigDecimal.TEN);
            }));
        }

        ts.forEach(Thread::start);
        ts.forEach(t -> {
            try {
                t.join();
            } catch (Exception e) {

            }
        });

        System.out.println(account.getBalance());

    }
}
