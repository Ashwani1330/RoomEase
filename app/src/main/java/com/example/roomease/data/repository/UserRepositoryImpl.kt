package com.example.roomease.data.repository

import com.example.roomease.domain.model.User
import com.example.roomease.domain.model.UserHostelDetails
import com.example.roomease.domain.repository.UserRepository

class UserRepositoryImpl: UserRepository {
    // In a real implementation, this might be backed by local storage or a remote DB.
    private var storedDetails: UserHostelDetails? = null
    private var userDetails: User? = null  // Store user details

    override suspend fun getUserHostelDetails(userId: String): UserHostelDetails? {
        return storedDetails?.takeIf { it.userId == userId }
    }

    override suspend fun saveUserHostelDetails(details: UserHostelDetails) {
        storedDetails =  details
    }

    override suspend fun saveUserDetails(user: User) {
        userDetails = user  // Save user details
    }
}

// Todo -> Checking from real backend(Spring) rather using in-memory variables
/*

// You will need to inject the Ktor HttpClient, likely using Koin
class UserRepositoryImpl(private val client: HttpClient) : UserRepository {

    // --- The in-memory variables are now gone ---

    // The base URL of your Spring Boot backend
    private val BASE_URL = "http://your-backend-ip-address:8080" // Replace with your actual IP

    override suspend fun getUserHostelDetails(userId: String): UserHostelDetails? {
        return try {
            val response = client.get("$BASE_URL/api/users/$userId/details")
            if (response.status.value == 200) {
                response.body<UserHostelDetails>() // Ktor automatically parses JSON to your data class
            } else {
                null
            }
        } catch (e: Exception) {
            // Log the exception e
            null // Return null on network error
        }
    }

    override suspend fun saveUserHostelDetails(details: UserHostelDetails) {
        try {
            client.post("$BASE_URL/api/users/details") {
                contentType(ContentType.Application.Json)
                setBody(details)
            }
        } catch (e: Exception) {
            // Log the exception e
        }
    }

    override suspend fun saveUserDetails(user: User) {
        try {
            client.post("$BASE_URL/api/users") {
                contentType(ContentType.Application.Json)
                setBody(user)
            }
        } catch (e: Exception) {
            // Log the exception e
        }
    }
}
 */