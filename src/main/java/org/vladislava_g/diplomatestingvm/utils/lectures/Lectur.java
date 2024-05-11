package org.vladislava_g.diplomatestingvm.utils.lectures;

public record Lectur(String header, String underHeader, String content) {
    @Override
    public String toString() {
        return "Lectur{" +
                "header='" + header + '\'' +
                ", addHeader='" + underHeader + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
