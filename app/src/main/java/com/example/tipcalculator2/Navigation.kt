package com.example.tipcalculator2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

// Routes are strings, so we keep them in constants
object Routes {
    const val HOME = "home"
    const val LIST = "list"
    const val ABOUT = "about"
}

data class Project(val id: Int, val name: String, val status: String)

val sampleProjects = listOf(
    Project(1, "Farm Market App", "In progress"),
    Project(2, "Campus Navigator", "Planning"),
    Project(3, "Clinic Booking", "Done")
)

data class Tab(val route: String, val label: String, val icon: ImageVector)

@Composable
fun ProjectApp() {
    val nav = rememberNavController()
    val backStackEntry by nav.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val tabs = listOf(
        Tab(Routes.HOME, "Home", Icons.Filled.Home),
        Tab(Routes.LIST, "List", Icons.AutoMirrored.Filled.List),
        Tab(Routes.ABOUT, "About", Icons.Filled.Info)
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = currentRoute == tab.route,   // follows the current route
                        onClick = {
                            nav.navigate(tab.route) {
                                popUpTo(nav.graph.findStartDestination().id) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(tab.icon, contentDescription = null) },
                        label = { Text(tab.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = nav,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(padding)
        ) {
            composable(Routes.HOME) { HomeScreen() }
            composable(Routes.LIST) { ListScreen() }
            composable(Routes.ABOUT) { AboutScreen() }
        }
    }
}

@Composable
fun HomeScreen() {
    TipCalc()
}

@Composable
fun ListScreen() {
    LazyColumn {
        items(sampleProjects, key = { it.id }) { p ->   // key keeps rows stable
            ListItem(
                headlineContent = { Text(p.name) },
                supportingContent = { Text(p.status) }
            )
        }
    }
}

@Composable
fun AboutScreen() {
    Column(Modifier.padding(24.dp)) {
        Text("About", style = MaterialTheme.typography.headlineMedium)
        Text("BIT 2320 Mobile Application Development prototype.")
    }
}