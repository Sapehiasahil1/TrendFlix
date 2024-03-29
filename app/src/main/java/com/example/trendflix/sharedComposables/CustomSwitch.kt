package com.example.trendflix.sharedComposables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomSwitch(
    checkable: Boolean,
    checked: Boolean = true,
    onCheckedChange: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(12.dp)
            .clip(CircleShape)
            .width(44.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (checked) Color(0XFFC9F964).copy(alpha = 0.18f)
                    else Color(0XFF423460).copy(alpha = 0.78f)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (checked) Arrangement.End else Arrangement.Start
        ) {
            Box(modifier = Modifier
                .padding(2.dp)
                .clip(CircleShape)
                .size(18.dp)
                .background(
                    if (checked) Color(0XFFC9F964)
                    else Color(0X9495B1FF).copy(alpha = 0.4f)
                )
                .clickable {
                    if (checkable) {
                        onCheckedChange()
                    }
                })
        }
    }
}