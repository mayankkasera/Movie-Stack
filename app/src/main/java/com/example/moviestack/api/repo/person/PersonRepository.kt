package com.example.moviestack.api.repo.person

import android.util.Log

import com.example.moviestack.pojo.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class PersonRepository(var personRequests: PersonRequests) : PersonRepositoryI {
    override fun getTaggedImages(id: String): Observable<PersonResponce> {
        return Observable.create<PersonResponce> { emitter ->
            personRequests?.getTaggedImages(id)?.enqueue(object : Callback<TaggedImages> {

                override fun onResponse(
                    call: Call<TaggedImages>,
                    response: Response<TaggedImages>
                ) {

                    response.body()?.let {
                        val movieResponse = PersonResponce()
                        movieResponse.type = PersonResponce.Type.TAGGED_IMAGES
                        movieResponse.data = it
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(PersonResponce())
                        emitter.onComplete()
                    }

                }

                override fun onFailure(call: Call<TaggedImages>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc", "" + t.message)
                }

            })
        }
    }

    override fun getExternalIds(id: String): Observable<PersonResponce> {
        return Observable.create<PersonResponce> { emitter ->

            personRequests?.getExternalIds(id)?.enqueue(object : Callback<ExternalIds> {

                override fun onResponse(call: Call<ExternalIds>, response: Response<ExternalIds>) {
                    response.body()?.let {
                        val movieResponse = PersonResponce()
                        movieResponse.type = PersonResponce.Type.EXTERNAL_IDS
                        movieResponse.data = it
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(PersonResponce())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<ExternalIds>, t: Throwable) {
                    emitter.onError(t)
                    Log.i("dshgczvc", "" + t.message)
                }

            })

        }
    }

    override fun getPersonInfo(id: String): Observable<PersonResponce> {
        return Observable.create<PersonResponce> { emitter ->
            personRequests?.getPersonInfo(id)?.enqueue(object : Callback<PersonInfo> {

                override fun onResponse(call: Call<PersonInfo>, response: Response<PersonInfo>) {
                    response.body()?.let {
                        val movieResponse = PersonResponce()
                        movieResponse.type = PersonResponce.Type.PERSON_INFO
                        movieResponse.data = it
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(PersonResponce())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<PersonInfo>, t: Throwable) {
                    emitter.onError(t)
                }

            })
        }
    }

    override fun getImages(id: String): Observable<PersonResponce> {
        return Observable.create<PersonResponce> { emitter ->

            personRequests?.getImages(id)?.enqueue(object : Callback<PersonImages> {
                override fun onResponse(call: Call<PersonImages>, response: Response<PersonImages>) {
                    Log.i("bvhvhg","${response.toString()}")
                    response.body()?.let {
                        val movieResponse = PersonResponce()
                        movieResponse.type = PersonResponce.Type.PERSON_IMAGES
                        movieResponse.data = it
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(PersonResponce())
                        emitter.onComplete()
                    }
                }
                override fun onFailure(call: Call<PersonImages>, t: Throwable) {
                    emitter.onError(t)
                }
            })

        }
    }

    override fun getMovieCredits(id: String): Observable<PersonResponce> {
        return Observable.create<PersonResponce> { emitter ->
            personRequests?.getMovieCredits(id)?.enqueue(object : Callback<MovieCredits>{

                override fun onResponse(call: Call<MovieCredits>, response: Response<MovieCredits>) {

                    response.body()?.let {

                        val joined = ArrayList<Result>()
                        joined.addAll(it.cast)
                        joined.addAll(it.crew)

                        val movieResponse = PersonResponce()
                        movieResponse.type = PersonResponce.Type.MOVIE_CREDITS
                        movieResponse.data = joined
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(PersonResponce())
                        emitter.onComplete()
                    }

                }

                override fun onFailure(call: Call<MovieCredits>, t: Throwable) {
                    emitter.onError(t)
                }



            })
        }
    }

    override fun getTvCredits(id: String): Observable<PersonResponce> {
        return Observable.create<PersonResponce> { emitter ->
            personRequests?.getTvCredits(id)?.enqueue(object : Callback<MovieCredits>{

                override fun onResponse(call: Call<MovieCredits>, response: Response<MovieCredits>) {

                    response.body()?.let {

                        val joined = ArrayList<Result>()
                        joined.addAll(it.cast)
                        joined.addAll(it.crew)

                        val movieResponse = PersonResponce()
                        movieResponse.type = PersonResponce.Type.MOVIE_CREDITS
                        movieResponse.data = joined
                        emitter.onNext(movieResponse)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(PersonResponce())
                        emitter.onComplete()
                    }

                }

                override fun onFailure(call: Call<MovieCredits>, t: Throwable) {
                    emitter.onError(t)
                }



            })
        }
    }

    override fun getPerson(
        page: String
    ): Observable<Person> {
        return Observable.create<Person> { emitter ->
            personRequests?.getPopular(page)?.enqueue(object : Callback<Person>{
                override fun onResponse(call: Call<Person>, response: Response<Person>) {
                    response.body()?.let {
                        emitter.onNext(it)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(Person())
                        emitter.onComplete()
                    }
                }
                override fun onFailure(call: Call<Person>, t: Throwable) {
                    emitter.onError(t)
                }
            })
        }
    }


}