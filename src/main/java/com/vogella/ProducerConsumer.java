package com.vogella;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {

    public static void main(String[] args) {
        Runnable consumers[] = new ConsumerThread[5];
        Runnable producers[] = new ProduceThread[5];

        Thread consumersThread[] = new Thread[5];
        Thread producersThread[] = new Thread[5];

        ProductFactory factory = new ProductFactory();
        for (int i = 0; i < consumers.length; i++) {
            consumers[i] = new ConsumerThread(factory);
            consumersThread[i] = getThread(consumers[i]);
            consumersThread[i].start();
        }

        for (int i = 0; i < producers.length; i++) {
            producers[i] = new ProduceThread(factory);
            producersThread[i] = getThread(producers[i]);
            producersThread[i].start();
        }



        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < consumersThread.length; i++) {
            consumersThread[i].interrupt();
            producersThread[i].interrupt();
        }


        for (int i = 0; i < producers.length; i++) {
            try{
                consumersThread[i].join();
                producersThread[i].join();
            }catch(InterruptedException e){
            }
        }
        System.out.println("!!" + factory.products.size());
    }

    private static Thread getThread(Runnable r) {
        return new Thread(r);
    }
}

class ConsumerThread implements Runnable {

    private ProductFactory factory;

    public ConsumerThread(ProductFactory factory) {
        super();
        this.factory = factory;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int random = (int) (Math.random() * 4) + 1;
            try {
                factory.consume(random);
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}

class ProduceThread implements Runnable {

    private ProductFactory factory;

    public ProduceThread(ProductFactory factory) {
        super();
        this.factory = factory;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int random = (int) (Math.random() * 4) + 1;
            try {
                factory.produce(random);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

}

class ProductFactory {
    public static final int MAX_SIZE = 10;
    List<String> products = new ArrayList<String>();

    public synchronized void produce(int size) throws InterruptedException {
        while (products.size() + size > MAX_SIZE) {
            try {
                System.out.println("current size:" + products.size() + " cannot add " + size + " more");
                wait();
            } catch (InterruptedException e) {
                throw e;
            }
        }

        for (int i = 0; i < size; i++) {
            products.add("test");
        }
        System.out.println(size + " is added,current size:" + products.size());
        notifyAll();
    }

    public synchronized List<String> consume(int size) throws InterruptedException {
        while (products.size() - size < 0) {
            try {
                System.out.println("current size:" + products.size() + " cannot consume " + size + " more");
                wait();
            } catch (InterruptedException e) {
                throw e;
            }
        }

        List<String> ret = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            ret.add(products.remove(0));
        }
        System.out.println(size + " is consumed,current size:" + products.size());
        notifyAll();
        return ret;
    }
}