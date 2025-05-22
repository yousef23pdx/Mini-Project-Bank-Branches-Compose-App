package com.example.minibankbranch.branch

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.minibankbranch.data.Branch
import com.example.minibankbranch.data.BranchType
import com.example.minibankbranch.repository.BranchRepository
import com.example.minibankbranch.ui.branch.BranchDetails
import com.example.minibankbranch.ui.branch.BranchesList

enum class NavRoutesEnum(val value: String) {
    NAV_ROUTE_BRANCHES_LIST("branchesList"),
    NAV_ROUTE_BRANCH_DETAILS("branchDetails/{branchId}")
}

@Composable
fun BranchesNavHost() {
    val navController = rememberNavController()
    val branches = BranchRepository.branches

    NavHost(
        navController = navController,
        startDestination = NavRoutesEnum.NAV_ROUTE_BRANCHES_LIST.value
    ) {
        composable(NavRoutesEnum.NAV_ROUTE_BRANCHES_LIST.value) {
            BranchesList(
                navController = navController,
                branches = branches,
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
            val branch = branches.find { branch -> branch.id == branchId }
            if (branch != null) {
                BranchDetails(branch = branch, navController = navController)
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
                    navController = navController
                )
            }
        }
    }
}