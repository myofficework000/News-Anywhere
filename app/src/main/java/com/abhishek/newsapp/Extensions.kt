package com.abhishek.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by abhishek.pathak on 08/04/2020.
 */

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

val Context.picasso: Picasso get() = Picasso.with(this)


fun ImageView.load(path: String, request: (RequestCreator) -> RequestCreator) {
    request(context.picasso.load(path)).into(this)
}

/*
@RequiresApi(Build.VERSION_CODES.O)
fun Date.toFormattedString(format: String = "EEE, MMM d, ''yy"): String {
    val parsedDate = LocalDateTime.parse("2018-12-14T09:55:00", DateTimeFormatter.ISO_DATE_TIME)
    val simpleDateFormat = SimpleDateFormat(format)
    return simpleDateFormat.format(this)
}
*/

fun toFormattedString(isoTime: String): String? {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    var convertedDate: Date? = null
    var formattedDate: String? = null
    try {
        convertedDate = sdf.parse(isoTime)
        formattedDate = SimpleDateFormat("MMMM dd,yyyy").format(convertedDate)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return formattedDate
}