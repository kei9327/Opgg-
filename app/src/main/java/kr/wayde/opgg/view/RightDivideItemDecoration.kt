package kr.wayde.opgg.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RightDivideItemDecoration(val divHeight: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1) {
            outRect.right = divHeight
        }
    }
}