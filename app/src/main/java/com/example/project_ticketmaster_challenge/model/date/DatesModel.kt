package com.example.project_ticketmaster_challenge.model.date

import android.os.Parcel
import android.os.Parcelable

data class DatesModel(val start: DatesStartModel?): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable(DatesStartModel::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(start, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DatesModel> {
        override fun createFromParcel(parcel: Parcel): DatesModel {
            return DatesModel(parcel)
        }

        override fun newArray(size: Int): Array<DatesModel?> {
            return arrayOfNulls(size)
        }
    }
}
