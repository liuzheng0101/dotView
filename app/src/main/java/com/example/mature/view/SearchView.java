package com.example.mature.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SearchView extends LinearLayout {
    private ImageView search_back;
    private TextView search_tv;
    private EditText search_ed;
    private int searchColor;
    private int searchSize;
    public SearchView(Context context) {
        this(context,null);
    }
    public SearchView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    /**
     *  数据处理集合
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        initAttrs(context,attrs);
        initView();
        initData();
    }
    /**
     * 初始化数据
     */
    private void initData() {
    }
    /**
     * 初始化组合子控件
     */
    private void initView() {
        View view=LayoutInflater.from(getContext()).inflate(R.layout.search_layout,this,true);
        search_back=view.findViewById(R.id.back);
        search_ed=view.findViewById(R.id.et_search);
        search_tv=view.findViewById(R.id.tv_search);

        search_tv.setTextColor(searchColor);  //动态设置颜色
        //返回点击事件
        search_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchViewCallback!=null){
                    searchViewCallback.onClickListener(v);
                }
            }
        });
        //搜索点击事件
        search_tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(search_ed.getText().toString())){
                    if (searchViewCallback!=null)
                        searchViewCallback.keywoidsEmpty();
                        return;
                }
                if (searchViewCallback !=null) {
                    searchViewCallback.searchClick(search_ed.getText().toString());
                }
            }
        });
    }
    /**
     * 初始化自定义属性
     * @param context
     * @param attrs
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.SearchView);
        searchColor = typedArray.getColor(R.styleable.SearchView_searchColor,Color.BLUE);
        //回收资源
        if (typedArray!=null){
            typedArray.recycle();
        }
    }

    //自定义接口
    private SearchViewCallback searchViewCallback;
    /** 方便调用
     *
     * @param searchViewCallback
     */
    public void setSearchViewCallback(SearchViewCallback searchViewCallback) {
        this.searchViewCallback = searchViewCallback;
    }

    public interface SearchViewCallback{
       void onClickListener(View v);
       void searchClick(String keywords);
       void keywoidsEmpty();
    }

}
