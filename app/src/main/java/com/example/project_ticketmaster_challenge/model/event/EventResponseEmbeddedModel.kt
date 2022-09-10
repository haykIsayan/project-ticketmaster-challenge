package com.example.project_ticketmaster_challenge.model.event

import android.os.Parcel
import android.os.Parcelable

data class EventResponseEmbeddedModel(val events: List<EventModel>): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(EventModel) ?: emptyList()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(events)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventResponseEmbeddedModel> {
        override fun createFromParcel(parcel: Parcel): EventResponseEmbeddedModel {
            return EventResponseEmbeddedModel(parcel)
        }

        override fun newArray(size: Int): Array<EventResponseEmbeddedModel?> {
            return arrayOfNulls(size)
        }
    }
}
