package com.development.task.mvvmproductapp.data.local;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductData {
    @SerializedName("data")
    @Expose
    private List<Product> data = null;
    @SerializedName("count")
    @Expose
    private Integer count;

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
