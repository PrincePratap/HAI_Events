package com.cody.haievents.Show.data


import com.cody.haievents.Show.model.AllCategoryResponse
import com.cody.haievents.Show.model.BlogItemResponse
import com.cody.haievents.Show.model.BlogsListResponse
import com.cody.haievents.Show.model.CategoryItemsResponse
import com.cody.haievents.Show.model.CategoryResponse
import com.cody.haievents.Show.model.CreateUserEventRequest
import com.cody.haievents.Show.model.CreateUserEventResponse
import com.cody.haievents.Show.model.GaneshTheaterBookingRequest
import com.cody.haievents.Show.model.GaneshTheaterGetSeatResponse
import com.cody.haievents.Show.model.MyTicketDetails
import com.cody.haievents.Show.model.MyTicketListResponse
import com.cody.haievents.Show.model.OrderRequest
import com.cody.haievents.Show.model.OrderResponse
import com.cody.haievents.Show.model.SearchShowResponse
import com.cody.haievents.Show.model.ShowDetailPageResponse
import com.cody.haievents.Show.model.UploadEventImage
import com.cody.haievents.common.data.remote.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.path


internal class ShowService: KtorApi() {

    suspend fun getShowDetail(showId: Int, userToken: String): ShowDetailPageResponse = client.get {
        endPoint(path = "/api/event-detail")
        setToken(userToken)
        parameter("id", showId)
    }.body()

    suspend fun getTicketPrice(showId: Int,): getShowTicketResponse = client.get {
        endPoint(path = "/api/movie")
        parameter("id", showId)
    }.body()

    suspend fun searchShow(query : String): SearchShowResponse = client.get {
        endPoint(path = "/api/search")
        parameter("query", query)
    }.body()

    suspend fun getBlogsList(): BlogsListResponse = client.get {
        endPoint(path = "/api/blogs")
    }.body()

    suspend fun getBlogsItem(blogId: Int): BlogItemResponse = client.get {
        parameter("id", blogId)
        endPoint(path = "/api/get-blog")
    }.body()

    suspend fun createOrder(request: OrderRequest): OrderResponse = client.post {
        endPoint(path = "/api/create-order")
        setBody(request)
    }.body()

    suspend fun addEvent(request: CreateUserEventRequest): CreateUserEventResponse = client.post {
        endPoint(path = "/api/add-user-event")
        setBody(request)
    }.body()

    suspend fun getALLCategories(): CategoryResponse = client.get {
        endPoint(path = "/api/categories")
    }.body()


    suspend fun uploadEventImage(imageBytes: ByteArray, fileName: String): UploadEventImage {
        return client.post {
            endPoint(path = "/api/upload-user-event-image")
            setBody(
                MultiPartFormDataContent(
                    formData {
                        // This 'append' maps directly to the key-value pair in Postman
                        append("image", imageBytes, Headers.build {
                            // This header tells the server what kind of file it is
                            append(HttpHeaders.ContentType, "image/jpeg") // Or image/png, etc.
                            // This header provides the filename
                            append(HttpHeaders.ContentDisposition, "filename=\"$fileName\"")
                        })
                    }
                )
            )
        }.body() // Ktor automatically deserializes the JSON response into our data class
    }

    suspend fun getGaneshTheater(): GaneshTheaterGetSeatResponse = client.get {
        endPoint(path = "/api/ganeshkala/seats")
    }.body()

    suspend fun ganeshTheaterBooking(request: GaneshTheaterBookingRequest): GaneshTheaterGetSeatResponse = client.post {
        endPoint(path = "/api/ganeshkala/book")
    }.body()


    suspend fun getAllCategory(): AllCategoryResponse = client.get {
        endPoint(path = "/api/categories")
    }.body()

    suspend fun  getCategoryItems(categoryID: Int): CategoryItemsResponse = client.get {
        parameter("category_id", categoryID)
        endPoint(path = "/api/events-by-category")
    }.body()



    suspend fun myTicketList(token: String): MyTicketListResponse = client.get {
        endPoint(path = "/api/my-bookings")
        setToken(token)
    }.body()

    suspend fun myTicketDetails(ticketId: Int, token : String): MyTicketDetails = client.get {
        endPoint(path = "/api/booking")
        parameter("id", ticketId)
        setToken(token)
    }.body()






}