package com.example.minibankbranch.branch

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.minibankbranch.data.Branch
import com.example.minibankbranch.data.BranchType

@Composable
fun BranchesList(
    navController: NavController,
    viewModel: BranchViewModel,
    onBranchClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize()) {
        Column(modifier = modifier.fillMaxSize()) {
            SearchBar(viewModel = viewModel)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { viewModel.toggleFavoritesView() }) {
                    Icon(Icons.Default.Favorite, contentDescription = "Toggle Favorites View")
                    Text("Favorites")
                }
                Button(onClick = { viewModel.refreshBranches() }) {
                    Icon(Icons.Default.Refresh, contentDescription = "Refresh Branches")
                    Text("Refresh")
                }
            }
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(viewModel.filteredBranches) { branch ->
                    BranchCard(
                        branch = branch,
                        viewModel = viewModel,
                        onClick = { onBranchClick(branch.id) }
                    )
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
            onClick = {
                viewModel.addBranch(
                    Branch(
                        id = viewModel.branches.size + 1,
                        name = "New Branch",
                        type = BranchType.EXPRESS,
                        address = "New Address",
                        phone = "123-456-7890",
                        hours = "09:00-14:00",
                        location = null,
                        imageUri = null
                    )
                )
            }
        ) {
            Text(text = "+")
        }
    }
}