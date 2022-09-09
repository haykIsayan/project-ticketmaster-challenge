package com.example.project_ticketmaster_challenge.model.classification

import android.os.Parcel
import android.os.Parcelable

data class SegmentModel(
    val id: String?,
    val name: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SegmentModel> {
        override fun createFromParcel(parcel: Parcel): SegmentModel {
            return SegmentModel(parcel)
        }

        override fun newArray(size: Int): Array<SegmentModel?> {
            return arrayOfNulls(size)
        }
    }
}
