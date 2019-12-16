package e.mi.fotra.dataclasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Language(
    val code: String,
    val name: String
) : Parcelable