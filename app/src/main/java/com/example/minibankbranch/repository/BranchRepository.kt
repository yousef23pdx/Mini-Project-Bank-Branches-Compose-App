package com.example.minibankbranch.repository

import com.example.minibankbranch.data.Branch
import com.example.minibankbranch.data.BranchType

object BranchRepository {
    val branches = listOf(
        Branch(
            id = 1,
            name = "NBK Main Branch - Kuwait City",
            type = BranchType.MAIN,
            address = "Abdullah Al-Salem St, Kuwait City, Kuwait",
            phone = "+965 1800 805",
            hours = "Sun-Thu 08:00-15:00",
            location = "https://maps.app.goo.gl/SexPwoWYWUAkgicn9",
            imageUri = null
        ),
        Branch(
            id = 2,
            name = "NBK Regional Branch - Hawalli",
            type = BranchType.REGIONAL,
            address = "Ibn Khaldoun St, Hawalli, Kuwait",
            phone = "+965 1800 806",
            hours = "Sun-Thu 08:00-16:00, Sat 09:00-12:00",
            location = "https://maps.app.goo.gl/Wh1Wd2YnrU8taHkeA",
            imageUri = null
        ),
        Branch(
            id = 3,
            name = "NBK Express Branch - Salmiya",
            type = BranchType.EXPRESS,
            address = null,
            phone = "+965 1800 807",
            hours = "Sun-Thu 10:00-14:00",
            location = "https://maps.app.goo.gl/413tAnJYxooz3vg38",
            imageUri = null
        ),
        Branch(
            id = 4,
            name = "NBK Regional Branch - Farwaniya",
            type = BranchType.REGIONAL,
            address = "Habib Munawer St, Farwaniya, Kuwait",
            phone = "+965 1800 808",
            hours = "Sun-Thu 08:00-15:00, Sat 09:00-13:00",
            location = "https://maps.app.goo.gl/7XS8GfTdWgguRuTE7",
            imageUri = null
        ),
        Branch(
            id = 5,
            name = "NBK Express Branch - Fahaheel",
            type = BranchType.EXPRESS,
            address = "Al Dabbous St, Fahaheel, Kuwait",
            phone = "+965 1800 809",
            hours = "Sun-Thu 09:00-13:00",
            location = "https://maps.app.goo.gl/vounPxcRtybdmVTf9",
            imageUri = null
        )
    )
}