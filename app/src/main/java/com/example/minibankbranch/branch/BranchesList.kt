package com.example.minibankbranch.ui.branch

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.minibankbranch.branch.BranchCard
import com.example.minibankbranch.data.Branch

@Composable
fun BranchesList(
    navController: NavController,
    branches: List<Branch>,
    onBranchClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(branches) { branch ->
            BranchCard(
                branch = branch,
                onClick = { onBranchClick(branch.id) }
            )
        }
    }
}