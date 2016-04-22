package com.idreamsky.dreamroom.util;

import com.idreamsky.dreamroom.model.SortModel;

import java.util.Comparator;

/**
 * Created by magical on 2016/4/22.
 */
public class PinyinComparator implements Comparator<SortModel> {

    @Override
    public int compare(SortModel m1, SortModel m2) {
        // TODO Auto-generated method stub
        if (m1.getSortLetters().equals("#")) {
            return 1;
        } else if (m2.getSortLetters().equals("#")) {
            return -1;
        } else {
            return m1.getSortLetters().compareTo(m2.getSortLetters());
        }
    }

}
