package com.reactions.fixt.presentation.common.transformer

import com.reactions.fixt.domain.common.transformer.FTransformer
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Publisher

class AsyncFTransformer<T> : FTransformer<T>() {

    override fun apply(upstream: Flowable<T>): Publisher<T> =
            upstream.subscribeOn(Schedulers.io())
}