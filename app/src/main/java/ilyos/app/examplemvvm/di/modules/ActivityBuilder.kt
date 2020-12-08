package ilyos.app.examplemvvm.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ilyos.app.examplemvvm.ui.activities.MainActivity

/**
 * Developed by Ilyos
 */

@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun contributeMainInjector(): MainActivity

}