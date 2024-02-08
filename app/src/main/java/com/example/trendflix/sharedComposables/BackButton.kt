package com.example.trendflix.sharedComposables

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trendflix.ui.theme.AppOnPrimaryColor

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    FloatingActionButton(
        modifier = modifier.size(42.dp),
        contentColor = AppOnPrimaryColor,
        onClick = {onClick()}
    ) {
        Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription ="back icon" )
    }
}