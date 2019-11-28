package com.reactions.fixt.presentation.common.transformer

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.schedulers.Schedulers
import com.reactions.fixt.domain.common.transformer.STransformer

class AsyncSTransformer<T> : STransformer<T>() {

    override fun apply(upstream: Single<T>): SingleSource<T> = upstream.subscribeOn(Schedulers.io())
}