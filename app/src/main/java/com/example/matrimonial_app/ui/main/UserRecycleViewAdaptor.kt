package com.example.matrimonial_app.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimonial_app.R
import com.squareup.picasso.Picasso

class UserRecycleViewAdaptor(
    private val userList: ArrayList<UserUiModel>
) : RecyclerView.Adapter<UserRecycleViewAdaptor.UserViewHolder>() {

    fun setData(data: List<UserUiModel>) {
        userList.run {
            clear()
            addAll(data)
        }
    }

    class UserViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

        fun bindItem(userUiModel: UserUiModel) {
            Picasso.get().load(userUiModel.imageUrl).into(v.findViewById<ImageView>(R.id.image))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(
                R.layout.user_item_veiw, parent, false
            )
        )
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindItem(userList[position])
    }
}