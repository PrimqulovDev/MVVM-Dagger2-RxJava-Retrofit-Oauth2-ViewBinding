package ilyos.app.examplemvvm.di.modules

import dagger.Module

/**
 * Developed by Ilyos
 */


@Module(includes = [NetworkModule::class, ViewModelModule::class, ViewModelFieldsModule::class])
internal object AppModule