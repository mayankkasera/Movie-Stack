package com.codeinger.moviestack.di.modules

import com.codeinger.moviestack.api.repo.discover.DiscoverRepository
import com.codeinger.moviestack.api.repo.discover.DiscoverRepositoryI
import com.codeinger.moviestack.api.repo.discover.DiscoverRequests
import com.codeinger.moviestack.api.repo.movie.MovieItemRepository
import com.codeinger.moviestack.api.repo.movie.MovieItemRepositoryI
import com.codeinger.moviestack.api.repo.movie.MovieItemRequests
import com.codeinger.moviestack.api.repo.person.PersonRepository
import com.codeinger.moviestack.api.repo.person.PersonRepositoryI
import com.codeinger.moviestack.api.repo.person.PersonRequests
import com.codeinger.moviestack.api.repo.search.SearchRepository
import com.codeinger.moviestack.api.repo.search.SearchRepositoryI
import com.codeinger.moviestack.api.repo.search.SearchRequests
import com.codeinger.moviestack.api.repo.trending.TrendingRepository
import com.codeinger.moviestack.api.repo.trending.TrendingRequest
import com.codeinger.moviestack.api.repo.tvshow.TvShowRepository
import com.codeinger.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.codeinger.tvstack.api.repo.tvshow.TvShowRequests
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [NetworkModule::class]
)
class DataModule {

    @Provides
    @Singleton
    fun provideDiscoverRepository(discoverRequests: DiscoverRequests) : DiscoverRepositoryI {
        return DiscoverRepository(discoverRequests)
    }


    @Provides
    @Singleton
    fun providePersonRepository(personRequests: PersonRequests) : PersonRepositoryI {
        return PersonRepository(personRequests)
    }

    @Provides
    @Singleton
    fun provideMovieItemRepository(movieItemRequests: MovieItemRequests) : MovieItemRepositoryI {
        return MovieItemRepository(movieItemRequests)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(searchRequests:SearchRequests) : SearchRepositoryI {
        return SearchRepository(searchRequests)
    }

    @Provides
    @Singleton
    fun provideTvShowRepository(tvShowRequests: TvShowRequests) : TvShowRepositoryI {
        return TvShowRepository(tvShowRequests)
    }

    @Provides
    @Singleton
    fun provideTrendingRepository(trendingRequest: TrendingRequest) : TrendingRepository {
        return TrendingRepository(trendingRequest)
    }
}