package ua.mai.fam.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {

    //https://stackoverflow.com/questions/6416706/easy-way-to-convert-iterable-to-collection#6416921
    public static <E> List<E> makeList(Iterable<E> iter) {
        List<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}
