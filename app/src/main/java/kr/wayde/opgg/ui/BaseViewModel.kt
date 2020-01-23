package kr.wayde.opgg.ui

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kr.wayde.opgg.util.AndroidContext
import kr.wayde.opgg.util.preference.AppPreference

import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    val appPref by lazy { AppPreference.getInstance(AndroidContext.instance().application) }

    val mJob = Job()

    override val coroutineContext: CoroutineContext
            = Dispatchers.Main + mJob

    protected val compositeDisposable = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
        super.onCleared()
    }
}