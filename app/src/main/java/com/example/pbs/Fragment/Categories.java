package com.example.pbs.Fragment;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.pbs.Modle.ProductItems;

import java.util.ArrayList;
import java.util.List;

public class Categories implements ParentObject {
    List<Object> productsList;
    String title;
    int category_imageView;

    public Categories( String title, int category_imageView) {
        this.title = title;
        this.category_imageView=category_imageView;

    }

    public Categories( String title) {
        this.title = title;

    }

    public List<Object> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Object> productsList) {
        this.productsList = productsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory_imageView() {
        return category_imageView;
    }

    public void setCategory_imageView(int category_imageView) {
        this.category_imageView = category_imageView;
    }

    @Override
    public List<Object> getChildObjectList() {
        return productsList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
    productsList=list;
    }
}


/*public class Categories  implements ParentObject {
    ArrayList<ProductItems> productsList;
    String title;
    int category_imageView;

    public Categories( String title, int category_imageView) {
        this.title = title;
        this.category_imageView=category_imageView;

    }

    public Categories( String title) {
        this.title = title;

    }

    public ArrayList<ProductItems> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<ProductItems> productsList) {
        this.productsList = productsList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCategory_imageView() {
        return category_imageView;
    }

    public void setCategory_imageView(int category_imageView) {
        this.category_imageView = category_imageView;
    }

    @Override
    public List<Object> getChildObjectList() {
        return productsList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        productsList=list;
    }
    @Override
    public void setChildObjectList(List<Object> list) {
        productsList=list;
    }
}*/
