package com.dongkyoo.gongzza;

import com.dongkyoo.gongzza.vos.HashTag;
import com.dongkyoo.gongzza.vos.Post;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 작성자: 이동규
 * 가짜 데이터를 리턴해주는 클래스. 테스트를 위해 사용
 */
public class MockData {

    public static List<String> getMockHashTagList() {
        return Arrays.asList("운동", "게임", "노래방", "밥", "abc", "def", "ghi");
    }

    public static List<Post> getMockPostList() {
        return Arrays.asList(
                new Post(1, "카페가실분", "케이크팝 가실분 구해요", new Date(), new Date(), new Date(), 4, 3, null, Arrays.asList(new HashTag("#FFAAAA", "여자만"))),
                new Post(2, "농구하실분", "농구 하실분 한 분 구해요", new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3), new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 5), new Date(), 4, 4, null, Arrays.asList(new HashTag("#FFAAAA", "180이상"), new HashTag("#FFAAAA", "유쾌"), new HashTag("#FFAAAA", "상쾌"), new HashTag("#FFAAAA", "통쾌"), new HashTag("#FFAAAA", "엄청긴                                               텍스트")))
        );
    }
}
