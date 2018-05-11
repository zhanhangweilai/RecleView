package com.example.lh.recycletest;

import android.app.Activity;
import android.app.Notification;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLinearLayoutManager, mLinearLayoutManager1;
    private MyAdapter myAdapter;
    private ArrayList<String> arrayList;
    private Handler mHandler;
    private Button button;
    private ListView mListView;
    private SwipeRefreshLayout swipeRefreshLayout;
    Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arrayList = new ArrayList<String>();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
       // swipeRefreshLayout.setColorSchemeColors();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                arrayList.add("添加");
                myAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        swipeRefreshLayout.requestDisallowInterceptTouchEvent(true);
        mHandler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };
        TextView tv = new TextView(this);
        tv.setWidth(20);
        tv.setHeight(30);
        tv.setText("pullRefresh");
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mListView= (ListView)findViewById(R.id.lv);
        mRecyclerView = (RecyclerView) findViewById(R.id.linear_recycler);
        button = (Button) findViewById(R.id.btn);
        mLinearLayoutManager = new LinearLayoutManager(this,1,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        initData();
        myAdapter = new MyAdapter(this, arrayList);
        mRecyclerView.setAdapter(myAdapter);
        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            int space1;
            public void SpacesItemDecoration(int space) {
                space1 = space;
            }
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 1;
                outRect.right = 1;
                outRect.bottom = 10;
                if (parent.getChildAdapterPosition(view) != 0){
                    outRect.top = 0;
                } else {
                    outRect.top =1;
                }
            }

            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
                int childCount = parent.getChildCount();

                for ( int i = 0; i < childCount; i++ ) {
                    View view = parent.getChildAt(i);

                    int index = parent.getChildAdapterPosition(view);
                    //第一个ItemView不需要绘制
                    if (index == 0) {
                        continue;
                    }

                    float dividerTop = view.getTop() - 20;
                    float dividerLeft = parent.getPaddingLeft();
                    float dividerBottom = view.getTop();
                    float dividerRight = parent.getWidth() - parent.getPaddingRight();

                    c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, mPaint);
                }
            }
        };
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration( itemDecoration);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayList.remove(2);
                myAdapter.notifyDataSetChanged();
                Log.i("lihang2","arrayList.size ="+arrayList.size());
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    private void initData() {
        for (int i = 0;i<100; i++) {
            arrayList.add(String.valueOf(i));
        }
    }
}


