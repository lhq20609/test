package lhq.test;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.CountDownLatch;

enum CountryEnum {
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕")
    ,FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    private Integer code;
    private String message;
    CountryEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static CountryEnum ForEachCountryEnum(int index){
        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum elements: values) {
            if(index == elements.getCode()){
                return elements;
            }
        }
        return null;
    }
}

public class CountDownLatchDemo {

    public static final int NUM=6;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for (int i = 1; i <= NUM; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"国被灭");
                countDownLatch.countDown();
            },CountryEnum.ForEachCountryEnum(i).getMessage()).start();
        }
        countDownLatch.await();
        System.out.println("秦国一统天下");
    }
}
