package com.example.goratest.myfakeglide

import android.graphics.*


object BitmapPaint{

    fun drawRoundCorners(bitmap: Bitmap, px: Float): Bitmap{
        val out = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(out)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val rectangle = Rect(0, 0, bitmap.width, bitmap.height)
        val rectangleF = RectF(rectangle)

        paint.isFilterBitmap = true
        paint.color = Color.GRAY
        canvas.drawColor(0, PorterDuff.Mode.CLEAR)
        canvas.drawRoundRect(rectangleF, px, px, paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawBitmap(bitmap, rectangle, rectangle, paint)
        return out
    }

    fun drawShadow(src: Bitmap, radius: Float): Bitmap {
        val bmOut = Bitmap.createBitmap(src.width + 10, src.height + 10, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmOut)
        canvas.drawColor(0, PorterDuff.Mode.CLEAR)
        val ptBlur = Paint()
        ptBlur.maskFilter = BlurMaskFilter(radius, BlurMaskFilter.Blur.NORMAL)
        val offsetXY = IntArray(2)
        val bmAlpha = src.extractAlpha(ptBlur, offsetXY)
        val paint = Paint()
        paint.color = Color.BLACK
        canvas.drawBitmap(bmAlpha, offsetXY[0].toFloat() + 3F, offsetXY[1].toFloat() + 3F, paint)
        bmAlpha.recycle()
        canvas.drawBitmap(src, 0F, 0F, null)
        return bmOut
    }
}