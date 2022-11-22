package com.example.tdpa_3p_pr03

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET("dof/sidof/indicadores/")
    suspend fun getIndicadores(): Response<DivisaResponse>
}