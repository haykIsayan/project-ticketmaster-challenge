package com.example.project_ticketmaster_challenge.model.event

import android.os.Parcel
import android.os.Parcelable

data class EventResponseModel(val _embedded: EventResponseEmbeddedModel?): Parcelable {
    constructor(parcel: Parcel) : this(parcel.readParcelable(EventResponseEmbeddedModel::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(_embedded, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventResponseModel> {
        override fun createFromParcel(parcel: Parcel): EventResponseModel {
            return EventResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<EventResponseModel?> {
            return arrayOfNulls(size)
        }
    }
}
