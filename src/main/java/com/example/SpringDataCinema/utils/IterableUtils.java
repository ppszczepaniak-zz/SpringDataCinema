package com.example.SpringDataCinema.utils;

import java.util.ArrayList;
import java.util.List;

public class IterableUtils {

    public static <T> List<T> iterableToList(Iterable<T> iterable) {
        List<T> resultList = new ArrayList<>();


//        OR using Streams:
//        return StreamSupport
//                .stream(iterable.spliterator(), false)
//                .collect(Collectors.toList());

        iterable.forEach(t -> resultList.add(t));
        return resultList;
    }
}
