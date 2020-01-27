package kr.wayde.opgg.ui.main

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

class MainSummonerAdapter : PagedListAdapter<Games, MainSummonerAdapter.SummonerViewHolder>(
    ItemDiffCallBack
) {
    companion object {
        private const val CELL_TYPE_HEADER = 0
        private const val CELL_TYPE_RECENT = 1
        private const val CELL_TYPE_GAMES = 2
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + 2
    }

    private fun getGamesItem(position: Int): Games? {
        return getItem(position - 2)
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
                SummonerViewHolder(binding as ItemSummonerHeaderBinding)
            }
            CELL_TYPE_RECENT -> {
                binding = DataBindingUtil.inflate(inflater, R.layout.item_recnet_20, parent, false)
                SummonerViewHolder(binding as ItemRecnet20Binding)
            }
            else -> {
                binding = DataBindingUtil.inflate(inflater, R.layout.item_game_match, parent, false)
                SummonerViewHolder(binding as ItemGameMatchBinding)
            }
        }

    }

    override fun onBindViewHolder(holder: SummonerViewHolder, position: Int) {

    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer, 2))
    }


    inner class SummonerViewHolder : RecyclerView.ViewHolder {
        private lateinit var summonerHeaderBinding: ItemSummonerHeaderBinding
        private lateinit var recentBinding: ItemRecnet20Binding
        private lateinit var gameMatchBinding: ItemGameMatchBinding

        constructor(binding: ItemSummonerHeaderBinding) : super(binding.root) {
            summonerHeaderBinding = binding
        }

        constructor(binding: ItemRecnet20Binding) : super(binding.root) {
            recentBinding = binding
        }

        constructor(binding: ItemGameMatchBinding) : super(binding.root) {
            gameMatchBinding = binding
        }
    }
}

object ItemDiffCallBack : DiffUtil.ItemCallback<Games>() {
    override fun areItemsTheSame(oldItem: Games, newItem: Games): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Games, newItem: Games): Boolean {
        return oldItem == newItem
    }
}
