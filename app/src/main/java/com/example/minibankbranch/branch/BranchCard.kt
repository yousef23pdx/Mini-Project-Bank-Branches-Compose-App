package com.example.minibankbranch.branch

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.minibankbranch.R
import com.example.minibankbranch.data.Branch

@Composable
fun BranchCard(
    branch: Branch,
    viewModel: BranchViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isFavorite = viewModel.isFavorite(branch.id)
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.nbk_logo),
                contentDescription = "NBK Logo",
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 8.dp)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = branch.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Type: ${branch.type}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(onClick = { viewModel.toggleFavorite(branch.id) }) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = if (isFavorite) "Unfavorite" else "Favorite",
                    tint = if (isFavorite) Color.Red else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .size(24.dp)
                        .then(
                            if (isFavorite) {
                                Modifier.shadow(
                                    elevation = 8.dp,
                                    shape = RoundedCornerShape(12.dp),
                                    ambientColor = Color.Red,
                                    spotColor = Color.Red
                                )
                            } else {
                                Modifier
                            }
                        )
                )
            }
        }
    }
}