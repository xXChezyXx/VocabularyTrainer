package test.java;

import main.java.utils.DynArray;

public class Test {

    public static void main(String[] args) {
        DynArray dynArray = new DynArray();
        dynArray.append(1);
        dynArray.append(2);
        dynArray.append(3);
        dynArray.append(4);
        dynArray.append(5);
        dynArray.append(6);
        dynArray.append(7);
        dynArray.append(8);
        dynArray.append(9);
        dynArray.append(10);

        dynArray.delete(4);
        for (int i = 0; i < dynArray.getLength(); i++) {
            System.out.println(dynArray.getItem(i));
        }
    }

}
