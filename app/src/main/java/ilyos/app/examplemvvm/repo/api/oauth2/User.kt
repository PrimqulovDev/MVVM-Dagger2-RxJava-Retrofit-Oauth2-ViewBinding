package ilyos.app.examplemvvm.repo.api.oauth2

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

open class User(
    @SerializedName("avatar") var avatar: String,
    @SerializedName("name") var name: String,
    @SerializedName("email") var email: String,
    @SerializedName("id") var id: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    fun clone(user: User) {
        this.avatar = user.avatar
        this.name = user.name
        this.email = user.email
        this.id = user.id
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(avatar)
        parcel.writeString(name)
        parcel.writeString(email)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}