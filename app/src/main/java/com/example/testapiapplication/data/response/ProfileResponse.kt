package com.example.testapiapplication.data.response

data class ProfileResponse(
    val flag: String,
    val image: String,
    val video: String,
    val icon: String,
    val secure: String,
    val is_update_password: String,
    val sign_in_provider: String,
    val display_name: String,
    val uuid: String,
) {
}
// {
//    "flag": false,
//    "image": "",
//    "video": "",
//    "icon": "https://api-storage-staging.gofa.vn/admin/video/gofa_car.gltf",
//    "secure": true,
//    "is_update_password": false
//}