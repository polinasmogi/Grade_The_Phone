package com.polinasmogi.gradethephone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.polinasmogi.gradethephone.R
import com.polinasmogi.gradethephone.db.PhoneEntity
import com.polinasmogi.gradethephone.ui.interfaces.ItemClickExecutor

class PhonesAdapter(private val executor: ItemClickExecutor): ListAdapter<PhoneEntity, PhoneViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_phone, parent, false)
        return PhoneViewHolder (view, executor)
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(getItem(position), executor, position)
    }

    fun getPhone(position: Int): PhoneEntity{
        return getItem(position)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PhoneEntity>() {
            override fun areItemsTheSame(oldItem: PhoneEntity, newItem: PhoneEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhoneEntity, newItem: PhoneEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}