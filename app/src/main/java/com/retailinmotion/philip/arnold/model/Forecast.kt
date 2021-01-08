package com.retailinmotion.philip.arnold.model

import com.retailinmotion.philip.arnold.utils.formatDate
import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "stopInfo")
data class Forecast(
    @PropertyElement(name = "message") val message: String?,
    @Attribute(name = "created") val created: String?,
    @Attribute(name = "stop") val stop: String?,
    @Attribute(name = "stopAbv") val stopAbv: String?,
    @Element(name = "direction") val directions: List<Direction>,
) {
    fun createdLayout() =
        created?.let {
            when (created.length) {
                10 ->
                    it.formatDate()
                in 11..20 ->
                    it.substring(0, 10).formatDate() +
                    " " +
                    it.substring(11, 16)
                else ->
                    created
            }
        } ?: run {
            ""
        }
}

@Xml(name="direction")
data class Direction(
    @Element(name = "tram") val trams: List<Tram>?,
    @Attribute(name = "name") val name: String?,
)

@Xml(name = "tram")
data class Tram(
    @Attribute(name = "dueMins") val duemins: String = "",
    @Attribute(name = "destination")  var destination: String = "",
) {
    fun dueDisplay() =
        if (duemins == DUE_TEXT || duemins.isEmpty()) {
            duemins
        } else {
            "$duemins $MINS_FORMAT"
        }
}

const val MINS_FORMAT = "mins"
const val DUE_TEXT = "DUE"
