package com.example.memoapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.memoapp.R

/**
 * ItemInputForm:アイテムの詳細を入力するComposable関数
 * @param itemDetails アイテムの詳細
 * @param modifier 修飾子
 * @param onValueChange 詳細が変更された時に呼び出されるコールバック関数
 */
@Composable
fun ItemInputForm(
    itemDetails: ItemDetails,
    modifier: Modifier = Modifier,
    onValueChange: (ItemDetails) -> Unit = {}
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(id = R.dimen.padding_medium)
        )
    ) {
        OutlinedTextField(
            value = itemDetails.title,
            onValueChange = { onValueChange(itemDetails.copy(title = it)) },
            label = { Text(text = "タイトル*") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = itemDetails.description,
            onValueChange = {
                onValueChange(itemDetails.copy(description = it))
            },
            label = { Text(text = "詳細*") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .toggleable(
                    value = itemDetails.done,
                    onValueChange = { onValueChange(itemDetails.copy(done = it)) },
                    role = Role.Checkbox
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = itemDetails.done, onCheckedChange = null)
            Text(
                text = "DONE",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Text(
            text = "*は必須入力です",
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemInputFormPre() {
    ItemInputForm(itemDetails = ItemDetails())
}