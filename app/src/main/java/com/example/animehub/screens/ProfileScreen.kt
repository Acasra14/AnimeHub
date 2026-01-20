package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.animehub.R
import com.example.animehub.ui.components.StandardButtonComp
import com.example.animehub.viewmodel.AnimeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: AnimeViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (viewModel.isLoggedIn) stringResource(R.string.profile_status_logged_in)
            else stringResource(R.string.profile_status_logged_out)
        )
        StandardButtonComp(
            label = if (viewModel.isLoggedIn) stringResource(R.string.logout_button)
            else stringResource(R.string.login_button),
            onClick = { viewModel.isLoggedIn = !viewModel.isLoggedIn }
        )
    }
}