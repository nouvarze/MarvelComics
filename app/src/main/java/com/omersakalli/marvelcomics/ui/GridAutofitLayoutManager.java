package com.omersakalli.marvelcomics.ui;

import android.content.Context;
import android.util.TypedValue;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//Borrowed and changed
//from: https://stackoverflow.com/questions/26666143/recyclerview-gridlayoutmanager-how-to-auto-detect-span-count @s.maks
public class GridAutofitLayoutManager extends GridLayoutManager
{
    private int columnHeight;
    private boolean isColumnHeightChanged = true;
    private int lastWidth;
    private int lastHeight;

    public GridAutofitLayoutManager(@NonNull final Context context, final int columnHeight) {
        /* Initially set spanCount to 1, will be changed automatically later. */
        super(context, 1);
        setColumnHeight(checkedColumnHeight(context, columnHeight));
    }

    public GridAutofitLayoutManager(
            @NonNull final Context context,
            final int columnHeight,
            final int orientation,
            final boolean reverseLayout) {

        /* Initially set spanCount to 1, will be changed automatically later. */
        super(context, 1, orientation, reverseLayout);
        setColumnHeight(checkedColumnHeight(context, columnHeight));
    }

    private int checkedColumnHeight(@NonNull final Context context, int columnHeight) {
        if (columnHeight <= 0) {
            /* Set default columnWidth value (48dp here). It is better to move this constant
            to static constant on top, but we need context to convert it to dp, so can't really
            do so. */
            columnHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48,
                    context.getResources().getDisplayMetrics());
        }
        return columnHeight;
    }

    public void setColumnHeight(final int newColumnHeight) {
        if (newColumnHeight > 0 && newColumnHeight != columnHeight) {
            columnHeight = newColumnHeight;
            isColumnHeightChanged = true;
        }
    }

    @Override
    public void onLayoutChildren(@NonNull final RecyclerView.Recycler recycler, @NonNull final RecyclerView.State state) {
        final int width = getWidth();
        final int height = getHeight();
        if (columnHeight > 0 && width > 0 && height > 0 && (isColumnHeightChanged || lastWidth != width || lastHeight != height)) {
            final int totalSpace;
            if (getOrientation() == VERTICAL) {
                totalSpace = width - getPaddingRight() - getPaddingLeft();
            } else {
                totalSpace = height - getPaddingTop() - getPaddingBottom();
            }
            final int spanCount = Math.max(1, totalSpace / columnHeight);
            setSpanCount(spanCount);
            isColumnHeightChanged = false;
        }
        lastWidth = width;
        lastHeight = height;
        super.onLayoutChildren(recycler, state);
    }
}