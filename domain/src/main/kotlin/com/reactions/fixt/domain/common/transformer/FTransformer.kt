package com.reactions.fixt.domain.common.transformer

import io.reactivex.FlowableTransformer

/**
 * A transformer to io thread for flowables.
 */
abstract class FTransformer<T> : FlowableTransformer<T, T>