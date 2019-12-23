package com.example.moviestack.di.component

import com.example.moviestack.utils.NetworkConstants

class NetworkComponentFactory {
     companion object{
         fun create() : NetworkComponent{
            return DaggerNetworkComponent.factory().create(NetworkConstants.baseUrl)
         }
     }
}