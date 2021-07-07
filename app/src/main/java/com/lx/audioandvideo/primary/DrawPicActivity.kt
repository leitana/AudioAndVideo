package com.lx.audioandvideo.primary

import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
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

    val path: String = PathUtils.getRootPathExternalFirst() + File.separator +  "AV/image"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_pic)
        showPicWithImageViewWithPermissionCheck()
    }

    /**
     * ImageView 绘制图片
     */
    @NeedsPermission(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    fun showPicWithImageView(){
        val bitmap = BitmapFactory.decodeFile(path + File.separator + "AVTEST.jpg")
        imageview.setImageBitmap(bitmap)
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