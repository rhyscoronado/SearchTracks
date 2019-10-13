package com.code.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SongDetail(@PrimaryKey(autoGenerate = true) var id: Int) : Parcelable {
    var wrappertype: String? = null
    var kind: String? = null
    var artistId: Long = 0
    var collectionId: Long = 0
    var artistName: String? = null
    var collectionName: String? = null
    var trackName: String? = null
    var collectionCensoredName: String? = null
    var trackCensoredName: String? = null
    var artistViewUrl: String? = null
    var trackViewUrl: String? = null
    var previewUrl: String? = null
    var artworkUrl30: String? = null
    var artworkUrl60: String? = null
    var artworkUrl100: String? = null
    var collectionPrice: Double = 0.0
    var trackPrice: Double = 0.0
    var releaseDate: String? = null
    var collectionExplicitness: String? = null
    var trackExplicitness: String? = null
    var discCount: Int = 0
    var discNumber: Int = 0
    var trackTimeMillis: Long = 0
    var country: String? = null
    var currency: String? = null
    var primaryGenreName: String? = null
    var isStreamable: Boolean = false
    var longDescription: String? = null

    constructor(parcel: Parcel) : this(parcel.readInt()) {
        wrappertype = parcel.readString()
        kind = parcel.readString()
        artistId = parcel.readLong()
        collectionId = parcel.readLong()
        artistName = parcel.readString()
        collectionName = parcel.readString()
        trackName = parcel.readString()
        collectionCensoredName = parcel.readString()
        trackCensoredName = parcel.readString()
        artistViewUrl = parcel.readString()
        trackViewUrl = parcel.readString()
        previewUrl = parcel.readString()
        artworkUrl30 = parcel.readString()
        artworkUrl60 = parcel.readString()
        artworkUrl100 = parcel.readString()
        collectionPrice = parcel.readDouble()
        trackPrice = parcel.readDouble()
        releaseDate = parcel.readString()
        collectionExplicitness = parcel.readString()
        trackExplicitness = parcel.readString()
        discCount = parcel.readInt()
        discNumber = parcel.readInt()
        trackTimeMillis = parcel.readLong()
        country = parcel.readString()
        currency = parcel.readString()
        primaryGenreName = parcel.readString()
        isStreamable = parcel.readByte() != 0.toByte()
        longDescription = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(wrappertype)
        parcel.writeString(kind)
        parcel.writeLong(artistId)
        parcel.writeLong(collectionId)
        parcel.writeString(artistName)
        parcel.writeString(collectionName)
        parcel.writeString(trackName)
        parcel.writeString(collectionCensoredName)
        parcel.writeString(trackCensoredName)
        parcel.writeString(artistViewUrl)
        parcel.writeString(trackViewUrl)
        parcel.writeString(previewUrl)
        parcel.writeString(artworkUrl30)
        parcel.writeString(artworkUrl60)
        parcel.writeString(artworkUrl100)
        parcel.writeDouble(collectionPrice)
        parcel.writeDouble(trackPrice)
        parcel.writeString(releaseDate)
        parcel.writeString(collectionExplicitness)
        parcel.writeString(trackExplicitness)
        parcel.writeInt(discCount)
        parcel.writeInt(discNumber)
        parcel.writeLong(trackTimeMillis)
        parcel.writeString(country)
        parcel.writeString(currency)
        parcel.writeString(primaryGenreName)
        parcel.writeByte(if (isStreamable) 1 else 0)
        parcel.writeString(longDescription)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SongDetail> {
        override fun createFromParcel(parcel: Parcel): SongDetail {
            return SongDetail(parcel)
        }

        override fun newArray(size: Int): Array<SongDetail?> {
            return arrayOfNulls(size)
        }
    }

}