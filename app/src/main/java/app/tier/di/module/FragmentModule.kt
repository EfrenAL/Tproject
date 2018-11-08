package app.tier.di.module

import app.tier.ui.login.LoginFragment
import app.tier.ui.map.MapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMapFragment(): MapFragment

    /*@ContributesAndroidInjector
    abstract fun contributeUserFragment(): LoginFragment*/

}