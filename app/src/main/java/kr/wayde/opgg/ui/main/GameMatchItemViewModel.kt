package kr.wayde.opgg.ui.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import kr.wayde.opgg.R
import kr.wayde.opgg.databinding.ItemBuildItemBinding
import kr.wayde.opgg.domain.entity.Games
import kr.wayde.opgg.domain.entity.Items
import kr.wayde.opgg.domain.entity.Spells
import kr.wayde.opgg.ui.BaseObservableViewModel

class GameMatchItemViewModel(val game: Games) : BaseObservableViewModel() {
    val buildItemAdapter: BuildItemAdapter = BuildItemAdapter(game.items)

    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun loadImage(view: SimpleDraweeView, imageUrl: String) {
            view.setImageURI(imageUrl)
        }

        @JvmStatic
        @BindingAdapter("app:loadSpellsImage")
        fun loadSpellsImage(view: SimpleDraweeView, spells: Spells) {
            view.setImageURI(spells.imageUrl)
        }

        @JvmStatic
        @BindingAdapter("app:integerText")
        fun integerText(view: TextView, int: Int) {
            view.text = int.toString()
        }

        @JvmStatic
        @BindingAdapter("app:gameLengthText")
        fun gameLengthText(view: TextView, int: Int) {
            view.text = String.format("%02d:%02d", int.div(60), int.rem(60))
        }

        @JvmStatic
        @BindingAdapter("app:visibleText")
        fun visibleText(view: TextView, string: String) {
            view.visibility = View.INVISIBLE
            string
                ?.takeIf { it.isNotEmpty() }
                ?.let {
                    view.text = string
                    view.visibility = View.VISIBLE
                }
        }

        @JvmStatic
        @BindingAdapter("app:contributionForKillRateText")
        fun contributionForKillRateText(view: TextView, string: String) {
            view.text = "킬관여 $string"

        }

        @JvmStatic
        @BindingAdapter("app:gameTypetext")
        fun gameTypetext(view: TextView, string: String) {
            view.run {
                when(string) {
                        "Ranked Solo"-> text = "솔랭"
                    }
            }
        }

        @JvmStatic
        @BindingAdapter("app:crateDateText")
        fun crateDateText(view: TextView, createDate: Long) {
            view.text = timeDiff(createDate)
        }

        @JvmStatic
        @BindingAdapter("app:adapter")
        fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            view.adapter = adapter
        }

        @JvmStatic
        @BindingAdapter("app:isWinText")
        fun visibleText(view: TextView, isWIn: Boolean) =
            if (isWIn) view.text = "승" else view.text = "패"

        @SuppressLint("ResourceAsColor")
        @JvmStatic
        @BindingAdapter("app:isWin")
        fun visibleText(view: View, isWIn: Boolean) =
            if (isWIn) view.setBackgroundColor(view.resources.getColor(R.color.soft_blue))
            else view.setBackgroundColor(view.resources.getColor(R.color.darkish_pink))

        enum class TimeValue(val value: Int, val maximum: Int, val msg: String) {
            SEC(60, 60, "분 전"),
            MIN(60, 24, "시간 전"),
            HOUR(24, 30, "일 전"),
            DAY(30, 12, "달 전"),
            MONTH(12, Int.MAX_VALUE, "년 전")
        }

        private fun timeDiff(timeStamp: Long): String {
            val curTime = System.currentTimeMillis() / 1000
            var diffTime = (curTime - timeStamp)
            var msg = ""
            if (diffTime < TimeValue.SEC.value)
                msg = "방금 전"
            else {
                for (i in TimeValue.values()) {
                    diffTime /= i.value
                    if (diffTime < i.maximum) {
                        msg = diffTime.toString() + i.msg
                        break
                    }
                }
            }
            return msg
        }
    }

    inner class BuildItemAdapter(private val items: List<Items>) :
        RecyclerView.Adapter<BuildItemAdapter.BuildItemHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildItemHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_build_item, parent, false)
            return BuildItemHolder(binding as ItemBuildItemBinding)
        }

        override fun onBindViewHolder(holder: BuildItemHolder, position: Int) {
            if (items.size - 1 >= position) {
                holder.itemBuildItemBinding.viewModel = BuildItemViewModel(items[position].imageUrl)
            } else {
                holder.itemBuildItemBinding.viewModel = BuildItemViewModel("")
            }
        }

        override fun getItemCount(): Int = 7

        inner class BuildItemHolder(val itemBuildItemBinding: ItemBuildItemBinding) :
            RecyclerView.ViewHolder(itemBuildItemBinding.root)
    }
}