package viewpager.lingdian.com.viewpagerdouble.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import viewpager.lingdian.com.viewpagerdouble.R;


/**
 * Created by yukuo on 2016/4/29.
 * 这是一个recycleview的刷新的头布局
 */
public class YRecycleviewRefreshHeadView extends LinearLayout {
    //刷新头布局
    RelativeLayout mContentView;
    private int mMeasuredHeight;
    private int mStatus;
    //正在刷新的状态
    public final static int STATE_REFRESHING = 2;
    //刷新结束的状态
    public final static int STATE_DONE = 3;
    //没有任何的状态
    public final static int STATE_NORMAL = 0;
    //准备刷新的状态
    private final int STATE_RELEASE_TO_REFRESH = 1;

    public YRecycleviewRefreshHeadView(Context context) {
        super(context);
        initView(context);
    }

    public YRecycleviewRefreshHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    /**
     * 初始化
     */
    private void initView(Context context) {
        mContentView = (RelativeLayout) View.inflate(context, R.layout.layout_header, null);
        //宽高
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        //距离父布局的距离
        lp.setMargins(0, 0, 0, 0);
        //设置布局的宽高的属性
        this.setLayoutParams(lp);
        //设置内容距离布局的边界的尺寸
        this.setPadding(0, 0, 0, 0);
        //添加内容布局并且设置宽是屏幕的宽.高为0
        addView(mContentView, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        //设置内容位于布局的下方
        setGravity(Gravity.BOTTOM);
        //查找头布局控件
        //测量
        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //获取高度
        mMeasuredHeight = getMeasuredHeight();
    }

    /**
     * 设置这个刷新状态的方法
     */
    public void setState(int status) {
        //如果状态一致就不要更改
        if (status == mStatus) {
            return;
        }
        /**
         * 设置当前布局格局状态隐藏和显示
         */
        //设置刷新状态
        if (status == STATE_REFRESHING) {
            //清除箭头的动画效果并且隐藏
            //展示进度条
        } else if (status == STATE_DONE) {
            //刷新结束状态
        } else {
            //正常状态隐藏进度条显示箭头
        }
        /**
         * 根据状态进行动画的调整
         */
        switch (status) {
            case STATE_NORMAL:
                //如果当前状态是准备做刷新就开始动画
                if (mStatus == STATE_RELEASE_TO_REFRESH) {
                }
                //如果当前状态正在刷新,就停止动画
                if (mStatus == STATE_REFRESHING) {
                }
                break;

            case STATE_RELEASE_TO_REFRESH:
                //如果当前状态不是准备去刷新
                if (mStatus != STATE_RELEASE_TO_REFRESH) {
                }
                break;
            case STATE_REFRESHING:
                break;
            case STATE_DONE:
                break;
            default:
        }
        mStatus = status;
    }

    /**
     * 返回当前的状态
     * @return
     */
    public int getStatus() {
        return mStatus;
    }

    /**
     * 释放意图
     *
     * @return
     */
    public boolean releaseAction() {
        //是否在刷新
        boolean isOnRefresh = false;
        //获取显示的高度
        int height = getVisibleHeight();
        if (height == 0) // not visible.
            isOnRefresh = false;//没有刷新
        if (getVisibleHeight() > mMeasuredHeight && mStatus < STATE_REFRESHING) {
            setState(STATE_REFRESHING);
            isOnRefresh = true;//正在刷新
        }
        // refreshing and header isn't shown fully. do nothing.
        if (mStatus == STATE_REFRESHING && height <= mMeasuredHeight) {
            //return;
        }
        int destHeight = 0; // default: scroll back to dismiss header.
        // is refreshing, just scroll back to show all the header.
        if (mStatus == STATE_REFRESHING) {
            destHeight = mMeasuredHeight;
        }
        smoothScrollTo(destHeight);

        return isOnRefresh;
    }

    /**
     * 移动距离
     *
     * @param delta
     */
    public void onMove(float delta) {
        if (getVisibleHeight() > 0 || delta > 0) {
            setVisibleHeight((int) delta + getVisibleHeight());
            if (mStatus <= STATE_RELEASE_TO_REFRESH) { // 未处于刷新状态，更新箭头
                if (getVisibleHeight() > mMeasuredHeight) {
                    setState(STATE_RELEASE_TO_REFRESH);
                } else {
                    setState(STATE_NORMAL);
                }
            }
        }
    }

    /**
     * 这是一个让布局高度改变的方法
     */
    public void smoothScrollTo(int Height) {
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), Height);
        animator.setDuration(300).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    /**
     * 设置布局高度为0,并设置状态为正常状态
     */
    public void reset() {
        smoothScrollTo(0);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                setState(STATE_NORMAL);
            }
        }, 500);
    }

    /**
     * 刷新完成
     */
    public void refreshComplete() {
        setState(STATE_DONE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                reset();
            }
        }, 200);
    }

    /**
     * 获取显示的高度
     *
     * @return
     */
    public int getVisibleHeight() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        return lp.height;
    }

    /**
     * 设置显示的高度
     *
     * @param height
     */
    public void setVisibleHeight(int height) {
        if (height < 0) height = 0;
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.height = height;
        mContentView.setLayoutParams(lp);
    }

}
