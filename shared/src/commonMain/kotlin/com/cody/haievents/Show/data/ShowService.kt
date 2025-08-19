package com.cody.haievents.Show.data

import com.cody.haievents.Show.data.model.BlogsListResponse
import com.cody.haievents.Show.data.model.OrderRequest
import com.cody.haievents.Show.data.model.OrderResponse
import com.cody.haievents.Show.data.model.SearchShowResponse
import com.cody.haievents.Show.data.model.ShowDetailPageResponse

import com.cody.haievents.common.data.remote.KtorApi
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody


internal class ShowService: KtorApi() {

    suspend fun getShowDetail(showId: Int, userToken: String): ShowDetailPageResponse = client.get {
        endPoint(path = "/api/event-detail")
        parameter("id", showId)
        parameter("api_token", userToken)
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

    suspend fun createOrder(request:OrderRequest): OrderResponse = client.post {
        endPoint(path = "/api/create-order")
        setBody(request)
    }.body()



}