package com.polinasmogi.gradethephone.ui.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.polinasmogi.gradethephone.db.PhoneEntity
import com.polinasmogi.gradethephone.ui.interfaces.ItemClickExecutor
import kotlinx.android.synthetic.main.item_phone.view.*

class PhoneViewHolder(view: View, executor: ItemClickExecutor) : RecyclerView.ViewHolder(view) {

    fun bind(phoneEntity: PhoneEntity, executor: ItemClickExecutor, position: Int) {
        itemView.phone_name.text = phoneEntity.name

        itemView.phone_grade.setIsIndicator(true)

        if(phoneEntity.grade != null)
            itemView.phone_grade.rating = phoneEntity.grade.toFloat()

        itemView.setOnClickListener {
            executor.execute(position)
        }
    }

}