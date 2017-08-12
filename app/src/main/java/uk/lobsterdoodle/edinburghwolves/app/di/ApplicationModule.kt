package uk.lobsterdoodle.edinburghwolves.app.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uk.lobsterdoodle.edinburghwolves.core.presenter.RosterListFragmentPresenter

@Module
class ApplicationModule {

    @Provides
    internal fun providesRosterListFragmentPresenter(): RosterListFragmentPresenter {
        return RosterListFragmentPresenter()
    }

    @Provides
    internal fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://bafanl-d5f55.firebaseio.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }
}
