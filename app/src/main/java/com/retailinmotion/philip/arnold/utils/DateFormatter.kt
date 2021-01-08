package com.retailinmotion.philip.arnold.utils

fun String.formatDate(): String {
    if (this.length < 10)
        return this
    return this.substring(8, 10) + "/" +
            this.substring(5, 7) + "/" +
            this.substring(0, 4)
}
