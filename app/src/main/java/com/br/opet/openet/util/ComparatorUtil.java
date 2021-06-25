package com.br.opet.openet.util;

import com.br.opet.openet.model.FriendModel;

import java.util.Comparator;

public class ComparatorUtil {

    public static class SortByFriendName implements Comparator<FriendModel> {
        public int compare(FriendModel a, FriendModel b) {
            return a.getName().compareTo(b.getName());
        }
    }
}
