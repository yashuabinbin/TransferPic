package com.pic.main;

import java.io.File;
import java.util.List;

/**
 * Created by lbb on 2017/4/12.
 */
public class FileInfo {

    private int num;

    private String picMd5;

    private long picLen;

    private List<File> pics;

    public long getPicLen() {
        return picLen;
    }

    public void setPicLen(long picLen) {
        this.picLen = picLen;
    }

    public String getPicMd5() {
        return picMd5;
    }

    public void setPicMd5(String picMd5) {
        this.picMd5 = picMd5;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<File> getPics() {
        return pics;
    }

    public void setPics(List<File> pics) {
        this.pics = pics;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "num=" + num +
                ", picMd5='" + picMd5 + '\'' +
                ", picLen=" + picLen +
                ", pics=" + pics +
                '}';
    }
}
