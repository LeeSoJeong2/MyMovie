package com.example.mymovie

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL

class ImageLoadTask(private val urlStr:String, private val imageView: ImageView): AsyncTask<Void, Void, Bitmap>() {
    private val bitmapHash = HashMap<String, Bitmap>()

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Void?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            if (bitmapHash.containsKey(urlStr)) {
                var oldBitmap: Bitmap? = bitmapHash.remove(urlStr)
                if (oldBitmap != null) {
                    oldBitmap.recycle()
                    oldBitmap = null
                }
            }

            val url = URL(urlStr)
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

            bitmapHash.put(urlStr, bitmap)

        } catch(e:Exception) {
            e.printStackTrace()
        }

        return bitmap
    }

    override fun onProgressUpdate(vararg values: Void?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)

        imageView.setImageBitmap(result)
        imageView.invalidate()
    }


}