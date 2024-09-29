package com.example.employeedir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.employeedir.model.EmployeeRepository

import com.example.employeedir.model.network.RetrofitInstance.apiInterface
import com.example.employeedir.ui.theme.EmployeeDirTheme
import com.example.employeedir.ui.theme.EmployeeListScreen
import com.example.employeedir.ui.theme.EmployeeViewModel
import com.example.employeedir.ui.theme.EmployeeViewModelFactory


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = EmployeeRepository(apiInterface)
        val viewModelFactory = EmployeeViewModelFactory(repository, application)
        val dataViewModel = ViewModelProvider(this, viewModelFactory)[EmployeeViewModel::class.java]

        setContent {
            EmployeeDirTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        EmployeeListScreen(
                            viewModel = dataViewModel,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                )
            }
        }
    }
}


    val number : Map<Int, Int>  = mapOf(1 to 2, 2 to 3, 3 to 4);
    fun isEven(number : Int) : Boolean{
        return number %2 == 0;
    }
    val doubledNumbers = number.map { it.key to it.value * 2 }.toMap()
    val evenNumbers = number.filter { it.value % 2 == 0 }

    @Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmployeeDirTheme {
        Greeting("Android")
    }
}
