package com.loblaw.redditnews.base.presentation.model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.loblaw.redditnews.base.domain.exception.LobLawNewsException

class ObservableResource<T> : SingleLiveEvent<T>() {

    val error: SingleLiveEvent<LobLawNewsException> = ErrorLiveData()
    val loading: SingleLiveEvent<Boolean> = SingleLiveEvent()

    fun observe(
        owner: LifecycleOwner, successObserver: Observer<T>,
        loadingObserver: Observer<Boolean>? = null,
        commonErrorObserver: Observer<LobLawNewsException>,
        httpErrorConsumer: Observer<LobLawNewsException>? = null,
        networkErrorConsumer: Observer<LobLawNewsException>? = null,
        unExpectedErrorConsumer: Observer<LobLawNewsException>? = null,
        serverDownErrorConsumer: Observer<LobLawNewsException>? = null,
        timeOutErrorConsumer: Observer<LobLawNewsException>? = null,
        unAuthorizedErrorConsumer: Observer<LobLawNewsException>? = null
    ) {
        super.observe(owner, successObserver)
        loadingObserver?.let { loading.observe(owner, it) }
        (error as ErrorLiveData).observe(
            owner, commonErrorObserver, httpErrorConsumer, networkErrorConsumer, unExpectedErrorConsumer,
            serverDownErrorConsumer, timeOutErrorConsumer, unAuthorizedErrorConsumer
        )
    }


    class ErrorLiveData : SingleLiveEvent<LobLawNewsException>() {
        private var ownerRef: LifecycleOwner? = null
        private var httpErrorConsumer: Observer<LobLawNewsException>? = null
        private var networkErrorConsumer: Observer<LobLawNewsException>? = null
        private var unExpectedErrorConsumer: Observer<LobLawNewsException>? = null
        private var commonErrorConsumer: Observer<LobLawNewsException>? = null

        private var serverDownErrorConsumer: Observer<LobLawNewsException>? = null
        private var timeOutErrorConsumer: Observer<LobLawNewsException>? = null
        private var unAuthorizedErrorConsumer: Observer<LobLawNewsException>? = null

        override fun setValue(t: LobLawNewsException?) {
            ownerRef?.also {
                removeObservers(it)
                t?.let { vale -> addProperObserver(vale) }
                super.setValue(t)
            }

        }

        override fun postValue(value: LobLawNewsException) {
            ownerRef?.also {
                removeObservers(it)
                addProperObserver(value)
                super.postValue(value)
            }

        }

        private fun addProperObserver(value: LobLawNewsException) {
            when (value.kind) {
                LobLawNewsException.Kind.NETWORK -> networkErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)
                LobLawNewsException.Kind.HTTP -> httpErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)
                LobLawNewsException.Kind.UNEXPECTED -> unExpectedErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                LobLawNewsException.Kind.SERVER_DOWN -> serverDownErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                LobLawNewsException.Kind.TIME_OUT -> timeOutErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                LobLawNewsException.Kind.UNAUTHORIZED -> unAuthorizedErrorConsumer?.let { observe(ownerRef!!, it) }
                    ?: observe(ownerRef!!, commonErrorConsumer!!)

                else -> {
                }
            }
        }


        fun observe(
            owner: LifecycleOwner, commonErrorConsumer: Observer<LobLawNewsException>,
            httpErrorConsumer: Observer<LobLawNewsException>? = null,
            networkErrorConsumer: Observer<LobLawNewsException>? = null,
            unExpectedErrorConsumer: Observer<LobLawNewsException>? = null,

            serverDownErrorConsumer: Observer<LobLawNewsException>? = null,
            timeOutErrorConsumer: Observer<LobLawNewsException>? = null,
            unAuthorizedErrorConsumer: Observer<LobLawNewsException>? = null
        ) {
            super.observe(owner, commonErrorConsumer)
            this.ownerRef = owner
            this.commonErrorConsumer = commonErrorConsumer
            this.httpErrorConsumer = httpErrorConsumer
            this.networkErrorConsumer = networkErrorConsumer
            this.unExpectedErrorConsumer = unExpectedErrorConsumer
            this.serverDownErrorConsumer = serverDownErrorConsumer
            this.timeOutErrorConsumer = timeOutErrorConsumer
            this.unAuthorizedErrorConsumer = unAuthorizedErrorConsumer
        }
    }
}