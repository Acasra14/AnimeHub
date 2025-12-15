package com.example.animehub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.animehub.R
import com.example.animehub.ui.theme.CustomAction

@Composable
fun ImageComp(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    drawable: Int,
    contentDesc: String = "",
    height: Int = 0,
    width: Int = 0
) {
    val contentDescription =
        if (contentDesc.isEmpty())
            stringResource(id = R.string.default_content_descrip)
        else
            contentDesc

    Image(
        painter = painterResource(id = drawable),
        contentDescription = contentDescription,
        modifier = modifier
            .height(height.dp)
            .width(width.dp),
        contentScale = contentScale
    )
}

@Composable
fun StandardButtonComp(
    label: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier.padding(8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = CustomAction,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text = label)
    }
}

@Composable
fun StandardTextComp(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium
) {
    Text(
        modifier = modifier,
        text = text,
        style = style
    )
}