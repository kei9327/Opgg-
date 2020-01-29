package kr.wayde.opgg.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kr.wayde.opgg.R
import kr.wayde.opgg.databinding.ItemGameMatchBinding
import kr.wayde.opgg.databinding.ItemRecnet20Binding
import kr.wayde.opgg.databinding.ItemSummonerHeaderBinding
import kr.wayde.opgg.domain.entity.Games
import kr.wayde.opgg.util.AdapterDataObserverProxy
import kr.wayde.opgg.util.MetricsUtil
import kr.wayde.opgg.view.RandkDivideItemDecoration
import kr.wayde.opgg.view.RightDivideItemDecoration

class MainSummonerAdapter(val context: Context, val summonerHeaderViewModel:SummonerHeaderViewModel , val summonerRecentViewModel: SummonerRecentViewModel) : PagedListAdapter<Games, MainSummonerAdapter.SummonerViewHolder>(
    diffCallback
) {
    companion object {
        private const val CELL_TYPE_HEADER = 0
        const val CELL_TYPE_RECENT = 1
        private const val CELL_TYPE_GAMES = 2

        private val diffCallback = object : DiffUtil.ItemCallback<Games>() {
            override fun areItemsTheSame(oldItem: Games, newItem: Games): Boolean {
                return oldItem.gameId == newItem.gameId
            }

            override fun areContentsTheSame(oldItem: Games, newItem: Games): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 2
    }

    override fun getItem(position: Int): Games? {
        if (position < 2) return null
        return super.getItem(position -2)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> CELL_TYPE_HEADER
            1 -> CELL_TYPE_RECENT
            else -> CELL_TYPE_GAMES
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SummonerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding

        return return when (viewType) {
            CELL_TYPE_HEADER -> {
                binding =
                    DataBindingUtil.inflate(inflater, R.layout.item_summoner_header, parent, false)
                SummonerViewHolder.Header(binding as ItemSummonerHeaderBinding)
            }
            CELL_TYPE_RECENT -> {
                binding = DataBindingUtil.inflate(inflater, R.layout.item_recnet_20, parent, false)
                SummonerViewHolder.Recent(binding as ItemRecnet20Binding)
            }
            else -> {
                binding = DataBindingUtil.inflate(inflater, R.layout.item_game_match, parent, false)
                SummonerViewHolder.GameMatch(binding as ItemGameMatchBinding)
            }
        }

    }

    override fun onBindViewHolder(holder: SummonerViewHolder, position: Int) {
        when(holder) {
            is SummonerViewHolder.Header -> {
                holder.summonerHeaderBinding.viewModel = summonerHeaderViewModel
                holder.summonerHeaderBinding.rcvRank.addItemDecoration(RandkDivideItemDecoration(MetricsUtil.convertDpToPixel(8f, context).toInt()))
            }
            is SummonerViewHolder.Recent -> {
                holder.recentBinding.viewModel = summonerRecentViewModel
                holder.recentBinding.recyclerView.addItemDecoration(RightDivideItemDecoration(MetricsUtil.convertDpToPixel(8f, context).toInt()))
            }
            is SummonerViewHolder.GameMatch -> {
                getItem(position)?.let {
                    holder.gameMatchBinding.viewModel = GameMatchItemViewModel(it)
                    holder.gameMatchBinding.rcvBuildItem.addItemDecoration(RightDivideItemDecoration(MetricsUtil.convertDpToPixel(2f, context).toInt()))
                }
            }
        }
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer, 2))
    }

    sealed class SummonerViewHolder : RecyclerView.ViewHolder {
        class Header(val summonerHeaderBinding:ItemSummonerHeaderBinding): SummonerViewHolder(summonerHeaderBinding)
        class Recent(val recentBinding:ItemRecnet20Binding): SummonerViewHolder(recentBinding)
        class GameMatch(val gameMatchBinding:ItemGameMatchBinding): SummonerViewHolder(gameMatchBinding)

        constructor(binding: ItemSummonerHeaderBinding) : super(binding.root)
        constructor(binding: ItemRecnet20Binding) : super(binding.root)
        constructor(binding: ItemGameMatchBinding) : super(binding.root)
    }
}
