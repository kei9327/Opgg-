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
import kr.wayde.opgg.databinding.ItemChampionWinRateBinding
import kr.wayde.opgg.domain.entity.*
import kr.wayde.opgg.domain.interactor.usecases.GetMatchesUseCase
import kr.wayde.opgg.ui.BaseObservableViewModel

class SummonerRecentViewModel(private val getMatchesUseCase: GetMatchesUseCase) :
    BaseObservableViewModel() {
    val mostChampionAdapter: MostChampionAdapter = MostChampionAdapter()
    val matchesInfoResult = MutableLiveData<Matches>()
    val positionLiveData = MutableLiveData<Positions>()

    fun requestSummonerInfo(summoner: String) {
        summoner
            ?.takeIf { it.isNotEmpty() }
            ?.let {
                getMatchesUseCase
                    .get(GetMatchesUseCase.Params(it, System.currentTimeMillis() / 1000))
                    .subscribe { it: Matches ->
                        positionLiveData.postValue(it.positions[0])
                        mostChampionAdapter.addItems(it.champions)
                        matchesInfoResult.postValue(it)
                    }
            }
    }

//    recentBinding.txtWinLose.text = String.format(recentBinding.txtWinLose.resources.getString(R.string.lol_win_lose), it.positions[0].wins, it.positions[0].losses)
//
//    recentBinding.txtKillAvg.text = it.games.map { games -> return@map games.stats.general.kill }.average().toString()
//    recentBinding.txtDeathAvg.text = it.games.map { games -> return@map games.stats.general.death }.average().toString()
//    recentBinding.txtAssist.text = it.games.map { games -> return@map games.stats.general.assist }.average().toString()

    companion object {
        @JvmStatic
        @BindingAdapter("app:winLoseText")
        fun winLoseText(view: TextView, games: List<Games>?) {
            var winCount = games?.count(Games::isWin)
            winCount?.let {
                view.text = String.format(
                    view.resources.getString(R.string.lol_win_lose),
                    winCount, 20 - winCount
                )
            }
        }

        @JvmStatic
        @BindingAdapter("app:killAvgText")
        fun killAvgText(view: TextView, games: List<Games>?) {
            view.text = String.format(
                "%.1f",
                games?.map { games -> return@map games.stats.general.kill }?.average()
            )
        }

        @JvmStatic
        @BindingAdapter("app:deathAvgText")
        fun deathAvgText(view: TextView, games: List<Games>?) {
            view.text = String.format(
                "%.1f",
                games?.map { games -> return@map games.stats.general.death }?.average()
            )
        }

        @JvmStatic
        @BindingAdapter("app:assistAvgText")
        fun assistAvgText(view: TextView, games: List<Games>?) {
            view.text = String.format(
                "%.1f",
                games?.map { games -> return@map games.stats.general.assist }?.average()
            )
        }

        @JvmStatic
        @BindingAdapter("app:kdaText")
        fun kdaText(view: TextView, matches: Matches?) {
            var kill = 0.0
            var assist = 0.0
            var death = 0.0
            matches?.games?.map { games -> return@map games.stats.general.kill }?.average()?.let {
                kill = it
            }

            matches?.games?.map { games -> return@map games.stats.general.assist }?.average()?.let {
                assist = it
            }
            matches?.games?.map { games -> return@map games.stats.general.death }?.average()?.let {
                death = it
            }

            var rate = 0
            matches?.positions?.get(0)?.wins?.let { rate = it / 20 * 100 }

            view.text = String.format("%.2f", (kill + assist) / death) + ":1 ($rate%)"
        }

        @JvmStatic
        @BindingAdapter("app:positionImg")
        fun positionImg(view: SimpleDraweeView, position: List<Positions>?) {
            var resource = when (position?.get(0)?.position) {
                "TOP" -> R.drawable.icon_lol_top
                "SUP" -> R.drawable.icon_lol_sup
                "MID" -> R.drawable.icon_lol_mid
                "JNG" -> R.drawable.icon_lol_jng
                else -> R.drawable.icon_lol_bot
            }

            view.setImageResource(resource)
        }

        @JvmStatic
        @BindingAdapter("app:txtMostRate")
        fun txtMostRate(view: TextView, position: List<Positions>?) {
            val wins = position?.get(0)?.wins
            val losses = position?.get(0)?.losses
            wins?.let {
                losses?.let {
                    val times = (wins.toFloat() / (wins + losses).toFloat()) * 100
                    view.text = "${times.toInt()}%"
                }
            }

        }

        @JvmStatic
        @BindingAdapter("app:adapter")
        fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
            view.adapter = adapter
        }

    }

    inner class MostChampionAdapter() :
        RecyclerView.Adapter<MostChampionAdapter.ChompionWinRateHolder>() {
        private val items: MutableList<Champions> = ArrayList()
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChompionWinRateHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding: ViewDataBinding =
                DataBindingUtil.inflate(inflater, R.layout.item_champion_win_rate, parent, false)
            return ChompionWinRateHolder(binding as ItemChampionWinRateBinding)
        }

        override fun onBindViewHolder(holder: ChompionWinRateHolder, position: Int) {
            if (items.size - 1 >= position) {
                holder.championWinRateBinding.viewModel =
                    ChampionWinRateItemViewModel(items[position])
            } else {
                holder.championWinRateBinding.viewModel = ChampionWinRateItemViewModel(null)
            }
        }

        override fun getItemCount(): Int = 2

        public fun addItems(items: List<Champions>) {
            this.items.addAll(items)
            notifyDataSetChanged()
        }

        inner class ChompionWinRateHolder(val championWinRateBinding: ItemChampionWinRateBinding) :
            RecyclerView.ViewHolder(championWinRateBinding.root)
    }
}