package com.example.ezcomposetutorial;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by Ervin on 2023/12/25
 **/
class ExampleUnitTest2 {

    @Test
    void test() {
        //PECS

        //读 生产者
        List<String> list1 = new ArrayList<>();
        list1.add("abc");
        List<? extends String> list2 = new ArrayList<>();
        //list2.add("def");


        //写，消费者
        List<? super Object> list = new ArrayList<>();
        //list = list1;
        list.add("abc");


        List<Dog> dogs = new ArrayList<Dog>();
        dogs.add(new Dog());
        producerExtends(dogs);

        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal());
        animals.add(new BigDog());
        consumerSuper(animals);
    }

    /**
     * 因为这个特性，所以说<? extnebds T>适合进行读取操作，不能写入，也就是PECS中的PE(Producer Extends)
     * @param animals
     */
    public void producerExtends(List<? extends Animal> animals) {
        // 读取animals
        Animal a1 = animals.get(0);

        // 添加animal
        //animals.add(new Animal());     //编译错
        //animals.add(new Dog());        //编译错
    }

    /**
     * <? super T>适合进行写操作，不能读取，也就是PECS中的CS(Consumer Super)
     * @param dogs
     */
    public static void consumerSuper(List<? super SmallDog> dogs) {
        dogs.add(new SmallDog());

        //dogs.add(new Dog());         //编译错
        //SmallDog dog1 = dogs.get(0);       //编译错

    }
}

class Animal {
}

class Dog extends Animal {
}

class SmallDog extends Dog {
}

class BigDog extends Dog {
}

class Cat extends Animal {

}

