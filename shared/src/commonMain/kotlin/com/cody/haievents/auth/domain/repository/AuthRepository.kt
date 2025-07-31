package com.cody.haievents.auth.domain.repository


internal interface AuthRepository {

    suspend fun signUp(name: String, email: String, phone : String, password: String): Result<SignupResponse>





//    suspend fun signIn(email: String, password: String): Result<AuthResultData>
//
//    suspend fun signInWithPhone(phone: String, password: String): Result<AuthResultData>
//
//    suspend fun forgetPassword(identifier: String): Result<ForgetPasswordResponse>
//
//    suspend fun verifyOtp(userId: String, otp: String): Result<AuthResultData>
//
//    suspend fun getProfile(): Result<UserDetailsProfileResponse>
//
//    suspend fun updateProfile(request : UpdateProfileRequest): Result<UpdateProfileResponse>
//
//    suspend fun getExploreItems(): Result<List<Pdf>>
//
//    suspend fun getHomeData(): Result<HomeResponse>
//
//    suspend fun resendOtp(userId: String): Result<ResendOtpResponse>
//
//    suspend fun logout(): Result<LogoutResponse>








}