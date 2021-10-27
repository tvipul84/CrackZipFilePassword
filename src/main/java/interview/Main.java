package interview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Number> linkedList1 = new LinkedList<>();
        linkedList1.add(1);
        linkedList1.add(2);
        linkedList1.add(3);
        linkedList1.add(4);
        List<Number> linkedList2 = new LinkedList<>();
        linkedList2.add(5);
        linkedList2.add(6);
        linkedList2.add(7);
        linkedList2.add(8);
        linkedList1.addAll(linkedList2);
        List<Number> sortedLinkedList = linkedList1.stream().sorted().toList();
        for(Number num: sortedLinkedList){
            System.out.print(num + " ");
        }

    }
}
