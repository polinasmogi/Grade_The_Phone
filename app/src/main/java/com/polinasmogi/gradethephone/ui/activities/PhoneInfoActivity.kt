package com.polinasmogi.gradethephone.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.polinasmogi.gradethephone.db.AppDatabase
import com.polinasmogi.gradethephone.db.PhoneDao
import com.polinasmogi.gradethephone.ui.interfaces.Constants
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.activity_phone_info.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


class PhoneInfoActivity : AppCompatActivity() {

    val TAG = "PhoneInfoActivity"

    private lateinit var database: PhoneDao
    private lateinit var photos : Array<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.polinasmogi.gradethephone.R.layout.activity_phone_info)

        init()
    }

    private fun init() {

        val phoneId = intent.getIntExtra(Constants.PHONE_ID, 0)

        database = AppDatabase.getInstance(this@PhoneInfoActivity).phoneDao()

        photo_carousel.setImageListener(imageListener)

        getPhoneAndSetInfo(phoneId)

        phone_grade.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            saveRating(phoneId, rating)
        }

    }


    private fun getPhoneAndSetInfo(phoneId: Int) {
        doAsync {

            val phoneEntity = database.getPhoneById(phoneId)

            uiThread {
                phone_name.text = phoneEntity.name

                photos = arrayOf(phoneEntity.photo1, phoneEntity.photo2, phoneEntity.photo3)
                //Указываю количество фото для карусели.
                //У некоторых телефонов загружены не все фотографии, поэтому я исключаю null.
                photo_carousel.pageCount =
                    Arrays.stream(photos).filter { e -> e != null }.count().toInt()

                if (phoneEntity.grade != null)
                    phone_grade.rating = phoneEntity.grade.toFloat()
            }

        }
    }

    private fun saveRating(phoneId: Int, rating: Float) {
        doAsync {
            database.updatePhone(phoneId, rating.toInt())
        }
    }

    private val imageListener = ImageListener { position, imageView ->
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transforms(CenterCrop())
        Glide.with(this@PhoneInfoActivity)
            .load(photos[position])
            .apply(requestOptions)
            .into(imageView!!)
    }

}