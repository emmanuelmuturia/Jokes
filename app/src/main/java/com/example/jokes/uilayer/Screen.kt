package com.example.jokes.uilayer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.example.jokes.datalayer.Joke

@Composable
fun JokesApp(jokesViewModel: JokesViewModel, modifier: Modifier = Modifier) {

    val myJokesState by jokesViewModel.jokesState.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("Jokes") }) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    jokesViewModel.getJokes()
                },
                backgroundColor = Color.Gray,
                shape = RoundedCornerShape(16.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.Refresh,
                    contentDescription = "Add FAB",
                    tint = Color.White,
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colors.background
        ) {
            HomeScreen(jokesState = myJokesState)
        }
    }
}



@Composable
fun JokesScreen(myJokes: List<Joke>) {
    LazyColumn {
        items(myJokes) { jokes ->
            JokesCard(jokes = jokes)
        }
    }
}


@Composable
fun JokesCard(jokes: Joke) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(7.dp),
        elevation = 7.dp,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "ID: ${jokes.id}", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = "Type: ${jokes.type}")
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = jokes.setup)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = jokes.punchline)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun JokesScreenPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        JokesScreen(myJokes = listOf(Joke("Comical", "Blah Blah Blah", "Blah!", 3)))
    }
}