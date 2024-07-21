package com.example.myshoppinglistapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myshoppinglistapp.ui.theme.MyShoppingListAppTheme

data class ShoppingItem(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false
)

@Composable
fun ShoppingListApp() {

    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember { mutableStateOf(false) }

    var ItemName by remember { mutableStateOf("") }
    var ItemQuantity by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text(text = "Add Item")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(sItems) { item ->
                if (item.isEditing) {
                    // updating item
                        ShoppingItemEditor(item = item, onEditComplete = {
                            editedName,editedQuantity ->
                            // disable editing for all items
                            sItems = sItems.map { it.copy(isEditing = false) }
                            // filter item for editing
                            val editedItem = sItems.find { it.id==item.id }
//                            println(editedItem)
                            editedItem?.let {
                                it.name = editedName
                                it.quantity = editedQuantity
                            }

                        })
                } else {
                    ShoppingListItem(
                        item,
                        { // onEditClick Function Call
                            sItems = sItems.map { it.copy(isEditing = it.id == item.id) }

                        },
                        { // onDeleteClick Function call
                            sItems = sItems - item
                        })
                }
            }
        }


    }

    val context = LocalContext.current

    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false }, confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Button(onClick = {
                    if (ItemName.isNotBlank() && ItemQuantity.isNotBlank()) {
                        val newItem = ShoppingItem(
                            id = sItems.size + 1,
                            name = ItemName,
                            quantity = ItemQuantity.toInt()
                        )
                        sItems = sItems + newItem
                        showDialog = false
                        Toast.makeText(
                            context,
                            "Item ID: ${newItem.id} Added!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Please enter item details ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    ItemName = ""
                    ItemQuantity = ""
                }) {
                    Text(text = "Add")
                }
                Button(onClick = { showDialog = false }) {
                    Text(text = "Cancel")
                }
            }
        },
            title = { Text(text = "Add Shopping Item: ") },
            text = {
                Column {
                    OutlinedTextField(
                        value = ItemName, onValueChange = { ItemName = it },
                        singleLine = true,
                        label = { Text(text = "Enter Item Name") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 5.dp)
                    )
                    OutlinedTextField(
                        value = ItemQuantity, onValueChange = { ItemQuantity = it },
                        singleLine = true,
                        label = { Text(text = "Enter Item Quantity") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp, 5.dp)
                    )
                }
            }
        )
        Log.d("Dialog", "ShoppingListApp: ${sItems}")
    }
}

@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Int) -> Unit) {
    val context = LocalContext.current
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Cyan)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Column {
            BasicTextField(
                value = editedName, onValueChange = { editedName = it },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
            BasicTextField(
                value = editedQuantity, onValueChange = { editedQuantity = it },
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
        }
        Button(onClick = {
            isEditing = false
            onEditComplete(editedName, editedQuantity.toIntOrNull() ?: 1)
        }) {
            Text(text = "Save")
        }
    }
}


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(
                border = BorderStroke(2.dp, Color(0XFF018786)),
                shape = RoundedCornerShape(20)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Name: ${item.name}",
            modifier = Modifier
                .padding(4.dp)
        )
        Text(
            text = "Qty: ${item.quantity}",
            modifier = Modifier
                .padding(4.dp)
        )
        Row(modifier = Modifier.padding(4.dp)) {
            IconButton(onClick = onEditClick) {
                Icon(Icons.Default.Edit, contentDescription = "Edit the Item")
            }
            IconButton(onClick = onDeleteClick) {
                Icon(Icons.Default.Delete, contentDescription = "Delete the Item")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ShoppingListPreview() {
    MyShoppingListAppTheme {
        val newItem = ShoppingItem(
            id = 1,
            name = "ItemName",
            quantity = 2
        )
//        ShoppingListApp()
        ShoppingListItem(newItem, {}, {})

    }
}