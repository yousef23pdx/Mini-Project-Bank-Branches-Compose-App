package com.example.minibankbranch.branch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.minibankbranch.data.Branch
import com.example.minibankbranch.repository.BranchRepository
import com.example.minibankbranch.repository.FavoritesManager

class BranchViewModel : ViewModel() {
    var branches by mutableStateOf(BranchRepository.branches)
        private set
    var filteredBranches by mutableStateOf(BranchRepository.branches)
        private set
    var searchQuery by mutableStateOf("")
        private set
    var showFavoritesOnly by mutableStateOf(false)
        private set

    fun toggleFavorite(branchId: Int) {
        FavoritesManager.toggleFavorite(branchId)
    }

    fun isFavorite(branchId: Int): Boolean {
        return FavoritesManager.isFavorite(branchId)
    }

    fun findBranch(branchId: Int): Branch? {
        return branches.find { branch -> branch.id == branchId }
    }

    fun addBranch(branch: Branch) {
        val newBranchesList = ArrayList(branches)
        newBranchesList.add(branch)
        branches = newBranchesList.toList()
        updateFilteredBranches()
    }

    fun updateSearchQuery(query: String) {
        searchQuery = query
        updateFilteredBranches()
    }

    fun toggleFavoritesView() {
        showFavoritesOnly = !showFavoritesOnly
        updateFilteredBranches()
    }

    fun refreshBranches() {
        branches = BranchRepository.branches
        showFavoritesOnly = false
        searchQuery = ""
        updateFilteredBranches()
    }

    private fun updateFilteredBranches() {
        filteredBranches = branches.filter { branch ->
            (searchQuery.isEmpty() ||
                    branch.name.contains(searchQuery, ignoreCase = true) ||
                    (branch.address?.contains(searchQuery, ignoreCase = true) ?: false))
                    && (!showFavoritesOnly || isFavorite(branch.id))
        }
    }
}