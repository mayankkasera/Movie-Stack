package com.example.moviestack.api.repo.person

import android.util.Log
import com.example.moviestack.api.pojo.ExternalIds
import com.example.moviestack.api.pojo.TaggedImages
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository(var personRequests:PersonRequests) : PersonRepositoryI{
    override fun getTaggedImages(id: String): Observable<PersonResponce> {
        return Observable.create<PersonResponce> { emitter ->
            personRequests?.getTaggedImages(id)?.enqueue(object : Callback<TaggedImages> {

                override fun onResponse(call: Call<TaggedImages>, response: Response<TaggedImages>) {

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

            personRequests?.getExternalIds(id)?.enqueue(object : Callback<ExternalIds>{

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


}