package ilyos.app.examplemvvm.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ilyos.app.examplemvvm.App
import ilyos.app.examplemvvm.di.modules.ActivityBuilder
import ilyos.app.examplemvvm.di.modules.AppModule
import ilyos.app.examplemvvm.ui.activities.MainActivity
import javax.inject.Singleton

/**
 * Developed by Ilyos
 */

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory : AndroidInjector.Factory<App> {
        override fun create(@BindsInstance instance: App): AppComponent
    }

    override fun inject(app: App)

}