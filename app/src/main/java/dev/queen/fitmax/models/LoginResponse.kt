package dev.queen.fitmax.models

data class LoginResponse(
    var message : String,
    var access_token : String,
    var user_id : String,
    var profile_id : String
)
