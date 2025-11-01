package com.example.roomease.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomease.domain.model.User
import com.example.roomease.domain.model.UserHostelDetails
import com.example.roomease.domain.usecase.GetUserHostelDetailsUseCase
import com.example.roomease.domain.usecase.SaveUserDetailsUseCase
import com.example.roomease.domain.usecase.SaveUserHostelDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val getUserHostelDetailsUseCase: GetUserHostelDetailsUseCase,
    private val saveUserHostelDetailsUseCase: SaveUserHostelDetailsUseCase,
    private val saveUserDetailsUseCase: SaveUserDetailsUseCase
): ViewModel() {

/*
    var userHostelDetails: UserHostelDetails? = null
        private set
*/

    var loggedInUser: User? = null
        private set

    // Use a StateFlow to represent the UI state
    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    private val _userHostelDetails = MutableStateFlow<UserHostelDetails?>(null)
    val userHostelDetails: StateFlow<UserHostelDetails?> = _userHostelDetails.asStateFlow()


    /**
     * Stores the basic user information (from Firebase auth) in the backend.
     */
    fun storeUser(user: User, onSuccess: () -> Unit) {
        viewModelScope.launch {
            saveUserDetailsUseCase(user)
            loggedInUser = user
            onSuccess()
        }
    }

    /**
     * Loads the user's hostel details from the backend and updates the UI state.
     * This is the central function for determining navigation flow after login.
     */
    fun loadUserDetails(userId: String) {
        // Set state to Loading before starting the network call
        _uiState.value = UserUiState.Loading

        viewModelScope.launch {
            try {
                var details = getUserHostelDetailsUseCase(userId)
                _userHostelDetails.value = details

                // Update the UI state based on whether details were found
                if (details != null) {
                    _uiState.value = UserUiState.DetailsAvailable
                } else {
                    _uiState.value = UserUiState.DetailsAvailable
                }
            } catch (e: Exception) {
                _userHostelDetails.value = null
                _uiState.value = UserUiState.DetailsMissing
            }
        }
    }

    /**
     * Saves new hostel details (e.g., from Login2Screen) to the backend.
     */
    fun saveUserDetails(details: UserHostelDetails, onSuccess: () -> Unit) {
        viewModelScope.launch {
            saveUserHostelDetailsUseCase(details)
            // Optionally, reload the details after saving.
            // Update the local state to reflect the new details immediately
            _userHostelDetails.value = details
            onSuccess()
        }
    }

    /**
     * Updates existing hostel details.
     * Reuses the savedUserHostelDetailsUseCase as its functionality is the same
     */
    fun updateUserDetails(updatedDetails: UserHostelDetails, onSuccess: () -> Unit) {
        viewModelScope.launch {
            saveUserHostelDetailsUseCase(updatedDetails)
            _userHostelDetails.value = updatedDetails
            onSuccess()
        }
    }
}

/**
 * Represents the different states for navigating the user after they are logged in.
 */
sealed class UserUiState {
    /** The app is waiting to fetch user details. **/
    object Loading : UserUiState()

    /** User details were found, and the app can proceed to the main screen. **/
    object DetailsAvailable : UserUiState()

    /** User details were not found, and the app must prompt the user to enter them. **/
    object DetailsMissing : UserUiState()
}