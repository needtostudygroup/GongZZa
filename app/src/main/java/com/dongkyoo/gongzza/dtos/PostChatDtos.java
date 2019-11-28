package com.dongkyoo.gongzza.dtos;

import java.util.Collections;
import java.util.List;

public class PostChatDtos {

    public static void merge(List<PostChatDto> src, List<PostChatDto> dest) {
        for (PostChatDto postChatDto : src) {
            boolean isFound = false;
            for (PostChatDto destPostChatDto : dest) {
                if (postChatDto.getId() == destPostChatDto.getId()) {
                    isFound = true;
                    destPostChatDto.getChatLogList().addAll(postChatDto.getChatLogList());
                    destPostChatDto.sortChatLogList();
                    break;
                }
            }

            if (!isFound) {
                dest.add(postChatDto);
            }
        }

        Collections.sort(dest);
    }
}
