package ru.mentee.power.collections.base;

import java.util.List;
import java.util.ArrayList;

public class ListUtils {
    /**
     * Объединяет два списка строк в один, удаляя дубликаты.
     *
     * @param list1 Первый список строк
     * @param list2 Второй список строк
     * @return Новый список, содержащий все уникальные элементы из обоих списков
     */
    public static List<String> mergeLists(List<String> list1, List<String> list2) {
        if ((list1 == null || list1.isEmpty()) && (list2 == null || list2.isEmpty())) {
            return new ArrayList<>();
        }
        if (list1 == null) return list2;
        else if (list2==null) return list1;

        List<String> merge = new ArrayList<>(list1);
        for (String elem : list2){
            if (!merge.contains(elem)) merge.add(elem);
        }
        merge.remove(null);
        return merge;
    }
}