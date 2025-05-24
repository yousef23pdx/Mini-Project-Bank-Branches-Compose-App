package com.example.minibankbranch.branch

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.minibankbranch.data.Branch
import com.example.minibankbranch.data.BranchType

enum class NavRoutesEnum(val value: String) {
    NAV_ROUTE_BRANCHES_LIST("branchesList"),
    NAV_ROUTE_BRANCH_DETAILS("branchDetails/{branchId}")
}

@Composable
fun BranchesNavHost() {
    val navController = rememberNavController()
    val viewModel: BranchViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavRoutesEnum.NAV_ROUTE_BRANCHES_LIST.value
    ) {
        composable(NavRoutesEnum.NAV_ROUTE_BRANCHES_LIST.value) {
            BranchesList(
                navController = navController,
                viewModel = viewModel,
                onBranchClick = { branchId ->
                    navController.navigate("branchDetails/$branchId")
                }
            )
        }
        composable(
            route = NavRoutesEnum.NAV_ROUTE_BRANCH_DETAILS.value,
            arguments = listOf(navArgument("branchId") { type = NavType.IntType })
        ) { navEntry ->
            val branchId = navEntry.arguments?.getInt("branchId")
            val branch = viewModel.findBranch(branchId ?: -1)
            if (branch != null) {
                BranchDetails(
                    branch = branch,
                    navController = navController,
                    viewModel = viewModel
                )
            } else {
                BranchDetails(
                    branch = Branch(
                        id = -1,
                        name = "Branch Not Found",
                        type = BranchType.MAIN,
                        address = null,
                        phone = null,
                        hours = null,
                        location = null,
                        imageUri = null
                    ),
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}