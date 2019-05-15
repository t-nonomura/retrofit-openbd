package net.storehouse.otatsu.openbdapp.api

import net.storehouse.otatsu.openbdapp.api.response.ISBNResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface ApiClient {
    @GET("get")
    fun getISBN(@Query("isbn") isbn: String): Observable<List<ISBNResponse>>
}