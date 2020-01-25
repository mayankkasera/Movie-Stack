package com.example.moviestack.di.component

import android.content.Context
import com.example.moviestack.utils.NetworkConstants

class NetworkComponentFactory {
     companion object{
         fun create(context: Context) : NetworkComponent{
            return DaggerNetworkComponent.factory().create(NetworkConstants.baseUrl,context)
         }
     }
}