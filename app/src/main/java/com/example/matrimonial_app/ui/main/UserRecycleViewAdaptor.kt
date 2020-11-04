package com.example.matrimonial_app.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimonial_app.R
import com.squareup.picasso.Picasso
import java.util.Objects

class UserRecycleViewAdaptor(
    private var userList: List<UserUiModel>,
    private val selectUserAction: (userId: String, selected: Boolean) -> Unit
) : RecyclerView.Adapter<UserRecycleViewAdaptor.UserViewHolder>() {

    fun setData(data: List<UserUiModel>) {
        DiffUtil.calculateDiff(DiffCallback(userList, data)).also {
            it.dispatchUpdatesTo(this)
            userList = data
        }
    }

    class UserViewHolder(private val v: View) : RecyclerView.ViewHolder(v) {

        fun bindItem(userUiModel: UserUiModel, selectUser: (userId: String, selected: Boolean) -> Unit) {
            Picasso.get().load(userUiModel.imageUrl)
                .error(R.drawable.person_drawable)
                .placeholder(R.drawable.progress_animation)
                .into(v.findViewById<ImageView>(R.id.image))
            v.findViewById<TextView>(R.id.name).also { it.text = userUiModel.name }
            v.findViewById<TextView>(R.id.dob_txt).also { it.text = userUiModel.age.toString() }
            v.findViewById<TextView>(R.id.phn_text).also { it.text = userUiModel.number }
            v.findViewById<TextView>(R.id.address).also { it.text = userUiModel.address }
            val declinedText = v.findViewById<TextView>(R.id.declined_txt)
            val acceptedText = v.findViewById<TextView>(R.id.accept_txt)
            val declineBtn = v.findViewById<ImageButton>(R.id.decline_btn).apply {
                isEnabled = userUiModel.acceptDeclineBtnEnabled
            }

            val acceptBtn = v.findViewById<ImageButton>(R.id.accept_btn).apply {
                isEnabled = userUiModel.acceptDeclineBtnEnabled
            }

            when (userUiModel.selected) {
                null -> {
                    acceptBtn.visibility = View.VISIBLE
                    declineBtn.visibility = View.VISIBLE
                    declinedText.visibility = View.GONE
                    acceptedText.visibility = View.GONE
                }
                true -> {
                    acceptBtn.visibility = View.GONE
                    declineBtn.visibility = View.VISIBLE
                    declinedText.visibility = View.GONE
                    acceptedText.visibility = View.VISIBLE
                }
                false -> {
                    acceptBtn.visibility = View.VISIBLE
                    declineBtn.visibility = View.GONE
                    declinedText.visibility = View.VISIBLE
                    acceptedText.visibility = View.GONE
                }
            }

            declineBtn.setOnClickListener {
                userUiModel.acceptDeclineBtnEnabled = false
                selectUser(userUiModel.userId, false)
            }

            acceptBtn.setOnClickListener {
                userUiModel.acceptDeclineBtnEnabled = false
                selectUser(userUiModel.userId, true)
            }
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
        holder.bindItem(userList[position], selectUserAction)
    }

    class DiffCallback(
        private val oldUserUiModels: List<UserUiModel>,
        private val newUserUiModels: List<UserUiModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldUserUiModels.size

        override fun getNewListSize(): Int = newUserUiModels.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldUserUiModels[oldItemPosition].userId == newUserUiModels[newItemPosition].userId

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldUserUiModel = oldUserUiModels[oldItemPosition]
            val newUserUiModel = newUserUiModels[newItemPosition]
            return oldUserUiModel.name == newUserUiModel.name &&
                    oldUserUiModel.age == newUserUiModel.age &&
                    oldUserUiModel.number == newUserUiModel.number &&
                    oldUserUiModel.address == newUserUiModel.address &&
                    Objects.equals(oldUserUiModel.imageUrl, newUserUiModel.imageUrl) &&
                    Objects.equals(oldUserUiModel.selected, newUserUiModel.selected)
        }

    }
}