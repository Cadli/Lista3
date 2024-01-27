package pl.edu.uwr.pum.lista3

import android.os.Parcel
import android.os.Parcelable

class Question(
    var textResId: String,
    var answers: Array<String>,
    var correctAnswerIndex: Int,

    ) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.createStringArray() ?: arrayOf(),
        parcel.readInt(),

        )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(textResId)
        parcel.writeStringArray(answers)
        parcel.writeInt(correctAnswerIndex)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Question> {
        override fun createFromParcel(parcel: Parcel): Question {
            return Question(parcel)
        }

        override fun newArray(size: Int): Array<Question?> {
            return arrayOfNulls(size)
        }
    }
}
