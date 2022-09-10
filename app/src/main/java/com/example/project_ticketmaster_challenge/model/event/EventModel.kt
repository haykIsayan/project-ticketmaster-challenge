package com.example.project_ticketmaster_challenge.model.event

import android.os.Parcel
import android.os.Parcelable
import com.example.project_ticketmaster_challenge.model.event.classification.ClassificationModel
import com.example.project_ticketmaster_challenge.model.event.date.DatesModel

data class EventModel(
    val id: String,
    val name: String,
    val type: String,
    val images: List<ImageModel>?,
    val classifications: List<ClassificationModel>?,
    val dates: DatesModel?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.createTypedArrayList(ImageModel),
        parcel.createTypedArrayList(ClassificationModel),
        parcel.readParcelable(DatesModel::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeTypedList(images)
        parcel.writeTypedList(classifications)
        parcel.writeParcelable(dates, flags)
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
