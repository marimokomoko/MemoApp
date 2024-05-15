package com.example.memoapp.ui.item

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.memoapp.R
import com.example.memoapp.ui.AppViewModelProvider
import com.example.memoapp.ui.TodoTopAppBar
import com.example.memoapp.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

/**
 * ItemEditDestination:編集画面への画面遷移を表すオブジェクト
 */
object ItemEditDestination : NavigationDestination {
    override val route = "item_edit"
    override val titleRes = R.string.edit_item_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}
/**
 * ItemEditScreen:編集画面
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit = {},
    onNavigateUp: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: ItemEditViewModel = viewModel(
        factory = AppViewModelProvider.Factory
    )
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TodoTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateItem()
                }
                navigateBack()
            },
            showDelete = true,
            onDeleteClick = {},
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
private fun ItemEditScreenPreview() {
    ItemEditScreen()
}
