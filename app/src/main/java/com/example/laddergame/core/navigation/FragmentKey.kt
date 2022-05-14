package com.example.laddergame.core.navigation

import com.zhuinden.simplestack.ServiceBinder
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey
import com.zhuinden.simplestackextensions.services.DefaultServiceProvider

abstract class FragmentKey: DefaultFragmentKey(), DefaultServiceProvider.HasServices {
    override fun bindServices(serviceBinder: ServiceBinder) {
        // empty
    }

    override fun getScopeTag(): String = toString()
}