package com.example.project_ticketmaster_challenge.model.date

import android.os.Parcel
import android.os.Parcelable

data class DatesStartModel(
    val localDate: String?
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(localDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DatesStartModel> {
        override fun createFromParcel(parcel: Parcel): DatesStartModel {
            return DatesStartModel(parcel)
        }

        override fun newArray(size: Int): Array<DatesStartModel?> {
            return arrayOfNulls(size)
        }
    }
}
