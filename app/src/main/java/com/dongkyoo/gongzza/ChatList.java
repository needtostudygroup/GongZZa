package com.dongkyoo.gongzza;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;

public class ChatList<T> extends ArrayList<T> {

    /**
     * 채팅 데이터 넣을 때 역으로 넣는 메소드
     * 최신 채팅을 넣으려고 시간 데이터를 기준으로 내림차순 정렬하여 데이터를 뽑음
     * 그래서 11월 10일, 9일, 8, 7, ... 1일 데이터가 있다고 할 때
     * 리스트의 0번째에 10일, 1번째에 9일 순으로 들어가 있음
     * 그래서 이걸 그냥 리스트뷰에 띄우면 최신 채팅 데이터가 위로 올라감
     * 하지만 채팅은 최신일수록 아래에 있는게 좋음
     * 따라서 내림차순 정렬된 데이터를 역으로 뒤집어서 넣는 것을 편하게 하기위해 만듬
     */
    @Override
    public boolean addAll(@NonNull Collection<? extends T> c) {
        Object[] arr = c.toArray();
        T[] array = (T[]) arr;
        for (int i = array.length - 1; i >= 0; i--) {
            add(array[i]);
        }
        return true;
    }
}
