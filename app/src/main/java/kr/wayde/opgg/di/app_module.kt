package kr.wayde.opgg.di

import kr.wayde.opgg.BuildConfig
import kr.wayde.opgg.data.interactor.OpggDataSource
import kr.wayde.opgg.data.repository.OpggRemote
import kr.wayde.opgg.domain.interactor.usecases.GetMatchesUseCase
import kr.wayde.opgg.domain.interactor.usecases.GetSummonerInfoUseCase
import kr.wayde.opgg.domain.repository.OpggRepository
import kr.wayde.opgg.domain.schedulers.SchedulersProvider
import kr.wayde.opgg.remote.OpggRemoteImpl
import kr.wayde.opgg.remote.OpggServiceFactory
import kr.wayde.opgg.remote.mapper.MatchesEntityMapper
import kr.wayde.opgg.remote.mapper.SummonerEntityMapper
import kr.wayde.opgg.ui.main.MainViewModel
import kr.wayde.opgg.ui.main.SummonerHeaderViewModel
import kr.wayde.opgg.ui.main.SummonerRecentViewModel
import kr.wayde.opgg.util.AppSchedulerProvider
import kr.wayde.opgg.util.Logger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { Logger() }
    single { AppSchedulerProvider() as SchedulersProvider }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SummonerRecentViewModel(get()) }
    viewModel { SummonerHeaderViewModel(get()) }
}

val domainModule = module {
    single { GetMatchesUseCase(get(), get()) }
    single { GetSummonerInfoUseCase(get(), get()) }
}

val dataModule = module {
    //DATA SOURCES
    single { OpggDataSource(get()) as OpggRepository }

    //remote
    single { OpggRemoteImpl(get(), get(), get()) as OpggRemote }
    single { MatchesEntityMapper() }
    single { SummonerEntityMapper() }
    single {
        OpggServiceFactory.makeOpggBrowserService(
            BuildConfig.DEBUG,
            "https://codingtest.op.gg/api/"
        )
    }
}
