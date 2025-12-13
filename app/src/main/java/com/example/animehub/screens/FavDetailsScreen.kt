package com.example.animehub.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.animehub.R
import com.example.animehub.data.Datasource
import com.example.animehub.ui.components.ImageComp
import com.example.animehub.ui.components.StandardButtonComp
import com.example.animehub.ui.components.StandardTextComp
import com.example.animehub.ui.components.StandardInputTextComp
import androidx.compose.material3.HorizontalDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavDetailsScreen(name: String, navController: NavController, modifier: Modifier = Modifier) {
    val element = Datasource.getElementByName(name)

    var userComment by rememberSaveable { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.fav_details_title, element?.name ?: "")) })
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            element?.let {
                ImageComp(
                    drawable = Datasource.getDrawableIdByName(it.photo),
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .padding(16.dp)
                        .widthIn(200.dp, 300.dp)
                        .fillMaxWidth(),
                )
                StandardTextComp(text = it.name, style = MaterialTheme.typography.headlineMedium)
                StandardTextComp(text = it.description, style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(24.dp))

                HorizontalDivider()

                StandardTextComp(
                    text = stringResource(R.string.user_comments_label),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                StandardInputTextComp(
                    label = stringResource(R.string.comment_hint),
                    value = userComment,
                    onValueChange = { userComment = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp),
                    singleLine = false
                )

                StandardButtonComp(
                    label = stringResource(R.string.save_comment),
                    modifier = Modifier.padding(top = 8.dp)
                ) {

                }

            } ?: StandardTextComp(stringResource(R.string.element_not_found))

            Spacer(modifier = Modifier.height(32.dp))
            StandardButtonComp(
                label = stringResource(R.string.back),
                onClick = { navController.navigateUp() }
            )
        }
    }
}