package com.dongkyoo.gongzza.vos;

import java.util.Objects;

public class HashTag {

    private String color;
    private String content;

    public HashTag() {
    }

    public HashTag(String color, String content) {
        this.color = color;
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashTag)) return false;
        HashTag hashTag = (HashTag) o;
        return color.equals(hashTag.color) &&
                content.equals(hashTag.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, content);
    }

    @Override
    public String toString() {
        return "HashTag{" +
                "color='" + color + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
