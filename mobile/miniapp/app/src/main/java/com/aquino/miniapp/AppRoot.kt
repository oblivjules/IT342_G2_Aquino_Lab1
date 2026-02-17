package com.aquino.miniapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aquino.miniapp.data.repository.AuthRepository
import com.aquino.miniapp.ui.navigation.Routes
import com.aquino.miniapp.ui.screens.DashboardScreen
import com.aquino.miniapp.ui.screens.LoginScreen
import com.aquino.miniapp.ui.screens.ProfileScreen
import com.aquino.miniapp.ui.screens.RegisterScreen
import com.aquino.miniapp.ui.viewmodel.AuthViewModel
import com.aquino.miniapp.ui.viewmodel.AuthViewModelFactory

@Composable
fun AppRoot() {
    val context = LocalContext.current
    val repository = remember { AuthRepository(context) }
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(repository))
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()

    if (!uiState.isInitialized) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }
        return
    }

    val startDestination = if (uiState.user != null) Routes.DASHBOARD else Routes.LOGIN

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.LOGIN) {
            LoginScreen(
                uiState = uiState,
                onLogin = { identifier, password -> viewModel.login(identifier, password) },
                onLoginSuccess = {
                    navController.navigate(Routes.DASHBOARD) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onRegisterClick = { navController.navigate(Routes.REGISTER) },
                onClearMessages = { viewModel.clearMessages() }
            )
        }
        composable(Routes.REGISTER) {
            RegisterScreen(
                uiState = uiState,
                onRegister = { username, email, password, firstName, lastName ->
                    viewModel.register(username, email, password, firstName, lastName)
                },
                onRegisterSuccess = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                },
                onLoginClick = { navController.navigate(Routes.LOGIN) },
                onClearMessages = { viewModel.clearMessages() }
            )
        }
        composable(Routes.DASHBOARD) {
            val user = uiState.user
            if (user == null) {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.DASHBOARD) { inclusive = true }
                }
            } else {
                DashboardScreen(
                    user = user,
                    onViewProfile = { navController.navigate(Routes.PROFILE) },
                    onLogout = {
                        viewModel.logout()
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.DASHBOARD) { inclusive = true }
                        }
                    }
                )
            }
        }
        composable(Routes.PROFILE) {
            val user = uiState.user
            if (user == null) {
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.PROFILE) { inclusive = true }
                }
            } else {
                ProfileScreen(user = user, onBack = { navController.navigateUp() })
            }
        }
    }
}
