package com.bb.hbx.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.hbx.R;
import com.bb.hbx.fragment.CustomersManagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/31.
 */

public class CustomerSearchEditText extends EditText {
    private Context context;
    private List<String> nameList = new ArrayList<>();
    private List<String> searchedList = new ArrayList<>();
    private CustomersManagerFragment fragment;
    private ListView lv_contact;
    private MyAdapter myAdapter;

    public CustomerSearchEditText(Context context) {
        super(context);
        this.context = context;
        fragment = new CustomersManagerFragment();
        this.nameList = fragment.nameTotalList;
        lv_contact = fragment.getLv_contact();
        Log.d("ttttt","-----------lv-----------" + lv_contact.toString());
        myAdapter = new MyAdapter(context,searchedList);
        Log.d("ttttt","-----------myAdapter-----------" + myAdapter.toString());
        lv_contact.setAdapter(myAdapter);
        initView();
    }

    public CustomerSearchEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void initView() {

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String content = CustomerSearchEditText.this.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    fragment.getLl_contact().setVisibility(VISIBLE);
                    if (searchedList.size() > 0) {
                        searchedList.clear();
                    }
                    for (int i = 0; i < nameList.size(); i++) {
                        if (nameList.get(i).contains(content)) {
                            searchedList.add(nameList.get(i));
                        }
                    }
                    myAdapter.notifyDataSetChanged();
                } else {
                    fragment.getLl_contact().setVisibility(INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private class MyAdapter extends BaseAdapter {
        private Context context;
        private List<String> list = new ArrayList<>();
        private LayoutInflater inflater;

        public MyAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_search_contact, parent, false);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                holder.tv_line = (TextView) convertView.findViewById(R.id.tv_line);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_name.setText(list.get(position));
            if (position == list.size() - 1) {
                holder.tv_line.setVisibility(INVISIBLE);
            }
            return convertView;
        }

        /**
         * listview 的holder类
         */
        public final class ViewHolder {
            public TextView tv_name;
            public TextView tv_line;
        }
    }

}
