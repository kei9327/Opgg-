package kr.wayde.opgg.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_main.*
import kr.wayde.opgg.databinding.FragmentMainBinding
import kr.wayde.opgg.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**o
 * Created by Wayde.k(Jnaghyok Park) on 2020.01.24
 *
 * TODO : UI 구성만 완료하면 끝!!!
 */
class MainFragment : BaseFragment() {

    private val viewModel by viewModel<MainViewModel>()
    private val summonerHeaderViewModel by viewModel<SummonerHeaderViewModel>()
    private val summonerRecentViewModel by viewModel<SummonerRecentViewModel>()
    private lateinit var mAdapter: MainSummonerAdapter

    companion object {
        private val SUMMONER_NAME = "genetory";
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        mAdapter = MainSummonerAdapter(SUMMONER_NAME, summonerHeaderViewModel, summonerRecentViewModel)
        rcv_summoner_info.adapter = mAdapter

        summonerRecentViewModel.matchesInfoResult.observe(this, Observer {
            mAdapter.notifyDataSetChanged()
        })
        summonerRecentViewModel.requestSummonerInfo(SUMMONER_NAME)

        viewModel.pagedList.observe(this, Observer {
            mAdapter.submitList(it)
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getGames(SUMMONER_NAME)
    }

}