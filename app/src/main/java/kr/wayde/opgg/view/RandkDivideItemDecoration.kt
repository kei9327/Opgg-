package kr.wayde.opgg.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RandkDivideItemDecoration(val divHeight: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = divHeight * 2
            outRect.right = divHeight
        } else if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {
            outRect.right = divHeight * 2
        } else {
            outRect.right = divHeight
        }
    }
}