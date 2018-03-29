package Chapter1;

import java.util.LinkedList;
import java.util.Queue;

/** Chapter1 Q4 猫狗队列问题
 *  实现一个猫狗 (均继承于Pet类) 队列, 能够拥有add, pollAll, pollCat, pollDog 等方法
 *  即既能无区别取出Pet, 又能区别取出Cat或Dog
 *
 *  使用了一个PetEnterQueue类, 对实际放入的Pet子类进行了包装和统一，并加入了一个count作为入队列顺序，从而保证出队列时有序
 *  Dog 和 Cat 分别放在一个 Queue<PetEnterQueue> 对象中，分别存储
 * Created by haohao on 2018/3/29.
 */
class Pet {
    private String type;

    public Pet(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}

class Dog extends Pet {
    public Dog() {
        super("Dog");
    }
}

class Cat extends Pet {
    public Cat() {
        super("Cat");
    }
}

class PetEnterQueue {
    private Pet pet;
    private long count;

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return this.pet;
    }

    public long getCount() {
        return this.count;
    }

    public String getPetType() {
        return this.pet.getType();
    }
}

public class DogCatQueue {
    private Queue<PetEnterQueue> dogQueue;
    private Queue<PetEnterQueue> catQueue;
    private long count;

    public DogCatQueue() {
        dogQueue = new LinkedList<>();      //LinkedList实现了Queue接口
        catQueue = new LinkedList<>();
        this.count = 0;
    }

    public void add(Pet pet) {
        if (pet.getType() == "Dog") {
            this.dogQueue.offer(new PetEnterQueue(pet, count++));
        } else if (pet.getType() == "Cat") {
            this.catQueue.offer(new PetEnterQueue(pet, count++));
        } else {
            throw new RuntimeException();
        }
    }

    public Pet pollAll() {
        if (!dogQueue.isEmpty() && !catQueue.isEmpty()) {
            if (dogQueue.peek().getCount() < catQueue.peek().getCount()) {
                return dogQueue.poll().getPet();
            } else {
                return catQueue.poll().getPet();
            }
        } else if (!dogQueue.isEmpty()) {
            return dogQueue.poll().getPet();
        } else if (!catQueue.isEmpty()) {
            return catQueue.poll().getPet();
        } else {
            throw new RuntimeException();
        }
    }

    public Dog pollDog() {
        if (!dogQueue.isEmpty()) {
            return (Dog) dogQueue.poll().getPet();
        } else {
            throw new RuntimeException("Dog queue is empty!");
        }
    }

    public Cat pollCat() {
        if (!catQueue.isEmpty()) {
            return (Cat) catQueue.poll().getPet();
        } else {
            throw new RuntimeException("Cat queue is empty!");
        }
    }

    public boolean isEmpty() {
        return (catQueue.isEmpty() && dogQueue.isEmpty());
    }

    public boolean isDogEmpty() {
        return dogQueue.isEmpty();
    }

    public boolean isCatEmpty() {
        return catQueue.isEmpty();
    }
}
