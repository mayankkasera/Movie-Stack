package com.example.moviestack.di.modules

import com.example.moviestack.api.repo.discover.DiscoverRepository
import com.example.moviestack.api.repo.discover.DiscoverRepositoryI
import com.example.moviestack.api.repo.discover.DiscoverRequests
import com.example.moviestack.api.repo.movieInfo.MovieRepository
import com.example.moviestack.api.repo.movieInfo.MovieRepositoryI
import com.example.moviestack.api.repo.movieInfo.MovieRequests
import com.example.moviestack.api.repo.person.PersonRepository
import com.example.moviestack.api.repo.person.PersonRepositoryI
import com.example.moviestack.api.repo.person.PersonRequests
import com.example.moviestack.api.repo.search.SearchRepository
import com.example.moviestack.api.repo.search.SearchRepositoryI
import com.example.moviestack.api.repo.search.SearchRequests
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
    fun provideMovieRepository(movieRequests: MovieRequests) : MovieRepositoryI {
        return MovieRepository(movieRequests)
    }

    @Provides
    @Singleton
    fun providePersonRepository(personRequests: PersonRequests) : PersonRepositoryI {
        return PersonRepository(personRequests)
    }


    @Provides
    @Singleton
    fun provideSearchRepository(searchRequests:SearchRequests) : SearchRepositoryI {
        return SearchRepository(searchRequests)
    }

}