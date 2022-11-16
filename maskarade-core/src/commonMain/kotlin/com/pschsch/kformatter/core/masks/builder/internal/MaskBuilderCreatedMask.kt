package com.pschsch.kformatter.core.masks.builder.internal

import com.pschsch.kformatter.core.masks.Mask
import com.pschsch.kformatter.core.optin.IncubatingMaskaradeAPI
import com.pschsch.kformatter.core.parcelable.Parcelize

@OptIn(IncubatingMaskaradeAPI::class)
@Parcelize
internal class MaskBuilderCreatedMask(
    private val slots: List<Mask.Slot>,
    override val hint: String
) : Mask, Collection<Mask.Slot> by slots {
    override fun toString(): String {
        return slots.joinToString {
            when (it) {
                Mask.Slot.Digit -> "D"
                Mask.Slot.Letter -> "L"
                is Mask.Slot.Hardcoded -> {
                    if (it.symbol == 'D') "\\D" else if (it.symbol == 'L') "\\L" else it.symbol.toString()
                }
            }
        }
    }

}