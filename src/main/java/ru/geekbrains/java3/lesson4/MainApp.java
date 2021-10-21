package ru.geekbrains.java3.lesson4;

public class MainApp {
    static Object obj = new Object();
    static volatile char controlVar = 'A';

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (obj) {
                        while (controlVar != 'A') {
                            obj.wait();
                        }
                        System.out.print("A ");
                        controlVar = 'B';
                        obj.notifyAll();
                    }
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (obj) {
                        while (controlVar != 'B') {
                            obj.wait();
                        }
                        System.out.print("B ");
                        controlVar = 'C';
                        obj.notifyAll();
                    }
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    synchronized (obj) {
                        while (controlVar != 'C') {
                            obj.wait();
                        }
                        System.out.print("C ");
                        controlVar = 'A';
                        obj.notifyAll();
                    }
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
/*
По второму заданию думаю нет смысла добалять ExecutorService.
Он скорее всего здесь понадобился бы если мы передавали друг другу какие то тяжелые файлы одновременно
(аудио сообщения, фото, видео)
 */

