package com.example.minibankbranch.repository

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

object FavoritesManager {
    private val favoriteBranchIds: SnapshotStateList<Int> = mutableStateListOf()

    fun isFavorite(branchId: Int): Boolean {
        return favoriteBranchIds.contains(branchId)
    }

    fun toggleFavorite(branchId: Int) {
        if (isFavorite(branchId)) {
            favoriteBranchIds.remove(branchId)
        } else {
            favoriteBranchIds.add(branchId)
        }
    }

    fun getFavorites(): List<Int> {
        return favoriteBranchIds.toList()
    }
}