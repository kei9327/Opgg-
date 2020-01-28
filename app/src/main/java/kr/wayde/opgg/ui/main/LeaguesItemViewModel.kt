package kr.wayde.opgg.ui.main

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView
import kr.wayde.opgg.R
import kr.wayde.opgg.domain.entity.Leagues
import kr.wayde.opgg.ui.BaseObservableViewModel

class LeaguesItemViewModel(val items: Leagues?) : BaseObservableViewModel() {

    companion object {
        @JvmStatic
        @BindingAdapter("app:tierImageUrl")
        fun tierImageUrl(view: SimpleDraweeView, imageUrl: String?) {
            imageUrl
                ?.takeIf { it.isNotEmpty() }
                ?.let {
                    view.setImageURI(it)
                }
        }

        @JvmStatic
        @BindingAdapter("app:nullCheckText")
        fun nullCheckText(view: TextView, text: String?) {
            text
                ?.takeIf { it.isNotEmpty() }
                ?.let {
                    view.text = it
                }
        }

        @JvmStatic
        @BindingAdapter("app:lpText")
        fun lpText(view: TextView, int: Int?) {
            int?.let {
                view.text = String.format("%,d", int) + "LP"
            }
        }

        @JvmStatic
        @BindingAdapter("app:winRateText")
        fun winRateText(view: TextView, leagues: Leagues?) {
            leagues?.let {
                view.text = String.format(view.resources.getString(R.string.lol_win_lose), leagues.wins, leagues.losses) + " (" + (leagues.wins.toFloat() / (leagues.wins + leagues.losses).toFloat() * 100).toInt().toString() + "%)"
            }
        }


    }

}