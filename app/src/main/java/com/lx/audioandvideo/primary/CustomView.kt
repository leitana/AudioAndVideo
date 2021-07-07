package com.lx.audioandvideo.primary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Environment
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.blankj.utilcode.util.PathUtils
import java.io.File


/**
 * @title：CustomView
 * @projectName AudioAndVideo
 * @description: <Description>
 * @author linxiao
 * @data Created in 2021/07/07
 */
class CustomView@JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0) :
    View(context, attributeSet, defStyleAttr) {
    private val path: String = PathUtils.getRootPathExternalFirst() + File.separator +  "AV/image"
    var paint: Paint = Paint()
    var bitmap: Bitmap? = null

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        bitmap =
            BitmapFactory.decodeFile(path + File.separator + "AVTEST.jpg")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 不建议在onDraw做任何分配内存的操作
        if (bitmap != null) {
            canvas.drawBitmap(bitmap!!, 0f, 0f, paint)
        }
    }
}