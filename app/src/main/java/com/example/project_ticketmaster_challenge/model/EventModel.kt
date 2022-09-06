package com.example.project_ticketmaster_challenge.model

import android.os.Parcel
import android.os.Parcelable

data class EventModel(
    val id: String,
    val name: String,
    val type: String,
    val images: List<ImageModel>,
    val classifications: List<ClassificationModel>
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createTypedArrayList(ImageModel) ?: emptyList(),
        parcel.createTypedArrayList(ClassificationModel) ?: emptyList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeTypedList(images)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventModel> {
        override fun createFromParcel(parcel: Parcel): EventModel {
            return EventModel(parcel)
        }

        override fun newArray(size: Int): Array<EventModel?> {
            return arrayOfNulls(size)
        }
    }
}
