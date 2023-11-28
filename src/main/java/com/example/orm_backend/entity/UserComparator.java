package com.example.orm_backend.entity;

import java.util.Comparator;

public class UserComparator implements Comparator<UserWithMoney> {
    @Override
    public int compare(UserWithMoney o1, UserWithMoney o2) {
        return o2.money - o1.money;
    }
}
