package app.tier.di.component

import app.tier.TierApplication
import app.tier.di.module.AppModule
import app.tier.di.module.BuildersModule
import app.tier.di.module.FragmentModule
import app.tier.di.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidInjectionModule::class), (BuildersModule::class), (AppModule::class), (NetworkModule::class), (FragmentModule::class)])
interface AppComponent {
    fun inject(app: TierApplication)
}