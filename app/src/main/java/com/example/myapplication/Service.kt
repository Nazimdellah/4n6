
package org.depinfo.retrofit_demo.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("{number}")
    fun listReposString(@Path("number") number: String): Call<String>
}