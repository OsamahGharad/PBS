package com.example.pbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.pbs.Fragment.Categories;
import com.example.pbs.Modle.Product;
import com.example.pbs.Modle.ProductItems;
import com.example.pbs.R;

import java.util.List;

public class Exbandable_Category extends ExpandableRecyclerAdapter<Exbandable_Category.CategoryViewHolder,Exbandable_Category.productViewHolder> {
   LayoutInflater inflater;

    private ProductItems[] listdata;

    public Exbandable_Category(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater=LayoutInflater.from(context);
    }

    @Override
    public CategoryViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view=inflater.inflate(R.layout.card_category,null);
        CategoryViewHolder categoryViewHolder=new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public productViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
       // View view=inflater.inflate(R.layout.card_product,null);
        View view=inflater.inflate(R.layout.fragment_product_items,null);
        productViewHolder product_ViewHolder=new productViewHolder(view);
        return product_ViewHolder;
    }

    @Override
    public void onBindParentViewHolder(CategoryViewHolder categoryViewHolder, int i, Object o) {
        Categories categories=(Categories)o;
        categoryViewHolder.category_title.setText(categories.getTitle());

    }

    @Override
    public void onBindChildViewHolder(productViewHolder productViewHolder, int i, Object o) {
      /*  Product product=(Product)o;
        productViewHolder.product1.setText(product.getProdct1());
        productViewHolder.product2.setText(product.getProduct2());*/

        ProductItems product=(ProductItems)o;
        productViewHolder.product.setText(product.getP_product());
        productViewHolder.price.setText(product.getP_price());

    }

    class CategoryViewHolder extends ParentViewHolder {
    public TextView category_title;
    public ImageView category_image_view;


        public CategoryViewHolder(View itemView) {
            super(itemView);
            category_title=itemView.findViewById(R.id.Category_list_title);
        }
    }
    class  productViewHolder extends ChildViewHolder {

    //  public  TextView product1,product2;

        public TextView product,price;

        public productViewHolder(View itemView) {
            super(itemView);
          /*  product1=itemView.findViewById(R.id.product1);
            product2=itemView.findViewById(R.id.product2);*/

            this.product = (TextView) itemView.findViewById(R.id.productN);
            this.price = (TextView) itemView.findViewById(R.id.productPrice);

        }
    }
}
