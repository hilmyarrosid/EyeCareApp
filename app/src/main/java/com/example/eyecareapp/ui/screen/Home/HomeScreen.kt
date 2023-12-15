package com.example.eyecareapp.ui.screen.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eyecareapp.ViewModelFactory
import com.example.eyecareapp.di.Injection
import com.example.eyecareapp.ui.common.UiState
import com.example.eyecareapp.ui.components.content.HomeContent
import com.example.eyecareapp.ui.theme.EyeCareAppTheme

@Composable
fun HomeScreen (
navigateToDetail:(Int) -> Unit,
viewModel: HomeViewModel = viewModel(
factory = ViewModelFactory(Injection.provideRepository(LocalContext.current))
)
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        val query by viewModel.query
        viewModel.glass.collectAsState(initial = UiState.Loading).value.let{
            uiState -> when (uiState){
                is UiState.Loading -> {
                    viewModel.getAllGlass()
                }
                is UiState.Success -> {
                    HomeContent(
                        navigateToDetail = navigateToDetail,
                        query = query,
                        onQueryChange =viewModel::search,
                        glass = uiState.data,
                        Category = viewModel::getAllGlassByCategory
                    )
                }
                is UiState.Error->{}
            }
        }
    }

}

@Preview (showBackground = true)
@Composable
fun prevHomeScreen(){
    EyeCareAppTheme {
        HomeScreen(
            navigateToDetail = {}
        )
    }
}