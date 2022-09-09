package com.example.project_ticketmaster_challenge.model.classification

import android.os.Parcel
import android.os.Parcelable

data class ClassificationModel(
    val segment: SegmentModel?,
): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable(SegmentModel::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(segment, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ClassificationModel> {
        override fun createFromParcel(parcel: Parcel): ClassificationModel {
            return ClassificationModel(parcel)
        }

        override fun newArray(size: Int): Array<ClassificationModel?> {
            return arrayOfNulls(size)
        }
    }
}
