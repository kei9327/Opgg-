package kr.wayde.opgg.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.summonerResult.observe(this, Observer {
            Log.i("MainFragment", it.toString())
        })

        viewModel.matchesInfoResult.observe(this, Observer {
            Log.i("MainFragment", it.toString())
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getSummonerInfo("genetory")
        viewModel.getMatchesInfo("genetory")
    }

}