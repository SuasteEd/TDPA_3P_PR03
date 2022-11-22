package com.example.tdpa_3p_pr03
import com.google.gson.annotations.SerializedName
data class DivisaResponse (
    @SerializedName("ListaIndicadores")var Indicadores:List<Indicador>
    )