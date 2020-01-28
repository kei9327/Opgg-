package kr.wayde.opgg.ui.main

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import kr.wayde.opgg.domain.entity.Champions
import kr.wayde.opgg.ui.BaseObservableViewModel

class ChampionWinRateItemViewModel(val items: Champions?) : BaseObservableViewModel() {

    companion object {
        @JvmStatic
        @BindingAdapter("app:championImageUrl")
        fun championImageUrl(view: SimpleDraweeView, championImageUrl: String?) {
            championImageUrl
                ?.takeIf { it.isNotEmpty() }
                ?.let {
                    view.setImageURI(championImageUrl)
                }
        }

        @JvmStatic
        @BindingAdapter("app:txtMostRate")
        fun txtWinRate(view: TextView, items: Champions?) {
            items?.let {
                view.text = ((it.wins.toFloat() / it.games.toFloat()) * 100).toInt().toString()
            }
        }
    }

}