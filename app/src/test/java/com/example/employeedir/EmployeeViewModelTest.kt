//package com.example.employeedir
//
//import android.app.Application
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.Observer
//import com.example.employeedir.model.EmployeeListState
//import com.example.employeedir.model.EmployeeRepository
//import com.example.employeedir.ui.theme.EmployeeViewModel
//import com.example.employeedir.model.network.Employees
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.resetMain
//import kotlinx.coroutines.test.runTest
//import kotlinx.coroutines.test.setMain
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito.*
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class EmployeeViewModelTest {
//
//    // Mock repository
//    private lateinit var repository: EmployeeRepository
//
//    // The ViewModel we are testing
//    private lateinit var viewModel: EmployeeViewModel
//
//    // A rule to make LiveData work synchronously during testing
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    // Mock observer to observe LiveData changes
//    private lateinit var observer: Observer<EmployeeListState>
//
//    // Test dispatcher for coroutines
//    private val testDispatcher = StandardTestDispatcher()
//
//    @Before
//    fun setUp() {
//        // Set the main dispatcher to the test dispatcher before tests
//        Dispatchers.setMain(testDispatcher)
//
//        // Initialize the mock repository
//        repository = mock(EmployeeRepository::class.java)
//
//        // Initialize the ViewModel with the mock repository
//        viewModel = EmployeeViewModel(repository, mock(Application::class.java))
//
//        // Initialize the observer for LiveData
//        observer = mock(Observer::class.java) as Observer<EmployeeListState>
//
//        // Attach observer to the LiveData for testing
//        viewModel.employeeListState.observeForever(observer)
//    }
//
//    @After
//    fun tearDown() {
//        // Reset the main dispatcher after tests to avoid memory leaks
//        Dispatchers.resetMain()
//    }
//
//    @Test
//    fun `test loading state when fetching employees`() = runTest {
//        // Simulate loading state
//        viewModel.refreshEmployeeList()
//
//        // Run pending coroutines
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Verify that the observer receives the Loading state
//        verify(observer).onChanged(EmployeeListState.Loading)
//    }
//
//    @Test
//    fun `test success state when employees are fetched successfully`() = runTest {
//        // Create a fake employee list
//        val fakeEmployees = listOf(
//            Employees("1", "John Doe", "Engineering", "FULL_TIME", "john@example.com", "", "", "","")
//        )
//
//        // Simulate the repository returning the employee list
//        `when`(repository.getEmployeeList()).thenReturn(fakeEmployees)
//
//        // Trigger the fetch operation
//        viewModel.refreshEmployeeList()
//
//        // Run pending coroutines
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Verify that the observer receives the Success state with sorted employees by name
//        val sortedEmployees = fakeEmployees.sortedBy { it.full_name }
//        verify(observer).onChanged(EmployeeListState.Success(sortedEmployees))
//    }
//
//    @Test
//    fun `test empty state when no employees are returned`() = runTest {
//        // Simulate the repository returning an empty list
//        `when`(repository.getEmployeeList()).thenReturn(emptyList())
//
//        // Trigger the fetch operation
//        viewModel.refreshEmployeeList()
//
//        // Run pending coroutines
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Verify that the observer receives the Empty state
//        verify(observer).onChanged(EmployeeListState.Empty)
//    }
//
//    @Test
//    fun `test error state when fetching employees fails`() = runTest {
//        // Simulate an exception being thrown by the repository
//        `when`(repository.getEmployeeList()).thenThrow(RuntimeException("Network error"))
//
//        // Trigger the fetch operation
//        viewModel.refreshEmployeeList()
//
//        // Run pending coroutines
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Verify that the observer receives the Error state
//        verify(observer).onChanged(EmployeeListState.Error("Failed to load employees: Network error"))
//    }
//
//    @Test
//    fun `test sorting by team`() = runTest {
//        // Create a fake employee list
//        val fakeEmployees = listOf(
//            Employees("1", "John Doe", "Engineering", "FULL_TIME", "john@example.com", "", "", "", ""),
//            Employees("2", "Jane Doe", "Marketing", "FULL_TIME", "jane@example.com", "", "", "","")
//        )
//
//        // Simulate the repository returning the employee list
//        `when`(repository.getEmployeeList()).thenReturn(fakeEmployees)
//
//        // Trigger the fetch operation
//        viewModel.refreshEmployeeList()
//
//        // Run pending coroutines
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Toggle sorting option to TEAM
//        viewModel.toggleSortOption()
//
//        // Verify that the observer receives the Success state with employees sorted by team
//        val sortedByTeam = fakeEmployees.sortedBy { it.team }
//        verify(observer, atLeastOnce()).onChanged(EmployeeListState.Success(sortedByTeam))
//    }
//
//    @Test
//    fun `test malformed data fetching`() = runTest {
//        // Simulate an exception for malformed data
//        `when`(repository.getMalformedList()).thenThrow(RuntimeException("Malformed data"))
//
//        // Trigger the fetch malformed list operation
//        viewModel.fetchMalformedList()
//
//        // Run pending coroutines
//        testDispatcher.scheduler.advanceUntilIdle()
//
//        // Verify that the observer receives the Error state for malformed data
//        verify(observer).onChanged(EmployeeListState.Error("Failed to load malformed data: Malformed data"))
//    }
//}
