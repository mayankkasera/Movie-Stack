package com.example.moviestack.di.modules

import com.example.moviestack.api.repo.discover.DiscoverRepository
import com.example.moviestack.api.repo.discover.DiscoverRepositoryI
import com.example.moviestack.api.repo.discover.DiscoverRequests
import com.example.moviestack.api.repo.movie.MovieItemRepository
import com.example.moviestack.api.repo.movie.MovieItemRepositoryI
import com.example.moviestack.api.repo.movie.MovieItemRequests
import com.example.moviestack.api.repo.person.PersonRepository
import com.example.moviestack.api.repo.person.PersonRepositoryI
import com.example.moviestack.api.repo.person.PersonRequests
import com.example.moviestack.api.repo.search.SearchRepository
import com.example.moviestack.api.repo.search.SearchRepositoryI
import com.example.moviestack.api.repo.search.SearchRequests
import com.example.moviestack.api.repo.trending.TrendingRepository
import com.example.moviestack.api.repo.trending.TrendingRequest
import com.example.moviestack.api.repo.tvshow.TvShowRepository
import com.example.moviestack.api.repo.tvshow.TvShowRepositoryI
import com.example.moviestack.api.repo.tvshow.TvShowRequests
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