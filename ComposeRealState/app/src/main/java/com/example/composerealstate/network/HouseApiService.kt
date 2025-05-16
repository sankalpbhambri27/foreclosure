package com.example.composerealstate.network

import com.example.composerealstate.data.model.HouseEntity
import com.example.composerealstate.data.model.HouseResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * This interface is used to make API calls to a backend server
 */
interface HouseApiService {
    @GET("house")
    suspend fun getAllHouses(): Response<HouseResponse>
}

class FakeHouseApiService : HouseApiService {
    override suspend fun getAllHouses(): Response<HouseResponse> {
        val mockList = HouseResponse().apply {
            add(
                HouseEntity(
                    id = 1,
                    image = "https://example.com/house1.jpg",
                    price = 500000,
                    bedrooms = 3,
                    bathrooms = 2,
                    size = 1500,
                    description = "A lovely family home.",
                    zipCode = "12345",
                    city = "MockCity",
                    latitude = 123456,
                    longitude = 654321,
                    creationDate = "2023-01-01"
                )
            )
            add(
                HouseEntity(
                    id = 2,
                    image = "https://example.com/house2.jpg",
                    price = 750000,
                    bedrooms = 4,
                    bathrooms = 3,
                    size = 2000,
                    description = "Spacious and modern.",
                    zipCode = "67890",
                    city = "TestVille",
                    latitude = 112233,
                    longitude = 445566,
                    creationDate = "2023-02-15"
                )
            )
        }

        return Response.success(mockList)
    }
}