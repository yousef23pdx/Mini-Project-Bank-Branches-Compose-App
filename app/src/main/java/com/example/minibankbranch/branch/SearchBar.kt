package com.example.minibankbranch.branch

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(viewModel: BranchViewModel, modifier: Modifier = Modifier) {
    OutlinedTextField(
        value = viewModel.searchQuery,
        onValueChange = { viewModel.updateSearchQuery(it) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
        placeholder = { Text("Search by name or address") },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        singleLine = true
    )
}