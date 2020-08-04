package collection;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author shingo_ge
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        Queue<Client> clients = new PriorityQueue<>(new UserComparator());
        clients.add(new Client("张飞","A10"));
        clients.add(new Client("关羽","A2"));
        clients.add(new Client("刘备","V1"));

        System.out.println(clients.poll());
        System.out.println(clients.poll());
        System.out.println(clients.poll());
    }
}
