package com.folkatech.pressensi.model.companydata

/**
 * Created by Luthfan Maftuh on 8/19/2019.
 * Email luthfanmaftuh@gmail.com
 */
data class CompanyDataRequest(
    val namaPerusahaan: String?,
    val teleponPerusahaan: String?,
    val jumlahPegawai: String?,
    val alamatPerusahaan: String?
) {

    fun isValidCompanyData(): Int {
        return when {
            namaPerusahaan.isNullOrEmpty() -> 0
            teleponPerusahaan.isNullOrEmpty() -> 1
            jumlahPegawai.isNullOrEmpty() -> 2
            alamatPerusahaan.isNullOrEmpty() -> 3
            else -> -1
        }
    }
}