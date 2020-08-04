package collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author shingo_ge
 * 去除重复消息
 */
public class SetTest {
    public static void main(String[] args) {
        List<Message> receives = List.of(
                new Message(1,"你好，在吗？"),
                new Message(1,"你好，在吗？"),
                new Message(2,"找你有事"),
                new Message(2,"找你有事"),
                new Message(3,"看见麻烦回复我一下"),
                new Message(4,"谢谢"),
                new Message(5,"拜托了"),
                new Message(5,"拜托了")
        );
        List<Message> messageDisplayed = process(receives);
        for (Message message : messageDisplayed) {
            System.out.println(message.getText());
        }
    }

    private static List<Message> process(List<Message> receives) {
        Set<Integer> messagesSet = new HashSet<>();
        List<Message> messageList = new ArrayList<>();
        for (Message receive : receives) {
            //如果能放入set，说明该消息不重复，将其加入ArrayList
            if (messagesSet.add(receive.getSequence())){
                messageList.add(receive);
            }
        }
        return messageList;
    }
}
