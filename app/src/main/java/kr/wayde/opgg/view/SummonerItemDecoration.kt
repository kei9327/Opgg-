package kr.wayde.opgg.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SummonerItemDecoration(val divHeight: Int): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.bottom = 2 * divHeight
        } else {
            outRect.bottom = divHeight
        }
    }
}