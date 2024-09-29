package com.example.employeedir.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.employeedir.model.EmployeeListState
import com.example.employeedir.model.network.Employees
import com.example.employeedir.R.drawable.placeholder
@Composable
fun EmployeeListScreen(modifier: Modifier = Modifier, viewModel: EmployeeViewModel) {
    val employeeListState by viewModel.employeeListState.observeAsState(EmployeeListState.Loading)

    Column(modifier = Modifier.fillMaxSize()) {
        // Add refresh and sort buttons at the top
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Refresh button
            Button(onClick = { viewModel.refreshEmployeeList() }) {
                Text(text = "Refresh List")
            }

            // Toggle sorting button (switches between sorting by name and team)
            Button(onClick = { viewModel.toggleSortOption() }) {
                val buttonText = if (viewModel.sortOption.value == EmployeeViewModel.SortOption.NAME) "Sort by Team" else "Sort by Name"
                Text(buttonText)
            }
        }

        // Now, handle the different states for the employee list
        when (employeeListState) {
            is EmployeeListState.Loading -> {
                // Loading state: Show a progress indicator
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is EmployeeListState.Success -> {
                // Success state: Show the employee list
                EmployeeList(
                    employees = (employeeListState as EmployeeListState.Success).employees,
                    modifier = Modifier.weight(1f)
                )
            }
            is EmployeeListState.Empty -> {
                // Empty state: Show "No employees" message
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No employees available")
                }
            }
            is EmployeeListState.Error -> {
                // Error state: Show error message
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = (employeeListState as EmployeeListState.Error).message)
                }
            }
        }
    }
}

@Composable
fun EmployeeList(employees: List<Employees>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(employees) { employee ->
            EmployeeItem(employee = employee)
        }
    }
}

@Composable
fun EmployeeItem(employee: Employees) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = employee.photo_url_small ?: placeholder, // Use placeholder if image URL is null
                    error = painterResource(placeholder) // Fallback image on error
                ),
                contentDescription = "Employee Photo",
                modifier = Modifier
                    .size(64.dp)
                    .padding(end = 16.dp)
            )
            Column {
                Text(
                    text = employee.full_name ?: "Unknown", // Fallback for null names
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = employee.team ?: "Unknown Team", // Fallback for null teams
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


