package br.com.up.carrosup.domain

/**
 * Created by Franklyn on 02/09/2017.
 */
data class Response (val id:Long,val status:String,val msg:String,val url:String) {
    fun isOk() = "OK".equals(status, ignoreCase = true)
}