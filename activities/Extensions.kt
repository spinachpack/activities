// Task 2

package com.example.intprogtask

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

// 1. String Extensions
fun String.capitalizeWords(): String {
    return this.split(" ").joinToString(" ") { it.capitalize() }
}

fun String.isValidEmail(): Boolean {
    val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    return this.matches(emailPattern.toRegex())
}

fun String.removeSpecialChars(): String {
    return this.replace(Regex("[^A-Za-z0-9 ]"), "")
}

// 2. View Extensions
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

// 3. TextView Extensions
fun TextView.setTextColorRes(colorResId: Int) {
    this.setTextColor(resources.getColor(colorResId))
}

fun TextView.setFontSize(sizeSp: Float) {
    this.textSize = sizeSp
}

fun TextView.setTextWithAnimation(text: String, duration: Long = 500) {
    this.animate().alpha(0f).setDuration(duration / 2).withEndAction {
        this.text = text
        this.animate().alpha(1f).setDuration(duration / 2).start()
    }.start()
}

// 4. EditText Extensions
fun EditText.isNotEmpty(): Boolean {
    return this.text.toString().trim().isNotEmpty()
}

fun EditText.clearAndFocus() {
    this.text.clear()
    this.requestFocus()
}

fun EditText.showErrorIfEmpty(errorMsg: String): Boolean {
    return if (this.text.toString().trim().isEmpty()) {
        this.error = errorMsg
        false
    } else {
        true
    }
}

// 5. ImageView Extensions
fun ImageView.setBreedImage(breed: String) {
    val drawableResId = when (breed.lowercase()) {
        "golden retriever" -> R.drawable.golden_retriever
        "bulldog" -> R.drawable.bulldog
        "poodle" -> R.drawable.poodle
        "german shepherd" -> R.drawable.german_shepherd
        "labrador" -> R.drawable.labrador
        else -> R.drawable.nudaeng
    }
    this.setImageResource(drawableResId)
}

fun ImageView.makeCircular() {
    this.post {
        val drawable = this.drawable ?: return@post
        val bitmap = (drawable as? BitmapDrawable)?.bitmap ?: return@post

        val size = Math.min(width, height)
        val output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(output)
        val paint = Paint().apply {
            isAntiAlias = true
        }

        val rect = Rect(0, 0, size, size)
        val rectF = RectF(rect)

        // Draw circular mask
        canvas.drawOval(rectF, paint)

        // Apply image with masking
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, null, rect, paint)

        this.setImageBitmap(output)
    }
}

fun ImageView.tint(colorResId: Int) {
    this.setColorFilter(
        resources.getColor(colorResId),
        PorterDuff.Mode.SRC_IN
    )
}