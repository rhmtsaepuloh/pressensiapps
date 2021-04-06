package com.folkatech.pressensi.model.setuserpembayaran


import com.google.gson.annotations.SerializedName

data class SetUserPembayaranRequest(
    @SerializedName("nip")
    val nip: String, // 12345
    @SerializedName("id_price")
    val idPrice: Int, // 1
    @SerializedName("jumlah_user")
    val jumlahUser: Int, // 1
    @SerializedName("lama_berlangganan")
    val lamaBerlangganan: Int, // 1
    @SerializedName("transaction_id")
    val transactionId: String, // 9ccc4b92-f9c2-4ff3-bece-7dfcf40095f6
    @SerializedName("order_id")
    val orderId: String, // 2090785861
    @SerializedName("gross_amount")
    val grossAmount: Double, // 250000.00
    @SerializedName("payment_type")
    val paymentType: String, // credit_card
    @SerializedName("transaction_time")
    val transactionTime: Long, // 123123124212
    @SerializedName("transaction_status")
    val transactionStatus: String, // capture
    @SerializedName("bank_name")
    val bankName: String, // mandiri
    @SerializedName("card_type")
    val cardType: String // credit
)