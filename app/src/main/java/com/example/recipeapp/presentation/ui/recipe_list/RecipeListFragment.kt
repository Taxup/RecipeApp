package com.example.recipeapp.presentation.ui.recipe_list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.recipeapp.presentation.components.RecipeCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value
                val query = viewModel.query.value

                viewModel.newSearch(query = query)
                Column {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        elevation = 8.dp
                    ) {
                        Column {
                            Row(modifier = Modifier.fillMaxWidth()) {
                                TextField(
                                    value = query,
                                    onValueChange = { viewModel.onQueryChanged(it) },
                                    modifier = Modifier
                                        .fillMaxWidth(0.9f)
                                        .padding(8.dp),
                                    label = {
                                        Text(text = "Search")
                                    },
                                    keyboardOptions = KeyboardOptions(
                                        keyboardType = KeyboardType.Text,
                                        imeAction = ImeAction.Search
                                    ),
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Filled.Search,
                                            contentDescription = ""
                                        )
                                    },
                                    onImeActionPerformed = { action, softwareKeyboardController ->
                                        if (action == ImeAction.Search)
//                                        viewModel.newSearch("")
                                            softwareKeyboardController?.hideSoftwareKeyboard()
                                    },
                                    textStyle = TextStyle(MaterialTheme.colors.onSurface),
                                    backgroundColor = MaterialTheme.colors.surface
                                )
                            }
                            ScrollableRow(modifier = Modifier.fillMaxWidth()) {
                                for (category in getAllFoodCategories()) {
                                    Text(
                                        text = category.value,
                                        style = MaterialTheme.typography.body2,
                                        color = Color.White,
                                        modifier = Modifier.padding(8.dp),
                                    )
//                                    FoodCategoryChip(
//                                        category = category.value,
//                                        onExecuteSearch = {
//                                            viewModel.newSearch(category.value)
//                                        }
//                                    )
                                }
                            }
                        }
                    }
                    LazyColumn {
                        itemsIndexed(
                            items = recipes
                        ) { index, item ->
                            RecipeCard(recipe = item, onClick = {})
                        }
                    }
                }
            }
        }
    }
}
