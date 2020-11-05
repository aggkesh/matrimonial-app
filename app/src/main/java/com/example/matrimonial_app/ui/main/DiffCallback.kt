package com.example.matrimonial_app.ui.main

import androidx.recyclerview.widget.DiffUtil
import com.example.matrimonial_app.ui.main.models.UserUiModel
import java.util.Objects

/**
 * Created by Keshav Aggarwal 11/4/2020
 *
 * Diff Callback used by [UserRecycleViewAdaptor] to find difference between
 * old [UserUiModel] list and new [UserUiModel]
 */
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
                oldUserUiModel.dob == newUserUiModel.dob &&
                oldUserUiModel.number == newUserUiModel.number &&
                oldUserUiModel.address == newUserUiModel.address &&
                Objects.equals(oldUserUiModel.imageUrl, newUserUiModel.imageUrl) &&
                Objects.equals(oldUserUiModel.selected, newUserUiModel.selected)
    }

}