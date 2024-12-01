package com.example.moviescout.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moviescout.data.repository.MoviesCategory

data class ChipOption(
    val label: String,
    val value: MoviesCategory
)

@Composable
fun MoviesListFilterChips(
    categories: List<ChipOption>,
    selectedCategory: ChipOption,
    onCategorySelected: (ChipOption) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        categories.forEach { option ->
            FilterChip(
                selected = selectedCategory.value == option.value,
                onClick = { onCategorySelected(option) },
                label = { Text(option.label) },
                modifier = Modifier.height(32.dp)
            )
        }
    }
}
