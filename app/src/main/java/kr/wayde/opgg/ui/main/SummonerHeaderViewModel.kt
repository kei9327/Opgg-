package kr.wayde.opgg.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import kr.wayde.opgg.R
import kr.wayde.opgg.databinding.ItemChampionWinRateBinding
import kr.wayde.opgg.domain.entity.*
import kr.wayde.opgg.domain.interactor.usecases.GetMatchesUseCase
import kr.wayde.opgg.domain.interactor.usecases.GetSummonerInfoUseCase
import kr.wayde.opgg.ui.BaseObservableViewModel

class SummonerHeaderViewModel(private val getSummonerInfoUseCase: GetSummonerInfoUseCase) : BaseObservableViewModel() {
//    val mostChampionAdapter: MostChampionAdapter = MostChampionAdapter()
    val summonerResult = MutableLiveData<Summoner>()

    fun requestSummonerInfo(summoner:String) {
        compositeDisposable.add(
            getSummonerInfoUseCase.get(summoner).subscribe { it: Summoner ->
                summonerResult.postValue(it)
            }
        )
    }

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
        @BindingAdapter("app:winLoseText")
        fun winLoseText(view: TextView, position:  Positions) {
            view.text = String.format(view.resources.getString(R.string.lol_win_lose), position.wins, position.losses)
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
        @BindingAdapter("app:adapter")
        fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            view.adapter = adapter
        }


    }

//    inner class MostChampionAdapter() :
//        RecyclerView.Adapter<MostChampionAdapter.ChompionWinRateHolder>() {
//        private val items: MutableList<Champions> = ArrayList()
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChompionWinRateHolder {
//            val inflater = LayoutInflater.from(parent.context)
//            val binding: ViewDataBinding =
//                DataBindingUtil.inflate(inflater, R.layout.item_champion_win_rate, parent, false)
//            return ChompionWinRateHolder(binding as ItemChampionWinRateBinding)
//        }
//
//        override fun onBindViewHolder(holder: ChompionWinRateHolder, position: Int) {
//            if (items.size - 1 >= position) {
//                holder.championWinRateBinding.viewModel = ChampionWinRateItemViewModel(items[position])
//            } else {
//                holder.championWinRateBinding.viewModel = ChampionWinRateItemViewModel(null)
//            }
//        }
//
//        override fun getItemCount(): Int = 2
//
//        public fun addItems(items: List<Champions>) {
//            this.items.addAll(items)
//            notifyDataSetChanged()
//        }
//
//        inner class ChompionWinRateHolder(val championWinRateBinding: ItemChampionWinRateBinding) :
//            RecyclerView.ViewHolder(championWinRateBinding.root)
//    }
}