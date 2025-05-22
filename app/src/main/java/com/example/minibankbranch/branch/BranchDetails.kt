package com.example.minibankbranch.ui.branch

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.minibankbranch.R
import com.example.minibankbranch.branch.NavRoutesEnum
import com.example.minibankbranch.data.Branch
import com.example.minibankbranch.repository.FavoritesManager

@Composable
fun BranchDetails(
    branch: Branch,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isFavorite = FavoritesManager.isFavorite(branch.id)
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp) // Reduced padding
            .systemBarsPadding(),
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp) // Reduced internal padding
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate(NavRoutesEnum.NAV_ROUTE_BRANCHES_LIST.value) }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to Branches List"
                    )
                }
                IconButton(onClick = { FavoritesManager.toggleFavorite(branch.id) }) {
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
            val imageUrl = when (branch.name) {
                "NBK Main Branch - Kuwait City" -> "https://ees-int.com/sites/default/files/styles/scale_and_crop_500px_x500px/public/2021-12/00%20NBK%20-03.jpg?h=b24b6c4b&itok=t0KMbpsh"
                "NBK Regional Branch - Hawalli" -> "https://lh4.googleusercontent.com/-2R0zNvdLULI/VCxf54ulC8I/AAAAAAAACF8/_ZJuvzaCtvIilW2f1wE-WdN_uUEcY8j8ACJkC/s1600-w1000/"
                "NBK Express Branch - Salmiya" -> "https://www.nmcgulf.com/wp-content/uploads/2018/10/WEB-NBK-Salmiya-02.jpg"
                "NBK Regional Branch - Farwaniya" -> "https://art19.ma/en1/wp-content/uploads/2023/08/AF1QipM77Kvel0nQARLi4oPAYHEjY81lIFBWlXUiJjruw408-h306-k-no.jpeg"
                "NBK Express Branch - Fahaheel" -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8ewBd4fQ5iFQW9ZXAqNZO8vXF5VHuBF6Z0w&s"
                else -> null
            }
            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Branch Image",
                    contentScale = ContentScale.Crop,
                    onState = { state ->
                        when (state) {
                            is AsyncImagePainter.State.Error -> {
                                Log.e("BranchDetails", "Failed to load image: $imageUrl, error: ${state.result.throwable}")
                            }
                            is AsyncImagePainter.State.Success -> {
                                Log.d("BranchDetails", "Image loaded successfully: $imageUrl")
                            }
                            else -> {}
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp) // Slightly reduced height
                        .clickable {
                            Log.d("BranchDetails", "Attempting to open image URL: $imageUrl")
                            try {
                                val uri = Uri.parse(imageUrl)
                                if (uri.scheme == "http" || uri.scheme == "https") {
                                    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                    }
                                    context.startActivity(intent)
                                    Log.d("BranchDetails", "Image URL opened successfully: $imageUrl")
                                } else {
                                    Log.w("BranchDetails", "Invalid URL scheme for image: $imageUrl")
                                    Toast.makeText(context, "Invalid image URL", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: ActivityNotFoundException) {
                                Log.w("BranchDetails", "No app found to open image: $imageUrl", e)
                                Toast.makeText(context, "No app available to open the image", Toast.LENGTH_SHORT).show()
                            } catch (e: Exception) {
                                Log.e("BranchDetails", "Unexpected error opening image: $imageUrl", e)
                                Toast.makeText(context, "Error opening image", Toast.LENGTH_SHORT).show()
                            }
                        }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.nbk_logo),
                    contentDescription = "Branch Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = branch.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Type: ${branch.type}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Address: ${branch.address ?: "Not available"}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
            Row(modifier = Modifier.padding(top = 4.dp)) {
                Text(
                    text = "Phone: ",
                    style = MaterialTheme.typography.bodyLarge
                )
                if (branch.phone != null) {
                    Text(
                        text = branch.phone,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.clickable {
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:${branch.phone}")
                            }
                            context.startActivity(intent)
                        }
                    )
                } else {
                    Text(
                        text = "Not available",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Row(modifier = Modifier.padding(top = 4.dp)) {
                Text(
                    text = "Location: ",
                    style = MaterialTheme.typography.bodyLarge
                )
                if (branch.location != null) {
                    Text(
                        text = branch.location,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.clickable {
                            val locationUrl = branch.location ?: return@clickable
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(locationUrl)).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "No app available to open the location", Toast.LENGTH_SHORT).show()
                                Log.e("BranchDetails", "Failed to open location: $locationUrl", e)
                            }
                        }
                    )
                } else {
                    Text(
                        text = "Not available",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            Text(
                text = "Hours: ${branch.hours ?: "Not available"}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}