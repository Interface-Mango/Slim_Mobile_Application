package com.example.hyejin.slimtest;

/**
 * Created by Minki on 2016-07-24.
 */
public class GetDataList {
    private String[] mData;
    final int columnCnt = 1;

    public GetDataList(String[] data) {
        mData = data;
    }

    public GetDataList(String id) {
        mData = new String[columnCnt];
        mData[0] = id;
    }

    public String[] getmData() {
        return mData;
    }

    public String getData(int index) {
        return mData[index];
    }

    public void setData(String[] data) {
        mData = data;
    }
}
