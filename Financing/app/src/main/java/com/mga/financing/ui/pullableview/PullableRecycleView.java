package com.mga.financing.ui.pullableview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class PullableRecycleView extends RecyclerView implements Pullable
{

	public PullableRecycleView(Context context)
	{
		super(context);
	}

	public PullableRecycleView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public PullableRecycleView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}



	@Override
	public boolean canPullDown()
	{
//		if (getCount() == 0)
//		{
//			// 没有item的时候也可以下拉刷新
//			return true;
//		} else if (getFirstVisiblePosition() == 0
//				&& getChildAt(0).getTop() >= 0)
//		{
//			// 滑到ListView的顶部了
//			return true;
//		} else
//			return false;

		return true;
	}

	@Override
	public boolean canPullUp()
	{
//		if (getCount() == 0)
//		{
//			// 没有item的时候也可以上拉加载
//			return true;
//		} else if (getLastVisiblePosition() == (getCount() - 1))
//		{
//			// 滑到底部了
//			if (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()) != null
//					&& getChildAt(
//							getLastVisiblePosition()
//									- getFirstVisiblePosition()).getBottom() <= getMeasuredHeight())
//				return true;
//		}
		return false;
	}
}
