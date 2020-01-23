package kr.wayde.opgg

import androidx.multidex.MultiDexApplication
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.stetho.Stetho
import kr.wayde.opgg.di.appModule
import kr.wayde.opgg.di.dataModule
import kr.wayde.opgg.di.domainModule
import kr.wayde.opgg.di.viewModelModule
import kr.wayde.opgg.util.AndroidContext
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

/**
 * Created by Wayde.k(Jnaghyok Park) on 2020.01.24
 */
class MainApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        AndroidContext.initialize(this)
        Stetho.initializeWithDefaults(this)
        Fresco.initialize(this)
        startKoin {
            androidContext(this@MainApplication)
            loadKoinModules(appModule, viewModelModule, domainModule, dataModule)
        }
    }
}