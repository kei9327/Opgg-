package kr.wayde.opgg.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import kr.wayde.opgg.R
import kr.wayde.opgg.databinding.ItemGameRankBinding
import kr.wayde.opgg.domain.entity.Leagues
import kr.wayde.opgg.domain.entity.Summoner
import kr.wayde.opgg.domain.interactor.usecases.GetSummonerInfoUseCase
import kr.wayde.opgg.ui.BaseObservableViewModel

class SummonerHeaderViewModel(private val getSummonerInfoUseCase: GetSummonerInfoUseCase) :
    BaseObservableViewModel() {
    val leaguesAdapter: LeaguesAdapter = LeaguesAdapter()
    val summonerResult = MutableLiveData<Summoner>()

    fun requestSummonerInfo(summoner: String) {
        compositeDisposable.add(
            getSummonerInfoUseCase.get(summoner).subscribe { it: Summoner ->
                leaguesAdapter.addItems(it.leagues)
                summonerResult.postValue(it)
            }
        )
    }

    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun loadImage(view: SimpleDraweeView, imageUrl: String?) {
            imageUrl
                ?.takeIf { it.isNotEmpty() }
                ?.let {
                    view.setImageURI(imageUrl)
                }
        }

        @JvmStatic
        @BindingAdapter("app:integerText")
        fun integerText(view: TextView, int: Int?) {
            int?.let {
                view.text = int.toString()
            }
        }


        @JvmStatic
        @BindingAdapter("app:adapter")
        fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            view.adapter = adapter
        }


    }

    inner class LeaguesAdapter() :
        RecyclerView.Adapter<LeaguesAdapter.LeaguesHolder>() {
        private val items: MutableList<Leagues> = ArrayList()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaguesHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_game_rank, parent, false)
            return LeaguesHolder(binding as ItemGameRankBinding)
        }

        override fun onBindViewHolder(holder: LeaguesHolder, position: Int) {
            if (items.size - 1 >= position) {
                holder.championWinRateBinding.viewModel =
                    LeaguesItemViewModel(items[position])
            } else {
                holder.championWinRateBinding.viewModel = LeaguesItemViewModel(null)
            }
        }

        override fun getItemCount(): Int = 2

        public fun addItems(items: List<Leagues>) {
            this.items.addAll(items)
            notifyDataSetChanged()
        }

        inner class LeaguesHolder(val championWinRateBinding: ItemGameRankBinding) :
            RecyclerView.ViewHolder(championWinRateBinding.root)
    }
}