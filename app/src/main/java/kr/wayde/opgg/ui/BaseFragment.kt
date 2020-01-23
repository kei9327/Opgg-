package kr.wayde.opgg.ui

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Wayde.k(Jnaghyok Park) on 2019.11.10
 */
open class BaseFragment: Fragment() {
    private val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onDestroyView()
    }
}