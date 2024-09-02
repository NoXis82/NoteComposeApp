package com.noxis.notecomposeapp.user

data class User(
    val id: String,
    val email: String,
    val fullName: String,
    val verificationStatus: VerificationStatus,
    val memberShipStatus: MemberShipStatus
)

enum class VerificationStatus {
    Verified
}

enum class MemberShipStatus {
    Free
}
