package com.example.animequotes.domain.use_cases

import com.example.animequotes.domain.repository.QuoteRepository
import javax.inject.Inject


//class GetQuoteUseCase @Inject constructor(
//    private val repository: QuoteRepository
//){
//    operator fun invoke(id: Int): Flow<Resource<Character>> = flow {
//        try {
//            emit(Resource.Loading())
//            val character = repository.getCharacterById(id = id).toDomainModel()
//            emit(Resource.Success(character))
//        } catch (e: HttpException) {
//            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
//        } catch (e: IOException) {
//            emit(Resource.Error("Network error occurred. Please check your connection."))
//        }
//    }
//}