package com.example.minibankbranch.data

data class Branch(
    val id: Int,
    val name: String,
    val type: BranchType,
    val address: String?,
    val phone: String?,
    val hours: String?,
    val location: String?,
    val imageUri: Int? = null,
    val imageUrl: String? = null // New field
)

enum class BranchType {
    MAIN, REGIONAL, EXPRESS
}