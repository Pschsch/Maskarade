package com.pschsch.kformatter.core.formatting

import com.pschsch.kformatter.core.masks.Mask
import com.pschsch.kformatter.core.optin.IncubatingMaskaradeAPI
import com.pschsch.kformatter.core.parcelable.Parcelable
import com.pschsch.kformatter.core.parcelable.Parcelize
import com.pschsch.kformatter.core.serializable.Serializable

@IncubatingMaskaradeAPI
interface MaskFormatter {

    @Parcelize
    sealed class CompletionState : Serializable, Parcelable {
        @Parcelize
        object NotCompleted : CompletionState()

        @Parcelize
        object Completed : CompletionState()

        @Parcelize
        object Overflow : CompletionState()
    }

    val masks : List<Mask>
    fun format(value : String): String

    fun isCompleted(value : String): CompletionState
}