package com.lx.audioandvideo.primary

import android.Manifest
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Bundle
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.PathUtils
import com.lx.audioandvideo.R
import kotlinx.android.synthetic.main.activity_draw_pic.*
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import java.io.File

/**
 * @title：DrawPicActivity
 * @projectName AudioAndVideo
 * @description: 通过三种方式绘制图片
 * @author linxiao
 * @data Created in 2021/07/07
 */
@RuntimePermissions
class DrawPicActivity: AppCompatActivity() {

    private val path: String = PathUtils.getRootPathExternalFirst() + File.separator +  "AV/image"
    private val bitmap = BitmapFactory.decodeFile(path + File.separator + "AVTEST.jpg")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_pic)
        showPicWithImageViewWithPermissionCheck()
        showPicWithSurfaceViewWithPermissionCheck()
    }

    /**
     * ImageView 绘制图片
     */
    @NeedsPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showPicWithImageView(){
        imageview.setImageBitmap(bitmap)
    }

    @NeedsPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showPicWithSurfaceView(){
        surfaceView.holder.addCallback(object : SurfaceHolder.Callback{
            override fun surfaceCreated(holder: SurfaceHolder) {
                val paint = Paint()
                paint.isAntiAlias = true
                paint.style = Paint.Style.STROKE
                val canvas = holder.lockCanvas()// 先锁定当前surfaceView的画布
                canvas.drawBitmap(bitmap, 0f, 0f, paint) //执行绘制操作
                holder.unlockCanvasAndPost(canvas) // 解除锁定并显示在界面上
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
            }

        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        // NOTE: delegate the permission handling to generated function
        onRequestPermissionsResult(requestCode, grantResults)
    }
}